package com.durex.music.controller;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.qq.SongDetail;
import com.durex.music.model.qq.Tag;
import com.durex.music.ui.MainPane;
import com.durex.music.service.SongDetailService;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Slf4j
public class SongDetailController implements Initializable {

    @FXML
    private RXAvatar songImage;
    @FXML
    private Label songName;
    @FXML
    private RXAvatar headImage;
    @FXML
    private Label nickname;
    @FXML
    private Label songDesc;
    @FXML
    private HBox tagBox;
    @FXML
    private Label playAll;
    @FXML
    private Label songCollection;
    @FXML
    private Label songMore;
    @FXML
    private HBox tabHBox;
    @FXML
    private Label songNum;
    @FXML
    private BorderPane songDetailPane;
    @FXML
    private VBox songDetailVbox;


    private Node curSelectedTab;

    public void init(String dissId) {
        MainPane.getScrollPane().setVvalue(0);
        final SongDetail songDetail = SongDetailService.getSongDetail(dissId);
        if (songDetail == null) {
            return;
        }

        // logo 图片
        songImage.setImage(new Image(songDetail.getLogo()));
        songImage.setShapeType(RXAvatar.Type.SQUARE);
        songImage.setPrefWidth(170);
        songImage.setPrefHeight(170);

        Rectangle clip = new Rectangle(
                songImage.getPrefWidth(), songImage.getPrefHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        songImage.setClip(clip);
        songName.setText(songDetail.getDissname());

        // 头像 昵称 标签
        headImage.setImage(new Image(songDetail.getHeadurl()));
        nickname.setText(songDetail.getNickname());
        final List<Tag> tagList = songDetail.getTags();
        if (tagList != null && !tagList.isEmpty()) {
            final List<Label> tagLabelList = tagList.stream().map(Tag::getName)
                    .map(name -> {
                        Label label = new Label("#" + name);
                        label.setFont(Font.font(14));
                        label.setTextFill(Color.web("#7b7b7b"));
                        return label;
                    }).toList();
            tagBox.getChildren().addAll(tagLabelList);
        }

        // 歌单描述
        songDesc.setText(songDetail.getDesc());
        Tooltip tooltip = new Tooltip();
        tooltip.setPrefWidth(200);
        tooltip.setWrapText(true);
        tooltip.setShowDelay(Duration.millis(100));
        tooltip.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        tooltip.textProperty().bind(songDesc.textProperty());
        songDesc.setTooltip(tooltip);

        // 播放全部 收藏 更新 按钮
        playAll.setAlignment(Pos.CENTER);
        playAll.setTextFill(Color.WHITE);
        playAll.setBackground(new Background(new BackgroundFill(MusicConstant.MENU_SELECTED_COLOR, new CornerRadii(25), null)));
        playAll.setOnMouseEntered(event -> playAll.setOpacity(0.8));
        playAll.setOnMouseExited(event -> playAll.setOpacity(1));

        songCollection.setAlignment(Pos.CENTER);
        songCollection.setBackground(new Background(new BackgroundFill(Color.web("#e3e3e3"), new CornerRadii(25), null)));
        songCollection.setOnMouseEntered(event -> songCollection.setOpacity(0.8));
        songCollection.setOnMouseExited(event -> songCollection.setOpacity(1));

        songMore.setAlignment(Pos.TOP_CENTER);
        songMore.setBackground(new Background(new BackgroundFill(Color.web("#e3e3e3"), new CornerRadii(50), null)));
        songMore.setOnMouseEntered(event -> songMore.setOpacity(0.8));
        songMore.setOnMouseExited(event -> songMore.setOpacity(1));


        // 歌曲数量以及评论数量
        songNum.setText("歌曲" + songDetail.getSongnum());
        for (int i = 0; i < tabHBox.getChildren().size(); i++) {
            final Node node = tabHBox.getChildren().get(i);
            // 默认选中第 0 个
            if (i == 0) {
                selectedTab(node);
            }
            node.setOnMouseClicked(event -> selectedTab(node));
        }

        // 歌曲列表
        final FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainPane.class.getResource("/fxml/music-list.fxml")));
        try {
            Parent musicList = fxmlLoader.load();
            songDetailPane.setCenter(musicList);
            MusicListController controller = fxmlLoader.getController();
            controller.init(songDetail);
            // 单向绑定高度变化
            songDetailVbox.prefHeightProperty().bind(
                    songDetailPane.prefHeightProperty()
            );
        } catch (IOException e) {
            log.error("加载歌曲列表失败: ", e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void selectedTab(Node node) {

        if (curSelectedTab != null) {
            Node lastSelectedTab = this.curSelectedTab;

            final Label lastSongNumLabel = (Label) lastSelectedTab.lookup(".tab-label");
            lastSongNumLabel.setTextFill(Color.BLACK);
            final Rectangle lastSongNumBottom = (Rectangle) lastSelectedTab.lookup(".song-num-bottom");
            lastSongNumBottom.setVisible(false);
        }

        final Label songNumLabel = (Label) node.lookup(".tab-label");
        songNumLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
        final Rectangle rectangle = (Rectangle) node.lookup(".song-num-bottom");
        rectangle.setVisible(true);

        this.curSelectedTab = node;
    }

}
