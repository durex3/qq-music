package com.durex.music.proxy;

import com.durex.music.model.PaneType;
import com.durex.music.ui.BasePagePane;
import com.durex.music.ui.MainPane;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoadUIProxyHandler implements InvocationHandler {

    private final Object target;

    public LoadUIProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        BasePagePane pane = (BasePagePane) target;
        if (pane.getType() == PaneType.MENU && args[0] != null) {
            MainPane.setMenuStyle((Pane) args[0]);
        }

        return method.invoke(target, args);
    }
}
