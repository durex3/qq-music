package com.durex.music.ui.page;

import com.durex.music.constant.MusicConstant;
import com.durex.music.exception.MusicException;
import com.durex.music.model.HistoryStack;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
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
public class MainPane {

    private static ScrollPane scrollPane;
    private static AnchorPane playListPane;
    private static AnchorPane playDetailPane;
    private static Timeline showPlayListAnim;
    private static Timeline hidePlayListAnim;
    private static Timeline showPlayDetailAnim;
    private static Timeline hidePlayDetailAnim;
    private static Pane curSelectedPane;

    private static final Font MENU = Font.loadFont(MainPane.class.getResourceAsStream("/font/qq-music-menu.ttf"), 25);
    private static final Font TOOL = Font.loadFont(MainPane.class.getResourceAsStream("/font/qq-music-tool.ttf"), 13);

    private MainPane() {
    }

    public static synchronized void load(Stage stage) {
        AnchorPane root;
        try {
            // 主面板
            FXMLLoader fxmlLoader = new FXMLLoader(MainPane.class.getResource("/fxml/main.fxml"));
            root = fxmlLoader.load();
            loadRightPane(root);
        } catch (IOException e) {
            log.error("初始化主面板失败: ", e);
            throw new MusicException(e);
        }
        initAnim();
        stage.setScene(new Scene(root));
    }

    public static void setMenuStyle(Pane pane) {
        if (curSelectedPane != null) {
            curSelectedPane.setBackground(null);
            curSelectedPane.getChildren().forEach(node -> ((Label) node).setTextFill(Color.BLACK));
        }

        pane.getChildren().forEach(node -> ((Label) node).setTextFill(Color.WHITE));
        pane.setBackground(new Background(new BackgroundFill(MusicConstant.MENU_SELECTED_COLOR, MusicConstant.MENU_CORNER_RADII, null)));

        curSelectedPane = pane;
    }

    public static void resetMenuStyle() {
        if (curSelectedPane != null) {
            curSelectedPane.setBackground(null);
            curSelectedPane.getChildren().forEach(node -> ((Label) node).setTextFill(Color.BLACK));
            curSelectedPane = null;
        }
    }

    public static ScrollPane getScrollPane() {
        return scrollPane;
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

    public static Pane getCurSelectedPane() {
        return curSelectedPane;
    }

    private static void loadRightPane(AnchorPane root) throws IOException {
        final AnchorPane rightPane = (AnchorPane) root.lookup("#right-pane");
        rightPane.prefWidthProperty().bind(root.widthProperty().subtract(MusicConstant.LEFT_MENU_WIDTH));
        rightPane.prefHeightProperty().bind(root.heightProperty());

        loadLeftMenu(root);
        loadTopInfo(root);
        loadContentPane(root);
        loadBottomPlayInfo(root);
    }

    /**
     * <h2>加载底部播放信息</h2>
     *
     * @param root root
     */
    private static void loadBottomPlayInfo(AnchorPane root) {
        final RXMediaProgressBar currMusicProgress = (RXMediaProgressBar) root.lookup("#curr-music-progress");
        currMusicProgress.prefWidthProperty().bind(root.widthProperty());
        final HBox currPlayInfoBox = (HBox) root.lookup("#curr-play-info-box");
        currPlayInfoBox.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
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

        loadWindowTool(root, Color.BLACK);
    }

    private static void loadWindowTool(AnchorPane root, Color color) {
        Label minimizeLabel = (Label) root.lookup("#minimize-label");
        minimizeLabel.setText("\ue65a;");
        minimizeLabel.setFont(TOOL);
        minimizeLabel.setTextFill(color);

        Label maximizeLabel = (Label) root.lookup("#maximize-label");
        maximizeLabel.setText("\ue65d;");
        maximizeLabel.setFont(TOOL);
        maximizeLabel.setTextFill(color);

        Label closeLabel = (Label) root.lookup("#close-label");
        closeLabel.setText("\ue661;");
        closeLabel.setFont(TOOL);
        closeLabel.setTextFill(color);
    }

    /**
     * <h2>加载左侧菜单</h2>
     *
     * @param root root
     */
    private static void loadLeftMenu(AnchorPane root) {

        final AnchorPane leftMenuPane = (AnchorPane) root.lookup("#left-Menu-pane");
        leftMenuPane.prefHeightProperty().bind(root.heightProperty());

        // 默认选择推荐
        Pane recommendPane = (Pane) root.lookup("#recommend-pane");
        setMenuStyle(recommendPane);

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
        showPlayListAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainPane.getPlayListPane().translateXProperty(), 0)));
        hidePlayListAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainPane.getPlayListPane().translateXProperty(), 300)));
        hidePlayListAnim.setOnFinished(actionEvent -> MainPane.getPlayListPane().setVisible(false));

        showPlayDetailAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainPane.getPlayDetailPane().translateYProperty(), 0)));
        hidePlayDetailAnim = new Timeline(new KeyFrame(Duration.millis(300), new KeyValue(MainPane.getPlayDetailPane().translateYProperty(), 690)));
        hidePlayDetailAnim.setOnFinished(actionEvent -> MainPane.getPlayDetailPane().setVisible(false));
    }

    private static void loadContentPane(AnchorPane root) throws IOException {
        // 播放器内容滚动面板 和播放队列面板
        MainPane.scrollPane = (ScrollPane) root.lookup("#scroll-pane");
        MainPane.scrollPane.prefWidthProperty().bind(root.widthProperty().subtract(MusicConstant.LEFT_MENU_WIDTH));
        MainPane.scrollPane.prefHeightProperty().bind(root.heightProperty().subtract(MusicConstant.TOP_BOTTOM_GAP_HEIGHT));

        MainPane.playListPane = (AnchorPane) root.lookup("#play-list-pane");
        AnchorPane listPane = (AnchorPane) root.lookup("#list-pane");
        listPane.prefHeightProperty().bind(root.heightProperty());
        ScrollPane playListScrollPane = (ScrollPane) root.lookup(".play-list-scroll-pane");
        playListScrollPane.minHeightProperty().bind(root.heightProperty().subtract(165));

        // 加载播放详情面板
        AnchorPane playDetailPane = (AnchorPane) root.lookup("#play-detail-pane");
        MainPane.playDetailPane = playDetailPane;
        final AnchorPane playDetail = FXMLLoader.load(Objects.requireNonNull(MainPane.class.getResource("/fxml/play-detail.fxml")));
        playDetailPane.getChildren().add(playDetail);
        loadWindowTool(playDetail, Color.WHITE);

        // 默认加载推荐内容面板
        final RecommendPagePane recommendPagePane = new RecommendPagePane();
        scrollPane.setContent(recommendPagePane.load(null));
        final HistoryStack.History recommendHistory = new HistoryStack.History();
        recommendHistory.setPagePane(recommendPagePane);
        recommendHistory.setParam(curSelectedPane);

        MainPane.getScrollPane().contentProperty().addListener((observableValue, o, n) -> {
            if (o != null && RecommendPagePane.getInstance() != o) {
                ((Pane) o).getChildren().clear();
            }
        });

        HistoryStack.push(recommendHistory);
    }
}
