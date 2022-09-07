package com.durex.music;

import com.durex.music.ui.MainPane;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 *
 */
public class QQMusicApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainPane.load(stage);
        stage.setMinWidth(1020);
        stage.setMinHeight(690);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/logo.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(QQMusicApplication.class, args);
    }
}
