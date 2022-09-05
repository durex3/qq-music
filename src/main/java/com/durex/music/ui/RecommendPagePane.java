package com.durex.music.ui;

import com.durex.music.aspect.Aspect;
import com.durex.music.aspect.MenuStyleAspect;
import com.durex.music.model.PaneType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * @author liugelong
 * @date 2022/9/2 14:06
 */
@Aspect(types = {MenuStyleAspect.class})
@Slf4j
public class RecommendPagePane implements BasePagePane {

    /**
     * 缓存单个实例
     */
    private static AnchorPane instance = null;

    @Override
    public synchronized Parent load(Object param) {
        if (instance != null) {
            return instance;
        }
        try {
            instance = FXMLLoader.load(Objects.requireNonNull(MainPane.class.getResource("/fxml/recommend.fxml")));
        } catch (IOException e) {
            log.error("加载推荐面板失败: ", e);
        }
        return instance;
    }

    @Override
    public PaneType getType() {
        return PaneType.MENU;
    }
}
