package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.MenuStyleAspect;
import com.durex.music.model.PaneType;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liugelong
 * @date 2022/9/5 09:31
 */
@Aspect(types = {MenuStyleAspect.class})
@Slf4j
public class MusicHallPagePane implements BasePagePane {


    @Override
    public Parent load(Object param) {
        return new Label("音乐馆");
    }

    @Override
    public PaneType getType() {
        return PaneType.MENU;
    }
}
