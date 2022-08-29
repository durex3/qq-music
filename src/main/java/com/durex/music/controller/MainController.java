package com.durex.music.controller;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.MusicPlayer;
import com.durex.music.scene.MainScene;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * <h2>主面板 controller</h2>
 *
 * @author liugelong
 * @date 2022/8/15 09:49
 */
@Slf4j
public class MainController implements Initializable {

    private Stage stage;
    private double dragOffsetX;
    private double dragOffsetY;
    private Pane curSelectedPane;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private RXAvatar curMusicPlayImage;
    @FXML
    private Label curMusicPlayName;
    @FXML
    private ToggleButton playButton;
    @FXML
    private Label curMusicSeconds;
    @FXML
    private Label curMusicTotalSeconds;
    @FXML
    private RXMediaProgressBar currMusicProgress;
    @FXML
    private Region preBtn;
    @FXML
    private Region nextBtn;
    @FXML
    private Label bottomPlayListNum;
    @FXML
    private Label playListNum;
    @FXML
    private HBox clearListBtn;
    @FXML
    private ListView<AnchorPane> playListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 默认选中的菜单
        curSelectedPane = (Pane) mainPane.lookup("#recommend-pane");

        // 当前播放歌曲的图片
        curMusicPlayImage.imageProperty().bind(MusicPlayer.getCurMusicPlayImage());
        // 当前播放歌曲的名字
        curMusicPlayName.textProperty().bind(MusicPlayer.getCurMusicPlayName());
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
        MusicPlayer.setCurMusicSeconds(curMusicSeconds);
        // 当前歌曲的总时长(秒)
        curMusicTotalSeconds.textProperty().bind(MusicPlayer.getCurMusicTotalSeconds());
        // 当前歌曲进度条
        MusicPlayer.setCurrMusicProgress(currMusicProgress);
        preBtn.setOnMouseClicked(event -> MusicPlayer.playPreMusic());
        nextBtn.setOnMouseClicked(event -> MusicPlayer.playNextMusic());
        bottomPlayListNum.textProperty().bind(MusicPlayer.getCurrentPlayListNum());
        playListNum.textProperty().bind(MusicPlayer.getCurrentPlayListNum());
        playListView.setItems(MusicPlayer.getPlayList());
        playListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                final int selectedIndex = playListView.getSelectionModel().getSelectedIndex();
                MusicPlayer.play(selectedIndex, MusicPlayer.getTablePlayList().get(selectedIndex));
            }
        });
    }

    public synchronized void init() {
        this.stage = (Stage) mainPane.getScene().getWindow();
    }

    @FXML
    public void handleMinimizeClicked(MouseEvent event) {
        stage.setIconified(true);
    }

    @FXML
    public void handleCloseClicked(MouseEvent event) {
        stage.close();
    }

    @FXML
    public void handleMousePressed(MouseEvent e) {
        this.dragOffsetX = e.getScreenX() - stage.getX();
        this.dragOffsetY = e.getScreenY() - stage.getY();
    }

    @FXML
    public void handleMouseDragged(MouseEvent e) {
        stage.setX(e.getScreenX() - this.dragOffsetX);
        stage.setY(e.getScreenY() - this.dragOffsetY);
    }

    @FXML
    public void handleRecommendClicked(MouseEvent e) {
        setMenuStyle((Pane) e.getSource());
        final FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainScene.class.getResource("/fxml/recommend.fxml")));
        try {
            MainScene.getContentPane().setContent(fxmlLoader.load());
        } catch (IOException ex) {
            log.error("加载推荐页面失败: ", ex);
        }
    }

    @FXML
    public void handleMusicClicked(MouseEvent e) {
        setMenuStyle((Pane) e.getSource());
        MainScene.getContentPane().setContent(new Label("音乐馆"));
    }


    @FXML
    public void handleVideoClicked(MouseEvent e) {
        setMenuStyle((Pane) e.getSource());
        MainScene.getContentPane().setContent(new Label("视频"));
    }

    @FXML
    public void handleRadioClicked(MouseEvent e) {
        setMenuStyle((Pane) e.getSource());
        MainScene.getContentPane().setContent(new Label("电台"));
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
    public void handleHidePlayListPaneClick(MouseEvent e) {
        if (MainScene.getShowPlayListAnim().getStatus() == Animation.Status.RUNNING) {
            MainScene.getShowPlayListAnim().stop();
        }
        MainScene.getHidePlayListAnim().play();
    }

    @FXML
    public void handleShowPlayDetailPaneClick(MouseEvent e) {
        MainScene.getPlayDetailPane().setVisible(true);
        if (MainScene.getHidePlayDetailAnim().getStatus() == Animation.Status.RUNNING) {
            MainScene.getHidePlayDetailAnim().stop();
        }
        MainScene.getShowPlayDetailAnim().play();
    }

    private void setMenuStyle(Pane source) {
        curSelectedPane.setBackground(null);
        curSelectedPane.getChildren().forEach(node -> ((Label) node).setTextFill(Color.BLACK));

        source.getChildren().forEach(node -> ((Label) node).setTextFill(Color.WHITE));
        source.setBackground(new Background(new BackgroundFill(MusicConstant.MENU_SELECTED_COLOR, MusicConstant.MENU_CORNER_RADII, null)));

        this.curSelectedPane = source;
    }
}
