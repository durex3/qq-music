package com.durex.music.ui;

import com.durex.music.model.PaneType;
import javafx.scene.Parent;

public interface BasePagePane {

    Parent load(Object param);

    PaneType getType();
}
