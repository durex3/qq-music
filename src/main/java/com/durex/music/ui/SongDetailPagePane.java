package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.PagePaneAspect;
import com.durex.music.controller.SongDetailController;
import com.durex.music.model.PaneType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Aspect(types = {PagePaneAspect.class})
@Slf4j
public class SongDetailPagePane implements BasePagePane {

    @Override
    public Parent load(Object param) {
        final FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(SongDetailPagePane.class.getResource("/fxml/song-detail.fxml")));

        fxmlLoader.setControllerFactory(c -> new SongDetailController(param.toString()));

        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            log.error("加载歌单详情页面失败: ", e);
        }
        return null;
    }

    @Override
    public PaneType getType() {
        return PaneType.OTHER;
    }
}
