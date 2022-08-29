package com.durex.music.controller;

import com.durex.music.model.MusicPlayer;
import com.durex.music.scene.MainScene;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import javafx.animation.Animation;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;


public class PlayDetailController implements Initializable {

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

    }


    @FXML
    public void handleShowPlayListPaneClick(MouseEvent e) {
        MainScene.getPlayListPane().setVisible(true);
        if (MainScene.getHidePlayListAnim().getStatus() == Animation.Status.RUNNING) {
            MainScene.getHidePlayListAnim().stop();
        }
        MainScene.getShowPlayListAnim().play();
    }

    public void handleHidePlayDetailPaneClick(MouseEvent e) {
        if (MainScene.getShowPlayDetailAnim().getStatus() == Animation.Status.RUNNING) {
            MainScene.getShowPlayDetailAnim().stop();
        }
        MainScene.getHidePlayDetailAnim().play();
    }
}
