package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.MusicProperty;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/9/2 15:07
 */
public class MusicPane {

    private MusicPane() {
    }

    public static VBox build(MusicProperty music) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        // 歌曲图片
        HBox musicInfoHBox = new HBox();
        musicInfoHBox.setAlignment(Pos.CENTER_LEFT);
        musicInfoHBox.setSpacing(10);
        String mid = music.getAlbummid();
        String imageUrl = String.format(MusicConstant.IMAGE_PREFIX, mid);
        RXAvatar musicImage = new RXAvatar(new Image(imageUrl, 65, 65, false, false, true));
        musicImage.setShapeType(RXAvatar.Type.SQUARE);
        musicImage.setPrefWidth(65);
        musicImage.setPrefWidth(65);
        musicImage.setMaxWidth(65);
        musicImage.setMaxHeight(65);

        // 歌曲名和歌手信息
        VBox musicNameAndSingerVBox = new VBox();
        musicNameAndSingerVBox.setAlignment(Pos.CENTER_LEFT);

        // 歌曲名
        HBox musicNameHBox = new HBox();
        musicNameHBox.setSpacing(5);
        final Label musicNameLabel = new Label(music.getName().getText());
        musicNameLabel.setFont(Font.font(14));
        musicNameLabel.setMaxWidth(280);
        musicNameLabel.textFillProperty().bind(music.getName().textFillProperty());
        musicNameHBox.getChildren().add(musicNameLabel);

        Label singerLabel = new Label(music.getSinger().getText());
        singerLabel.setFont(Font.font(14));
        singerLabel.setMaxWidth(280);

        singerLabel.setTextFill(Color.web("#777777"));
        musicNameAndSingerVBox.getChildren().addAll(musicNameHBox, singerLabel);

        musicInfoHBox.getChildren().addAll(musicImage, musicNameAndSingerVBox);

        vBox.getChildren().add(musicInfoHBox);

        return vBox;
    }
}
