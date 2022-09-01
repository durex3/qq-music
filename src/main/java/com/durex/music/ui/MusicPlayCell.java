package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.MusicProperty;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * @author liugelong
 * @date 2022/9/1 17:36
 */
public class MusicPlayCell extends ListCell<MusicProperty> {

    private final AnchorPane anchorPane;
    private final HBox musicInfoHBox;
    private final HBox musicDurationHBox;
    RXAvatar musicImage;


    public MusicPlayCell() {
        anchorPane = new AnchorPane();
        musicInfoHBox = new HBox();
        musicInfoHBox.setPrefHeight(50);
        musicInfoHBox.setAlignment(Pos.CENTER_LEFT);
        musicInfoHBox.setSpacing(10);
        musicDurationHBox = new HBox();
        musicDurationHBox.setPrefHeight(50);
        musicDurationHBox.setAlignment(Pos.CENTER_LEFT);
        anchorPane.getChildren().addAll(musicInfoHBox, musicDurationHBox);
        AnchorPane.setLeftAnchor(musicInfoHBox, 0.0);
        AnchorPane.setRightAnchor(musicDurationHBox, 0.0);
        musicImage = new RXAvatar();
        musicImage.setShapeType(RXAvatar.Type.SQUARE);
        musicImage.setPrefWidth(40);
        musicImage.setPrefWidth(40);
        musicImage.setMaxWidth(40);
        musicImage.setMaxHeight(40);
        musicInfoHBox.getChildren().addAll(musicImage);
    }

    @Override
    protected void updateItem(MusicProperty music, boolean empty) {
        super.updateItem(music, empty);
        if (music == null || empty) {
            setText("");
            this.setGraphic(null);
        } else {
            musicInfoHBox.getChildren().removeAll();
            musicDurationHBox.getChildren().removeAll();
            // 歌曲图片
            String mid = music.getAlbummid();
            String imageUrl = String.format(MusicConstant.IMAGE_PREFIX, mid);
            final Image image = new Image(imageUrl, 40, 40, false, false, true);
            musicImage.setImage(image);

//            // 歌曲名和歌手信息
//            VBox musicNameAndSingerVBox = new VBox();
//            musicNameAndSingerVBox.setAlignment(Pos.CENTER_LEFT);
//
//            // 歌曲名
//            HBox musicNameHBox = new HBox();
//            musicNameHBox.setSpacing(5);
//            final Label musicNameLabel = new Label(music.getName().getText());
//            musicNameLabel.setMaxWidth(100);
//            musicNameLabel.textFillProperty().bind(music.getName().textFillProperty());
//            musicNameHBox.getChildren().add(musicNameLabel);
//
//            if (music.isVip()) {
//                StackPane stackPane = new StackPane();
//                Rectangle rectangle = new Rectangle(20, 12);
//                rectangle.setFill(Color.WHITE);
//                rectangle.setStroke(MusicConstant.MENU_SELECTED_COLOR);
//                Label vipLabel = new Label("vip");
//                vipLabel.setFont(Font.font(10));
//                vipLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
//                stackPane.getChildren().addAll(rectangle, vipLabel);
//                musicNameHBox.getChildren().add(stackPane);
//            }
//
//            Label singerLabel = new Label(music.getSinger().getText());
//            singerLabel.setMaxWidth(150);
//            singerLabel.setFont(Font.font(14));
//            singerLabel.textFillProperty().bind(music.getSinger().textFillProperty());
//
//            musicNameAndSingerVBox.getChildren().addAll(musicNameHBox, singerLabel);
//
//
//
//            Label musicDuration = new Label(TimeUtils.format(Double.valueOf(music.getInterval())));
//            musicDuration.textFillProperty().bind(music.getDuration().textFillProperty());
//            musicDurationHBox.getChildren().add(musicDuration);

            this.setGraphic(anchorPane);
        }
    }
}
