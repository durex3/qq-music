package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.PagePaneAspect;
import com.durex.music.model.PaneType;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liugelong
 * @date 2022/9/5 09:31
 */
@Aspect(types = {PagePaneAspect.class})
@Slf4j
public class MusicHallPagePane implements BasePagePane {


    @Override
    public Parent load(Object param) {
        BorderPane borderPane = new BorderPane();
        final Label label = new Label("音乐馆-暂未实现");
        label.setFont(Font.font(36));
        borderPane.setCenter(label);
        return borderPane;
    }

    @Override
    public PaneType getType() {
        return PaneType.MENU;
    }
}
