package com.durex.music.ui.page;

import com.durex.music.aspect.Ignore;
import com.durex.music.model.PaneType;
import javafx.scene.Parent;

public interface BasePagePane {

    Parent load(Object param);

    @Ignore
    PaneType getType();
}
