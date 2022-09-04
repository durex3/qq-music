package com.durex.music.ui;

import com.durex.music.proxy.LoadUIProxyHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class PaneFactory {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    clazz.getInterfaces(),
                    new LoadUIProxyHandler(instance)
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
