package com.durex.music.model;

import com.durex.music.ui.BasePagePane;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liugelong
 * @date 2022/9/5 15:39
 */
public class HistoryStack {

    private static final int STACK_SIZE = 10;

    private static final SimpleBooleanProperty BACK_STACK_IS_EMPTY = new SimpleBooleanProperty(true);

    private static final SimpleBooleanProperty FORWARD_STACK_IS_EMPTY = new SimpleBooleanProperty(true);

    private static final Deque<History> BACK_STACK = new ArrayDeque<>(STACK_SIZE);

    private static final Deque<History> FORWARD_STACK = new ArrayDeque<>(STACK_SIZE);

    private HistoryStack() {
    }

    public static History forward() {
        if (BACK_STACK.size() == STACK_SIZE) {
            BACK_STACK.removeLast();
        }

        History node = FORWARD_STACK.pop();

        BACK_STACK.push(node);
        BACK_STACK_IS_EMPTY.set(false);


        if (FORWARD_STACK.isEmpty()) {
            FORWARD_STACK_IS_EMPTY.set(true);
        }

        return node;
    }

    public static History back() {
        if (FORWARD_STACK.size() == STACK_SIZE) {
            FORWARD_STACK.removeLast();
        }
        if (BACK_STACK.size() == 1) {
            return BACK_STACK.peek();
        } else {
            FORWARD_STACK.push(BACK_STACK.pop());
            FORWARD_STACK_IS_EMPTY.set(false);
            if (BACK_STACK.size() == 1) {
                BACK_STACK_IS_EMPTY.set(true);
            }
        }

        return BACK_STACK.peek();
    }

    public static void push(History history) {
        if (BACK_STACK.size() == STACK_SIZE) {
            BACK_STACK.removeLast();
        }
        BACK_STACK.push(history);
        if (BACK_STACK.size() > 1) {
            BACK_STACK_IS_EMPTY.set(false);
        }

        if (!FORWARD_STACK.isEmpty()) {
            FORWARD_STACK.clear();
            FORWARD_STACK_IS_EMPTY.set(true);
        }
    }

    public static class History {

        private Pane menu;
        private BasePagePane pagePane;
        private Object param;

        public Pane getMenu() {
            return menu;
        }

        public void setMenu(Pane menu) {
            this.menu = menu;
        }

        public BasePagePane getPagePane() {
            return pagePane;
        }

        public void setPagePane(BasePagePane pagePane) {
            this.pagePane = pagePane;
        }

        public Object getParam() {
            return param;
        }

        public void setParam(Object param) {
            this.param = param;
        }
    }

    public static SimpleBooleanProperty getBackStackIsEmpty() {
        return BACK_STACK_IS_EMPTY;
    }

    public static SimpleBooleanProperty getForwardStackIsEmpty() {
        return FORWARD_STACK_IS_EMPTY;
    }
}
