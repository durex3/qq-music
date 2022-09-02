package com.durex.music.ui;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.MusicProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author liugelong
 * @date 2022/9/2 08:51
 */
public class MusicNameTableCell extends TableCell<MusicProperty, Label> {

    private final HBox hBox;
    private final StackPane stackPane;

    public MusicNameTableCell() {
        hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(20, 12);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(MusicConstant.MENU_SELECTED_COLOR);

        Label vipLabel = new Label("vip");
        vipLabel.setFont(Font.font(10));

        vipLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
        stackPane.getChildren().addAll(rectangle, vipLabel);
    }

    @Override
    protected void updateItem(Label nameLabel, boolean empty) {
        super.updateItem(nameLabel, empty);
        if (nameLabel != null && !empty) {
            final MusicProperty music = this.getTableView().getItems().get(this.getIndex());
            nameLabel.setTextFill(music.getName().getTextFill());
            if (!hBox.getChildren().contains(nameLabel)) {
                hBox.getChildren().add(nameLabel);
            }
            if (!hBox.getChildren().contains(stackPane) && music.isVip()) {
                hBox.getChildren().add(stackPane);
            }
            if (music.isNotCanPlay()) {
                nameLabel.setTextFill(Color.web("#a6a6a6"));
            }

            this.setGraphic(hBox);
        }
    }
}
