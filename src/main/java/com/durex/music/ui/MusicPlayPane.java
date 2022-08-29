package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.utils.TimeUtils;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/8/25 16:04
 */
public class MusicPlayPane {

    private MusicPlayPane() {

    }

    public static AnchorPane build(MusicProperty music) {

        AnchorPane anchorPane = new AnchorPane();

        // 歌曲图片
        HBox musicInfoHBox = new HBox();
        musicInfoHBox.setPrefHeight(50);
        musicInfoHBox.setAlignment(Pos.CENTER_LEFT);
        musicInfoHBox.setSpacing(10);
        String mid = music.getAlbummid();
        String imageUrl = String.format(MusicConstant.IMAGE_PREFIX, mid);
        RXAvatar musicImage = new RXAvatar(new Image(imageUrl, 40, 40, false, false, true));
        musicImage.setShapeType(RXAvatar.Type.SQUARE);
        musicImage.setPrefWidth(40);
        musicImage.setPrefWidth(40);
        musicImage.setMaxWidth(40);
        musicImage.setMaxHeight(40);

        // 歌曲名和歌手信息
        VBox musicNameAndSingerVBox = new VBox();
        musicNameAndSingerVBox.setAlignment(Pos.CENTER_LEFT);

        // 歌曲名
        HBox musicNameHBox = new HBox();
        musicNameHBox.setSpacing(5);
        final Label nameLabel = (Label) music.getName().lookup("#name-label");
        final Label musicNameLabel = new Label(nameLabel.getText());
        musicNameLabel.setId("play-list-music-name");
        musicNameLabel.setTextFill(Color.BLACK);
        musicNameLabel.setMaxWidth(100);
        musicNameHBox.getChildren().add(musicNameLabel);

        if (music.isVip()) {
            StackPane stackPane = new StackPane();
            stackPane.setId("music-vip");
            Rectangle rectangle = new Rectangle(20, 12);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(MusicConstant.MENU_SELECTED_COLOR);
            Label vipLabel = new Label("vip");
            vipLabel.setFont(Font.font(10));
            vipLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
            stackPane.getChildren().addAll(rectangle, vipLabel);
            musicNameHBox.getChildren().add(stackPane);
        }
        if (music.isNotCanPlay()) {
            musicNameLabel.setTextFill(Color.web("#a6a6a6"));
        }


        Label singerLabel = new Label(music.getSinger().getText());
        singerLabel.setMaxWidth(150);
        singerLabel.setId("play-list-singer");
        singerLabel.setFont(Font.font(14));
        singerLabel.setTextFill(Color.BLACK);
        musicNameAndSingerVBox.getChildren().addAll(musicNameHBox, singerLabel);

        musicInfoHBox.getChildren().addAll(musicImage, musicNameAndSingerVBox);
        AnchorPane.setLeftAnchor(musicInfoHBox, 0.0);

        HBox musicDurationHBox = new HBox();
        musicDurationHBox.setPrefHeight(50);
        musicDurationHBox.setAlignment(Pos.CENTER_LEFT);
        Label musicDuration = new Label(TimeUtils.format(Double.valueOf(music.getInterval())));
        musicDuration.setId("play-list-music-duration");
        musicDuration.setTextFill(Color.BLACK);
        musicDurationHBox.getChildren().add(musicDuration);
        AnchorPane.setRightAnchor(musicDurationHBox, 0.0);

        anchorPane.getChildren().addAll(musicInfoHBox, musicDurationHBox);
        return anchorPane;
    }
}
