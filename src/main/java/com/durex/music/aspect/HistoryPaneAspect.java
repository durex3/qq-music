package com.durex.music.aspect;

import com.durex.music.model.HistoryStack;
import com.durex.music.ui.BasePagePane;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * @author liugelong
 * @date 2022/9/5 10:02
 */
public class HistoryPaneAspect implements IAspect {

    @Override
    public void after(Object instance, Object result, Object... args) {
        if (instance instanceof BasePagePane && result instanceof Parent node) {
            HistoryStack.History history = new HistoryStack.History();
            if (args[0] != null && args[0] instanceof Pane menu) {
                history.setMenu(menu);
            }
            history.setNode(node);
            HistoryStack.push(history);
        }
    }
}
