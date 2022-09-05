package com.durex.music.aspect;

import com.durex.music.ui.MainPane;
import javafx.scene.layout.Pane;

/**
 * @author liugelong
 * @date 2022/9/5 09:14
 */
public class MenuStyleAspect implements IAspect {

    @Override
    public void before(Object instance, Object... args) {
        if (args[0] != null) {
            MainPane.setMenuStyle((Pane) args[0]);
        }
    }
}
