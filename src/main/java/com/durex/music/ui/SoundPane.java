package com.durex.music.ui;

import com.durex.music.model.MusicPlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * @author liugelong
 * @date 2022/8/30 16:58
 */
@Slf4j
public class SoundPane {

    private SoundPane() {
    }

    public static synchronized AnchorPane load() {
        // 加载声音面板
        try {
            final AnchorPane soundPane = FXMLLoader.load(Objects.requireNonNull(SoundPane.class.getResource("/fxml/sound.fxml")));

            Slider soundSlider = (Slider) soundPane.lookup(".sound-slider");
            // 双向绑定
            soundSlider.valueProperty().bindBidirectional(MusicPlayer.getCurrentSoundValue());
            Label soundNumLabel = (Label) soundPane.lookup(".sound-num");
            soundNumLabel.textProperty().bind(MusicPlayer.getCurrentSoundValue().asString("%.0f%%"));
            // 声音滑块改变时,改变player的音量
            soundSlider.valueProperty().addListener((ob, ov, nv) -> {
                if (MusicPlayer.getPlayer() != null) {
                    MusicPlayer.getPlayer().setVolume(nv.doubleValue() / 100);
                }
            });
            return soundPane;
        } catch (IOException e) {
            log.error("加载声音面板失败: ", e);
        }
        return null;
    }
}
