package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.IAspect;
import com.durex.music.exception.MusicException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.LinkedList;

/**
 * <h1>面板工厂</h1>
 */
public class PaneFactory {

    private PaneFactory() {

    }

    public static <T> T newInstance(Class<T> clazz) {
        final Annotation[] annotations = clazz.getAnnotations();
        final LinkedList<IAspect> aspectLinkedList = new LinkedList<>();

        try {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Aspect) {
                    final Class<?>[] types = ((Aspect) annotation).types();
                    for (Class<?> type : types) {
                        final IAspect iAspect = (IAspect) (type.getConstructor().newInstance());
                        aspectLinkedList.push(iAspect);
                    }
                }
            }

            T instance = clazz.getConstructor().newInstance();
            return (T) Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    clazz.getInterfaces(),
                    (proxy, method, args) -> {
                        aspectLinkedList.forEach(aspect -> aspect.before(args));
                        final Object result = method.invoke(instance, args);
                        aspectLinkedList.forEach(aspect -> aspect.after(args));
                        return result;
                    }
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new MusicException(e);
        }
    }
}
