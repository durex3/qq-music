package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.PagePaneAspect;
import com.durex.music.model.PaneType;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liugelong
 * @date 2022/9/5 09:50
 */
@Aspect(types = {PagePaneAspect.class})
@Slf4j
public class VideoPagePane implements BasePagePane {

    @Override
    public Parent load(Object param) {
        return new Label("视频");
    }

    @Override
    public PaneType getType() {
        return PaneType.MENU;
    }
}
