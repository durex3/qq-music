package com.durex.music.ui;

import com.durex.music.controller.SongDetailController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class SongDetailPane {

    private SongDetailPane() {

    }

    public static synchronized Parent load(String songId) {
        final FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(SongDetailPane.class.getResource("/fxml/song-detail.fxml")));

        fxmlLoader.setControllerFactory(c -> new SongDetailController(songId));
        Parent songDetail = null;
        try {
            songDetail = fxmlLoader.load();
        } catch (IOException e) {
            log.error("加载歌单详情页面失败: ", e);
        }
        return songDetail;
    }
}
