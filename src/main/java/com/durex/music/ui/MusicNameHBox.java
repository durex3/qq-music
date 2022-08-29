package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.qq.Music;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/8/24 15:17
 */
public class MusicNameHBox {

    private MusicNameHBox() {
    }

    public static HBox build(Music music) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        Label nameLabel = new Label(music.getSongname());
        nameLabel.setId("name-label");
        nameLabel.setTextFill(Color.BLACK);
        hBox.getChildren().add(nameLabel);
        boolean vip = music.getPay().getPayplay() == 1;
        boolean canPlay = music.getMsgid() != 1 && music.getMsgid() != 3;
        if (vip) {
            StackPane stackPane = new StackPane();
            stackPane.setId("music-vip");
            Rectangle rectangle = new Rectangle(20, 12);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(MusicConstant.MENU_SELECTED_COLOR);
            Label vipLabel = new Label("vip");
            vipLabel.setFont(Font.font(10));
            vipLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
            stackPane.getChildren().addAll(rectangle, vipLabel);
            hBox.getChildren().add(stackPane);
        }

        if (!canPlay) {
            nameLabel.setTextFill(Color.web("#a6a6a6"));
        }
        return hBox;
    }
}
