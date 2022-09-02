package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/9/2 11:44
 */
public class MusicVipPane {

    private MusicVipPane() {
    }

    public static StackPane build() {
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(20, 12);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(MusicConstant.MENU_SELECTED_COLOR);
        Label vipLabel = new Label("vip");
        vipLabel.setFont(Font.font(10));
        vipLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
        stackPane.getChildren().addAll(rectangle, vipLabel);
        return stackPane;
    }
}
