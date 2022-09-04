package com.durex.music.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoadUIProxyHandler implements InvocationHandler {

    private final Object target;

    public LoadUIProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After invoke " + method.getName());

        return result;
    }
}
