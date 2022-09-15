package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.utils.TimeUtils;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/8/25 16:04
 */
public class MusicPlayListCell extends ListCell<MusicProperty> {

    private final AnchorPane anchorPane = new AnchorPane();
    private final RXAvatar musicImage;
    private final HBox musicNameHBox;
    private final Label musicNameLabel;
    private final Label singerLabel;
    private final Label musicDuration;
    private StackPane vipPane;

    public MusicPlayListCell() {

        HBox musicInfoHBox = new HBox();
        musicInfoHBox.setPrefHeight(50);
        musicInfoHBox.setAlignment(Pos.CENTER_LEFT);
        musicInfoHBox.setSpacing(10);
        AnchorPane.setLeftAnchor(musicInfoHBox, 0.0);

        // 歌曲图片
        musicImage = new RXAvatar();
        musicImage.setShapeType(RXAvatar.Type.SQUARE);
        musicImage.setPrefWidth(40);
        musicImage.setPrefWidth(40);
        musicImage.setMaxWidth(40);
        musicImage.setMaxHeight(40);

        // 歌曲名和歌手信息
        VBox musicNameAndSingerVBox = new VBox();
        musicNameAndSingerVBox.setAlignment(Pos.CENTER_LEFT);

        musicNameHBox = new HBox();
        musicNameHBox.setSpacing(5);

        musicNameLabel = new Label();
        musicNameLabel.setMaxWidth(100);

        singerLabel = new Label();
        singerLabel.setMaxWidth(150);
        singerLabel.setFont(Font.font(14));

        musicNameHBox.getChildren().add(musicNameLabel);
        musicNameAndSingerVBox.getChildren().addAll(musicNameHBox, singerLabel);
        musicInfoHBox.getChildren().addAll(musicImage, musicNameAndSingerVBox);

        HBox musicDurationHBox = new HBox();
        musicDurationHBox.setPrefHeight(50);
        musicDurationHBox.setAlignment(Pos.CENTER_LEFT);
        musicDuration = new Label();
        musicDurationHBox.getChildren().add(musicDuration);
        AnchorPane.setRightAnchor(musicDurationHBox, 0.0);

        anchorPane.getChildren().addAll(musicInfoHBox, musicDurationHBox);

    }

    @Override
    protected void updateItem(MusicProperty music, boolean empty) {
        super.updateItem(music, empty);
        if (empty || music == null) {
            musicNameLabel.textFillProperty().unbind();
            singerLabel.textFillProperty().unbind();
            musicDuration.textFillProperty().unbind();

            this.setGraphic(null);
            this.setText(null);
            return;
        }

        if (musicImage.getImage() == null) {
            String mid = music.getAlbummid();
            String imageUrl = String.format(MusicConstant.IMAGE_PREFIX, mid);
            musicImage.setImage(new Image(imageUrl, 40, 40, false, false, true));
        }

        musicNameLabel.setText(music.getName().getText());
        if (!musicNameLabel.textFillProperty().isBound()) {
            musicNameLabel.textFillProperty().bind(music.getName().textFillProperty());
        }

        if (!musicNameHBox.getChildren().contains(vipPane) && music.isVip()) {
            vipPane = MusicVipPane.build();
            musicNameHBox.getChildren().add(vipPane);
        }
        singerLabel.setText(music.getSinger().getText());
        if (!singerLabel.textFillProperty().isBound()) {
            singerLabel.textFillProperty().bind(music.getSinger().textFillProperty());
        }

        musicDuration.setText(TimeUtils.format(Double.valueOf(music.getInterval())));
        if (!musicDuration.textFillProperty().isBound()) {
            musicDuration.textFillProperty().bind(music.getSinger().textFillProperty());
        }

        this.setGraphic(anchorPane);
    }
}
