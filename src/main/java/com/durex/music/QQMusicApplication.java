package com.durex.music;

import com.durex.music.ui.MainPane;
import com.durex.music.utils.ResizeUtils;
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
        stage.initStyle(StageStyle.UNDECORATED);
        ResizeUtils.addResizable(stage);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/logo.png"))));
        stage.show();
    }
}
