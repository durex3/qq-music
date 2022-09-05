package com.durex.music.aspect;

/**
 * <h1>切面接口</h1>
 *
 * @author liugelong
 * @date 2022/9/5 09:07
 */
public interface IAspect {

    default void before(Object instance, Object... args) {

    }

    default void after(Object instance, Object... args) {

    }
}
