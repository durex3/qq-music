package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.IAspect;
import com.durex.music.aspect.Ignore;
import com.durex.music.exception.MusicException;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.LinkedList;

/**
 * <h1>面板工厂</h1>
 */
public class PaneProxy {

    private PaneProxy() {

    }

    public static <T> T newInstance(Class<T> clazz) {
        final Annotation[] annotations = clazz.getAnnotations();
        final LinkedList<IAspect> aspectLinkedList = new LinkedList<>();

        try {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Aspect aspect) {
                    final Class<?>[] types = aspect.types();
                    for (Class<?> type : types) {
                        final IAspect iAspect = (IAspect) (type.getConstructor().newInstance());
                        aspectLinkedList.push(iAspect);
                    }
                }
            }

            T instance = clazz.getConstructor().newInstance();
            T t = (T) Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    clazz.getInterfaces(),
                    (proxy, method, args) -> {
                        Object result;
                        if (!method.isAnnotationPresent(Ignore.class)) {
                            aspectLinkedList.forEach(aspect -> aspect.before(instance, args));
                        }
                        result = method.invoke(instance, args);
                        if (!method.isAnnotationPresent(Ignore.class)) {
                            aspectLinkedList.forEach(aspect -> aspect.after(instance, result, args));
                        }
                        aspectLinkedList.clear();
                        return result;
                    }
            );
            return new WeakReference<>(t).get();

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new MusicException(e);
        }
    }
}
