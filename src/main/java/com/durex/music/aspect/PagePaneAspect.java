package com.durex.music.aspect;

import com.durex.music.model.HistoryStack;
import com.durex.music.model.PaneType;
import com.durex.music.ui.page.BasePagePane;
import com.durex.music.ui.page.MainPane;
import javafx.scene.layout.Pane;

/**
 * @author liugelong
 * @date 2022/9/6 08:48
 */
public class PagePaneAspect implements IAspect {

    @Override
    public void before(Object instance, Object... args) {
        if (instance instanceof BasePagePane pane && pane.getType() == PaneType.MENU) {
            MainPane.setMenuStyle((Pane) args[0]);
        } else {
            MainPane.resetMenuStyle();
        }
    }

    @Override
    public void after(Object instance, Object result, Object... args) {
        if (instance instanceof BasePagePane pane) {
            HistoryStack.History history = new HistoryStack.History();
            history.setPagePane(pane);
            history.setParam(args[0]);
            HistoryStack.push(history);
        }
        MainPane.getScrollPane().setVvalue(0);
    }
}
