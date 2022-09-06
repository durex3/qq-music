package com.durex.music.aspect;

import com.durex.music.model.HistoryStack;
import com.durex.music.ui.BasePagePane;
import com.durex.music.ui.MainPane;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * @author liugelong
 * @date 2022/9/6 08:48
 */
public class PagePaneAspect implements IAspect {

    @Override
    public void before(Object instance, Object... args) {
        if (args[0] != null && args[0] instanceof Pane pane) {
            MainPane.setMenuStyle(pane);
        } else {
            MainPane.resetMenuStyle();
        }
    }

    @Override
    public void after(Object instance, Object result, Object... args) {
        if (instance instanceof BasePagePane pane && result instanceof Parent) {
            HistoryStack.History history = new HistoryStack.History();
            if (args[0] != null && args[0] instanceof Pane menu) {
                history.setMenu(menu);
            }
            history.setPagePane(pane);
            history.setParam(args[0]);
            HistoryStack.push(history);
        }
    }
}
