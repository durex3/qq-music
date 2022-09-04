package com.durex.music.ui;

import javafx.scene.Parent;

public interface BasePagePane<T> {

    Parent load(T param);
}
