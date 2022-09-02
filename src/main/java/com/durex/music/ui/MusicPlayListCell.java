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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/8/25 16:04
 */
public class MusicPlayListCell {

    private MusicPlayListCell() {

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
        final Label musicNameLabel = new Label(music.getName().getText());
        musicNameLabel.setMaxWidth(100);
        musicNameLabel.textFillProperty().bind(music.getName().textFillProperty());
        musicNameHBox.getChildren().add(musicNameLabel);

        if (music.isVip()) {
            musicNameHBox.getChildren().add(MusicVipPane.build());
        }

        Label singerLabel = new Label(music.getSinger().getText());
        singerLabel.setMaxWidth(150);
        singerLabel.setFont(Font.font(14));
        singerLabel.textFillProperty().bind(music.getSinger().textFillProperty());
        musicNameAndSingerVBox.getChildren().addAll(musicNameHBox, singerLabel);

        musicInfoHBox.getChildren().addAll(musicImage, musicNameAndSingerVBox);
        AnchorPane.setLeftAnchor(musicInfoHBox, 0.0);

        HBox musicDurationHBox = new HBox();
        musicDurationHBox.setPrefHeight(50);
        musicDurationHBox.setAlignment(Pos.CENTER_LEFT);
        Label musicDuration = new Label(TimeUtils.format(Double.valueOf(music.getInterval())));
        musicDuration.textFillProperty().bind(music.getDuration().textFillProperty());
        musicDurationHBox.getChildren().add(musicDuration);
        AnchorPane.setRightAnchor(musicDurationHBox, 0.0);

        anchorPane.getChildren().addAll(musicInfoHBox, musicDurationHBox);
        return anchorPane;
    }
}
