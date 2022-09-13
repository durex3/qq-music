package com.durex.music.ui;

import com.durex.music.model.bind.MusicProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

/**
 * @author liugelong
 * @date 2022/9/2 08:51
 */
public class MusicNameTableCell extends TableCell<MusicProperty, Label> {

    private final StackPane stackPane;
    private final HBox musicNameHBox;
    private final Label playBtn;
    private StackPane vipStackPane;

    public MusicNameTableCell() {
        stackPane = new StackPane();
        musicNameHBox = new HBox();
        musicNameHBox.setSpacing(5);
        musicNameHBox.setAlignment(Pos.CENTER_LEFT);

        // 播放下载等按钮
        HBox toolHBox = new HBox();
        toolHBox.setMaxWidth(100);
        toolHBox.setSpacing(5);
        toolHBox.setAlignment(Pos.CENTER_LEFT);
        toolHBox.setBackground(new Background(new BackgroundFill(Color.rgb(235, 235, 235), null, null)));
        final SVGPath svgPath = new SVGPath();
        svgPath.setContent("M224 938.713333a53.58 53.58 0 0 1-53.333333-53.433333V138.72a53.333333 53.333333 0 0 1 80.886666-45.666667l618.666667 373.28a53.333333 53.333333 0 0 1 0 91.333334l-618.666667 373.28a53.16 53.16 0 0 1-27.553333 7.766666z m0.046667-810.666666a10.98 10.98 0 0 0-5.333334 1.42 10.466667 10.466667 0 0 0-5.38 9.253333v746.56a10.666667 10.666667 0 0 0 16.18 9.133333l618.666667-373.28a10.666667 10.666667 0 0 0 0-18.266666l-618.666667-373.28a10.386667 10.386667 0 0 0-5.446666-1.586667z");
        playBtn = new Label();
        playBtn.setPrefWidth(15);
        playBtn.setMaxWidth(15);
        playBtn.setMaxHeight(20);
        playBtn.setShape(svgPath);
        playBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        toolHBox.getChildren().add(playBtn);
        StackPane.setAlignment(toolHBox, Pos.CENTER_RIGHT);

        stackPane.getChildren().addAll(musicNameHBox, toolHBox);
    }

    @Override
    protected void updateItem(Label nameLabel, boolean empty) {
        super.updateItem(nameLabel, empty);
        if (empty || nameLabel == null) {
            this.setGraphic(null);
            this.setText(null);
            playBtn.backgroundProperty().unbind();
            return;
        }

        final MusicProperty music = this.getTableView().getItems().get(this.getIndex());
        nameLabel.setTextFill(music.getName().getTextFill());
        if (!musicNameHBox.getChildren().contains(nameLabel)) {
            musicNameHBox.getChildren().add(nameLabel);
        }
        if (!musicNameHBox.getChildren().contains(vipStackPane) && music.isVip()) {
            vipStackPane = MusicVipPane.build();
            musicNameHBox.getChildren().add(vipStackPane);
        }
        if (music.isNotCanPlay()) {
            nameLabel.setTextFill(Color.web("#a6a6a6"));
        }

        if (!playBtn.backgroundProperty().isBound()) {
            playBtn.backgroundProperty().bind(music.backgroundProperty());
        }

        this.setGraphic(stackPane);
    }
}
