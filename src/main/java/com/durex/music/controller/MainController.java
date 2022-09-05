package com.durex.music.controller;

import com.durex.music.model.HistoryStack;
import com.durex.music.model.MusicPlayer;
import com.durex.music.ui.BasePagePane;
import com.durex.music.ui.MainPane;
import com.durex.music.ui.MusicHallPagePane;
import com.durex.music.ui.PaneFactory;
import com.durex.music.ui.RadioPagePane;
import com.durex.music.ui.RecommendPagePane;
import com.durex.music.ui.SoundPane;
import com.durex.music.ui.VideoPagePane;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h2>主面板 controller</h2>
 *
 * @author liugelong
 * @date 2022/8/15 09:49
 */
@Slf4j
public class MainController implements Initializable {

    private double dragOffsetX;
    private double dragOffsetY;
    private ContextMenu soundPopup;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private StackPane curMusicPlayImagePane;
    @FXML
    private RXAvatar curMusicPlayImage;
    @FXML
    private Label showPlayDetailIcon;
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
    @FXML
    private StackPane soundBtn;
    @FXML
    private Label backBtn;
    @FXML
    private Label forwardBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 后退和前进按钮
        backBtn.disableProperty().bind(HistoryStack.getBackStackIsEmpty());
        forwardBtn.disableProperty().bind(HistoryStack.getForwardStackIsEmpty());
        // 当前播放歌曲的图片
        curMusicPlayImage.imageProperty().bind(MusicPlayer.getCurMusicPlayImage());
        curMusicPlayImagePane.setOnMouseEntered(event -> {
            if (curMusicPlayImage.getImage() != null) {
                curMusicPlayImagePane.setOpacity(0.5);
                showPlayDetailIcon.setVisible(true);
            }
        });
        curMusicPlayImagePane.setOnMouseExited(event -> {
            if (curMusicPlayImage.getImage() != null) {
                curMusicPlayImagePane.setOpacity(1);
                showPlayDetailIcon.setVisible(false);
            }
        });
        // 当前播放歌曲的名字
        curMusicPlayName.textProperty().bind(MusicPlayer.getCurMusicPlayName());
        Tooltip tooltip = new Tooltip();
        tooltip.setShowDelay(Duration.millis(100));
        tooltip.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        tooltip.textProperty().bind(curMusicPlayName.textProperty());
        curMusicPlayName.setTooltip(tooltip);
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
        bottomPlayListNum.textProperty().bind(MusicPlayer.getMusicPlayList().sizeProperty());
        playListNum.textProperty().bind(MusicPlayer.getMusicPlayList().sizeProperty());
        playListView.setItems(MusicPlayer.getMusicPlayList().getMusicPaneList());
        playListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                final int selectedIndex = playListView.getSelectionModel().getSelectedIndex();
                MusicPlayer.play(selectedIndex, MusicPlayer.getMusicPlayList().getMusicPropertyList().get(selectedIndex));
            }
        });

        // 声音弹出
        soundPopup = new ContextMenu(new SeparatorMenuItem());
        final AnchorPane soundPane = SoundPane.load();
        if (soundPane != null) {
            soundPopup.getScene().setRoot(soundPane);
        }
    }

    @FXML
    public void handleMinimizeClicked(MouseEvent event) {
        getStage().setIconified(true);
    }

    @FXML
    public void handleCloseClicked(MouseEvent event) {
        getStage().close();
    }

    @FXML
    public void handleMousePressed(MouseEvent e) {
        this.dragOffsetX = e.getScreenX() - getStage().getX();
        this.dragOffsetY = e.getScreenY() - getStage().getY();
    }

    @FXML
    public void handleMouseDragged(MouseEvent e) {
        getStage().setX(e.getScreenX() - this.dragOffsetX);
        getStage().setY(e.getScreenY() - this.dragOffsetY);
    }

    @FXML
    public void handleRecommendClicked(MouseEvent e) {
        BasePagePane pane = PaneFactory.newInstance(RecommendPagePane.class);
        MainPane.getScrollPane().setContent(pane.load(e.getSource()));
    }

    @FXML
    public void handleMusicClicked(MouseEvent e) {
        BasePagePane pane = PaneFactory.newInstance(MusicHallPagePane.class);
        MainPane.getScrollPane().setContent(pane.load(e.getSource()));
    }


    @FXML
    public void handleVideoClicked(MouseEvent e) {
        BasePagePane pane = PaneFactory.newInstance(VideoPagePane.class);
        MainPane.getScrollPane().setContent(pane.load(e.getSource()));
    }

    @FXML
    public void handleRadioClicked(MouseEvent e) {
        BasePagePane pane = PaneFactory.newInstance(RadioPagePane.class);
        MainPane.getScrollPane().setContent(pane.load(e.getSource()));
    }

    @FXML
    public void handleShowPlayListPaneClicked(MouseEvent e) {
        MainPane.getPlayListPane().setVisible(true);
        if (MainPane.getHidePlayListAnim().getStatus() == Animation.Status.RUNNING) {
            MainPane.getHidePlayListAnim().stop();
        }
        MainPane.getShowPlayListAnim().play();
    }

    @FXML
    public void handleHidePlayListPaneClicked(MouseEvent e) {
        if (MainPane.getShowPlayListAnim().getStatus() == Animation.Status.RUNNING) {
            MainPane.getShowPlayListAnim().stop();
        }
        MainPane.getHidePlayListAnim().play();
    }

    @FXML
    public void handleShowPlayDetailPaneClicked(MouseEvent e) {
        if (MusicPlayer.getPlayer() == null) {
            return;
        }
        MainPane.getPlayDetailPane().setVisible(true);
        if (MainPane.getHidePlayDetailAnim().getStatus() == Animation.Status.RUNNING) {
            MainPane.getHidePlayDetailAnim().stop();
        }
        MainPane.getShowPlayDetailAnim().play();
    }

    @FXML
    public void handleSoundPopupClicked(MouseEvent e) {
        Bounds bounds = soundBtn.localToScreen(soundBtn.getBoundsInLocal());
        soundPopup.show(mainPane.getScene().getWindow(), bounds.getMinX() - 20, bounds.getMinY() - 165);
    }

    @FXML
    public void handleBackClicked(MouseEvent e) {
        final HistoryStack.History history = HistoryStack.back();
        setHistoryNode(history);
    }

    @FXML
    public void handleForwardClicked(MouseEvent e) {
        final HistoryStack.History history = HistoryStack.forward();
        setHistoryNode(history);
    }

    private static void setHistoryNode(HistoryStack.History history) {
        if (history == null) {
            return;
        }
        if (history.getMenu() != null) {
            MainPane.setMenuStyle(history.getMenu());
        }
        MainPane.getScrollPane().setContent(null);
        MainPane.getScrollPane().setContent(history.getNode());
    }

    private Stage getStage() {
        return (Stage) mainPane.getScene().getWindow();
    }
}
