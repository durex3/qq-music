package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.utils.NumberUtils;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.math.BigDecimal;

public class SongVBox {

    private SongVBox() {
    }

    /**
     * <h2>构造单个歌单 ui</h2>
     *
     * @param imageUrl  歌单封面图
     * @param titleText 歌单名
     */
    public static VBox build(String id, String imageUrl, String titleText, long listenNum) {
        VBox song = new VBox();
        song.setSpacing(5);

        StackPane stackPane = new StackPane();

        // 封面图
        RXAvatar songImage = new RXAvatar(new Image(imageUrl, 170, 170, false, false, true));
        songImage.setId("song-image");
        songImage.setUserData(id);
        songImage.setShapeType(RXAvatar.Type.SQUARE);
        songImage.setPrefWidth(170);
        songImage.setPrefHeight(170);

        Rectangle clip = new Rectangle(
                songImage.getPrefWidth(), songImage.getPrefHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        songImage.setClip(clip);

        // 播放量 box
        HBox listenBox = new HBox();
        listenBox.setAlignment(Pos.CENTER);
        listenBox.setPadding(new Insets(0, 10, 0, 10));
        listenBox.setSpacing(3);
        listenBox.setMaxHeight(25);
        listenBox.setMaxWidth(100);
        listenBox.setBackground(new Background(new BackgroundFill(Color.web("#1e2120"), new CornerRadii(20), null)));
        Label listenIcon = new Label();
        // 播放量小图标
        listenIcon.setPrefWidth(12);
        listenIcon.setPrefWidth(12);
        listenIcon.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        listenIcon.setStyle("-fx-shape: \"M512 128c-211.968 0-384 172.032-384 384v298.496c0 70.656 57.344 128 128 128h128v-341.504H213.504V512c0-164.864 133.632-298.496 298.496-298.496 164.864 0 298.496 133.632 298.496 298.496v85.504H640v341.504h128c70.656 0 128-57.344 128-128V512c0-211.968-172.032-384-384-384z\"");

        // 播放量
        Label listenLabel = new Label(NumberUtils.conversion(BigDecimal.valueOf(listenNum)));
        listenLabel.setTextFill(Color.WHITE);
        listenLabel.setFont(new Font(null, 12));
        listenBox.getChildren().addAll(listenIcon, listenLabel);
        StackPane.setMargin(listenBox, new Insets(0, 10, 10, 0));
        StackPane.setAlignment(listenBox, Pos.BOTTOM_RIGHT);

        // 播放按钮图片
        Label playLabel = new Label();
        playLabel.setId("play-label");
        playLabel.setPrefWidth(50);
        playLabel.setPrefHeight(50);
        playLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        playLabel.setStyle("-fx-shape: \"M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0z m216.992 541.128L404.96 728.208a33.6 33.6 0 0 1-50.456-29.128V324.928a33.6 33.6 0 0 1 50.456-29.136l324 187.08a33.6 33.6 0 0 1 0 58.256z m0 0\"\n");
        playLabel.setVisible(false);

        stackPane.getChildren().addAll(songImage, listenBox, playLabel);

        // 动画 当鼠标移动到歌单时 向上平移
        stackPane.setOnMouseEntered(event -> {
            playLabel.setVisible(true);
            listenBox.setVisible(false);
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(100),
                            new KeyValue(songImage.translateYProperty(), -10),
                            new KeyValue(playLabel.translateYProperty(), -10)));
            timeline.play();
        });

        stackPane.setOnMouseExited(event -> {
            playLabel.setVisible(false);
            listenBox.setVisible(true);
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(100),
                            new KeyValue(songImage.translateYProperty(), 0),
                            new KeyValue(playLabel.translateYProperty(), 0)
                    )
            );
            timeline.play();
        });


        playLabel.setOnMouseEntered(event -> playLabel.setBackground(new Background(new BackgroundFill(MusicConstant.MENU_SELECTED_COLOR, null, null))));

        playLabel.setOnMouseExited(event -> playLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))));

        // 歌单名
        Label title = new Label(titleText);
        title.setFont(new Font(null, 14));
        title.setPrefWidth(170);
        title.setPrefHeight(50);
        title.setWrapText(true);
        song.getChildren().addAll(stackPane, title);

        return song;
    }
}
