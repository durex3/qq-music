package com.durex.music.scene;

import com.durex.music.constant.MusicConstant;
import com.durex.music.controller.MainController;
import com.durex.music.exception.MusicException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * <h1>只会被加载一次</h1>
 */
@Slf4j
public class MainScene {

    private static ScrollPane contentPane;
    private static AnchorPane playListPane;
    private static AnchorPane playDetailPane;

    private static Timeline showPlayListAnim;
    private static Timeline hidePlayListAnim;
    private static Timeline showPlayDetailAnim;
    private static Timeline hidePlayDetailAnim;

    private static final Font MENU = Font.loadFont(MainScene.class.getResourceAsStream("/font/qq-music-menu.ttf"), 25);
    private static final Font TOOL = Font.loadFont(MainScene.class.getResourceAsStream("/font/qq-music-tool.ttf"), 13);

    private MainScene() {
    }

    public static synchronized void load(Stage stage) {
        AnchorPane root;
        MainController mainController;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainScene.class.getResource("/fxml/main.fxml")));
            root = fxmlLoader.load();
            mainController = fxmlLoader.getController();

            // 加载播放器内容面板唯一
            AnchorPane mainPane = (AnchorPane) root.lookup("#content-pane");
            final ScrollPane contentPane = (ScrollPane) mainPane.lookup("#scroll-pane");
            MainScene.contentPane = contentPane;
            MainScene.playListPane = (AnchorPane) root.lookup("#play-list-pane");

            // 加载播放详情面板
            AnchorPane playDetailPane = (AnchorPane) root.lookup("#play-detail-pane");
            MainScene.playDetailPane = playDetailPane;
            fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainScene.class.getResource("/fxml/play-detail.fxml")));
            playDetailPane.getChildren().add(fxmlLoader.load());

            // 加载推荐内容面板
            fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainScene.class.getResource("/fxml/recommend.fxml")));
            contentPane.setContent(fxmlLoader.load());
        } catch (IOException e) {
            log.error("初始化主面板失败: ", e);
            throw new MusicException(e);
        }
        loadLeftMenu(root);
        loadTopInfo(root);
        loadBottomPlayInfo(root);
        initAnim();
        stage.setScene(new Scene(root));
        mainController.init();
    }

    public static ScrollPane getContentPane() {
        return contentPane;
    }

    public static AnchorPane getPlayListPane() {
        return playListPane;
    }

    public static AnchorPane getPlayDetailPane() {
        return playDetailPane;
    }

    public static Timeline getShowPlayListAnim() {
        return showPlayListAnim;
    }

    public static Timeline getHidePlayListAnim() {
        return hidePlayListAnim;
    }

    public static Timeline getShowPlayDetailAnim() {
        return showPlayDetailAnim;
    }

    public static Timeline getHidePlayDetailAnim() {
        return hidePlayDetailAnim;
    }

    /**
     * <h2>加载底部播放信息</h2>
     *
     * @param root root
     */
    private static void loadBottomPlayInfo(AnchorPane root) {

    }

    /**
     * <h2>加载顶部搜索以及最大最小化等工具</h2>
     *
     * @param root root
     */
    private static void loadTopInfo(AnchorPane root) {
        Label searchLabel = (Label) root.lookup("#search-icon-label");
        searchLabel.setText("\ue600;");
        searchLabel.setFont(TOOL);

        Label minimizeLabel = (Label) root.lookup("#minimize-label");
        minimizeLabel.setText("\ue65a;");
        minimizeLabel.setFont(TOOL);

        Label maximizeLabel = (Label) root.lookup("#maximize-label");
        maximizeLabel.setText("\ue65d;");
        maximizeLabel.setFont(TOOL);

        Label closeLabel = (Label) root.lookup("#close-label");
        closeLabel.setText("\ue661;");
        closeLabel.setFont(TOOL);
    }

    /**
     * <h2>加载左侧菜单</h2>
     *
     * @param root root
     */
    private static void loadLeftMenu(Parent root) {

        // 默认选择推荐
        Pane recommendPane = (Pane) root.lookup("#recommend-pane");
        recommendPane.setBackground(new Background(new BackgroundFill(MusicConstant.MENU_SELECTED_COLOR, MusicConstant.MENU_CORNER_RADII, null)));

        Label recommendLabel = (Label) root.lookup("#recommend-label");
        recommendLabel.setTextFill(Color.WHITE);
        recommendLabel.setText("\ue606;");
        recommendLabel.setFont(MENU);

        Label recommendText = (Label) root.lookup("#recommend-text");
        recommendText.setTextFill(Color.WHITE);

        // 音乐馆
        Label musicLabel = (Label) root.lookup("#music-label");
        musicLabel.setText("\ue644;;");
        musicLabel.setFont(MENU);

        // 视频
        Label videoLabel = (Label) root.lookup("#video-label");
        videoLabel.setText("\ue6e7;");
        videoLabel.setFont(MENU);

        // 电台
        Label radioLabel = (Label) root.lookup("#radio-label");
        radioLabel.setText("\ue693;");
        radioLabel.setFont(MENU);
    }

    private static void initAnim() {
        showPlayListAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainScene.getPlayListPane().translateXProperty(), 0)));
        hidePlayListAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainScene.getPlayListPane().translateXProperty(), 300)));
        hidePlayListAnim.setOnFinished(actionEvent -> MainScene.getPlayListPane().setVisible(false));

        showPlayDetailAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainScene.getPlayDetailPane().translateYProperty(), 0)));
        hidePlayDetailAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainScene.getPlayDetailPane().translateYProperty(), 690)));
        hidePlayDetailAnim.setOnFinished(actionEvent -> MainScene.getPlayDetailPane().setVisible(false));
    }

}
