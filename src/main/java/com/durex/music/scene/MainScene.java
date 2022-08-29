package com.durex.music.scene;

import com.durex.music.constant.MusicConstant;
import com.durex.music.controller.MainController;
import com.durex.music.exception.MusicException;
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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * <h1>只会被加载一次</h1>
 */
@Slf4j
public class MainScene {

    private static ScrollPane contentPane;

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

            // 加载播放器内容面板
            AnchorPane mainPane = (AnchorPane) root.lookup("#content-pane");
            final ScrollPane contentPane = (ScrollPane) mainPane.lookup("#scroll-pane");
            fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainScene.class.getResource("/fxml/recommend.fxml")));
            contentPane.setContent(fxmlLoader.load());
        } catch (IOException e) {
            //log.error("初始化主面板失败: ", e);
            throw new MusicException(e);
        }
        loadLeftMenu(root);
        loadTopInfo(root);
        loadBottomPlayInfo(root);
        stage.setScene(new Scene(root));
        mainController.init();
    }

    public static ScrollPane getContentPane() {
        return contentPane;
    }

    public static void setContentPane(ScrollPane contentPane) {
        MainScene.contentPane = contentPane;
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
}
