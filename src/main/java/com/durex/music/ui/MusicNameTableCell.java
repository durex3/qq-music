package com.durex.music.ui;

import com.durex.music.model.bind.MusicProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @author liugelong
 * @date 2022/9/2 08:51
 */
public class MusicNameTableCell extends TableCell<MusicProperty, Label> {

    private final StackPane stackPane;
    private final HBox musicNameHBox;
    private StackPane vipStackPane;

    public MusicNameTableCell() {
        stackPane = new StackPane();
        musicNameHBox = new HBox();
        musicNameHBox.setSpacing(5);
        musicNameHBox.setAlignment(Pos.CENTER_LEFT);

        stackPane.getChildren().add(musicNameHBox);
    }

    @Override
    protected void updateItem(Label nameLabel, boolean empty) {
        super.updateItem(nameLabel, empty);
        if (empty || nameLabel == null) {
            this.setGraphic(null);
            this.setText(null);
            return;
        }

        final MusicProperty music = this.getTableView().getItems().get(this.getIndex());
        nameLabel.setTextFill(music.getName().getTextFill());
        if (!musicNameHBox.getChildren().contains(nameLabel)) {
            musicNameHBox.getChildren().add(nameLabel);
        }
        if (!musicNameHBox.getChildren().contains(vipStackPane) && music.isVip()) {
            vipStackPane = MusicVipPane.createInstance();
            musicNameHBox.getChildren().add(vipStackPane);
        }
        if (music.isNotCanPlay()) {
            nameLabel.setTextFill(Color.web("#a6a6a6"));
        }

        this.setGraphic(stackPane);
    }
}
