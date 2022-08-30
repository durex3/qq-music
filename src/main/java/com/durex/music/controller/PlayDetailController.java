package com.durex.music.controller;

import com.durex.music.model.MusicPlayer;
import com.durex.music.scene.MainScene;
import com.durex.music.service.MusicService;
import com.leewyatt.rxcontrols.controls.RXAudioSpectrum;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXLrcView;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import com.leewyatt.rxcontrols.pojo.LrcDoc;
import javafx.animation.Animation;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class PlayDetailController implements Initializable {


    private final float[] emptyAry = new float[128];

    @FXML
    private ToggleButton playButton;
    @FXML
    private Label curMusicSeconds;
    @FXML
    private Label curMusicTotalSeconds;
    @FXML
    private RXMediaProgressBar currMusicProgress;
    @FXML
    private Label bottomPlayListNum;
    @FXML
    private Region preBtn;
    @FXML
    private Region nextBtn;
    @FXML
    private RXAvatar curMusicPlayImage;
    @FXML
    private Label curMusicPlayName;
    @FXML
    private Label curMusicPlaySinger;
    @FXML
    private Label curMusicPlayAlbum;
    @FXML
    private RXLrcView curMusicLrcView;
    @FXML
    private RXAudioSpectrum curMusicAudioSpectrum;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 播放按钮 双向绑定
        playButton.selectedProperty().bindBidirectional(MusicPlayer.getPlayButtonSelected());
        // 监听是否播放
        playButton.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) {
                MusicPlayer.play();
            } else {
                MusicPlayer.pause();
            }
        });
        // 当前歌曲的进度(秒)
        curMusicSeconds.textProperty().bind(MusicPlayer.getCurMusicSeconds().textProperty());
        // 当前歌曲的总时长(秒)
        curMusicTotalSeconds.textProperty().bind(MusicPlayer.getCurMusicTotalSeconds());
        // 当前歌曲进度条 双向绑定主界面的进度条属性
        Bindings.bindBidirectional(currMusicProgress.durationProperty(), MusicPlayer.getCurrMusicProgress().durationProperty());
        Bindings.bindBidirectional(currMusicProgress.bufferProgressTimeProperty(), MusicPlayer.getCurrMusicProgress().bufferProgressTimeProperty());
        Bindings.bindBidirectional(currMusicProgress.currentTimeProperty(), MusicPlayer.getCurrMusicProgress().currentTimeProperty());

        // 进度条拖动和点击事件
        currMusicProgress.setOnMouseDragged(event -> {
            if (MusicPlayer.getPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
                MusicPlayer.getPlayer().seek(currMusicProgress.getCurrentTime());
            }
        });

        currMusicProgress.setOnMouseClicked(event -> {
            if (MusicPlayer.getPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
                MusicPlayer.getPlayer().seek(currMusicProgress.getCurrentTime());
            }
        });

        preBtn.setOnMouseClicked(event -> MusicPlayer.playPreMusic());
        nextBtn.setOnMouseClicked(event -> MusicPlayer.playNextMusic());
        bottomPlayListNum.textProperty().bind(MusicPlayer.getCurrentPlayListNum());
        // 当前播放歌曲的图片
        MusicPlayer.getCurMusicPlayImage().addListener((observableValue, o, n) ->
                curMusicPlayImage.setImage(new Image(n.getUrl(), 350, 350, false, false, true)));

        MusicPlayer.getCurMusicPlayName().addListener((observableValue, o, n) -> {
            curMusicPlayName.setText(n.split("-")[0]);
            curMusicPlaySinger.setText(n.split("-")[1]);
        });
        curMusicPlayAlbum.textProperty().bind(MusicPlayer.getCurMusicPlayAlbum());

        // 解析歌词
        MusicPlayer.getCurMusicPlayMid().addListener((observableValue, o, n) -> {
            if (o != null && !o.isBlank()) {
                curMusicLrcView.currentTimeProperty().unbind();
                curMusicLrcView.setCurrentTime(Duration.ZERO);
                MusicPlayer.getPlayer().setAudioSpectrumListener(null);
                curMusicAudioSpectrum.setMagnitudes(emptyAry);
            }
            final String lyric = MusicService.getLyric(n);
            curMusicLrcView.setLrcDoc(LrcDoc.parseLrcDoc(lyric));
            // 设置歌词进度
            curMusicLrcView.currentTimeProperty().bind(MusicPlayer.getPlayer().currentTimeProperty());
            //设置频谱可视化
            MusicPlayer.getPlayer().setAudioSpectrumListener((timestamp, duration, magnitudes, phases) ->
                    curMusicAudioSpectrum.setMagnitudes(magnitudes));
        });

    }


    @FXML
    public void handleShowPlayListPaneClick(MouseEvent e) {
        MainScene.getPlayListPane().setVisible(true);
        if (MainScene.getHidePlayListAnim().getStatus() == Animation.Status.RUNNING) {
            MainScene.getHidePlayListAnim().stop();
        }
        MainScene.getShowPlayListAnim().play();
    }

    @FXML
    public void handleHidePlayDetailPaneClick(MouseEvent e) {
        if (MainScene.getShowPlayDetailAnim().getStatus() == Animation.Status.RUNNING) {
            MainScene.getShowPlayDetailAnim().stop();
        }
        MainScene.getHidePlayDetailAnim().play();
    }
}
