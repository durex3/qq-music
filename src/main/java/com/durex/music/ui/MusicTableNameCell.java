package com.durex.music.ui;

import com.durex.music.model.bind.MusicProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @author liugelong
 * @date 2022/8/24 15:17
 */
public class MusicTableNameCell {

    private MusicTableNameCell() {
    }

    public static HBox build(MusicProperty music) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        Label nameLabel = new Label(music.getName().getText());
        if (music.isNotCanPlay()) {
            music.getName().setTextFill(Color.web("#a6a6a6"));
        }
        hBox.getChildren().add(nameLabel);
        nameLabel.textFillProperty().bind(music.getName().textFillProperty());
        if (music.isVip()) {
            hBox.getChildren().add(MusicVipPane.build());
        }

        return hBox;
    }
}
