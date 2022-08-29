package com.durex.music.model.bind;

import com.durex.music.utils.TimeUtils;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author liugelong
 * @date 2022/8/22 11:42
 */
public class CurrPlaySecondsBinding extends StringBinding {
    private final ReadOnlyObjectProperty<Duration> currentTime;


    public CurrPlaySecondsBinding(ReadOnlyObjectProperty<Duration> currentTime) {
        this.currentTime = currentTime;
        this.bind(currentTime);
    }

    @Override
    protected String computeValue() {
        double seconds = currentTime.get().toSeconds();
        seconds = BigDecimal.valueOf(seconds).setScale(0, RoundingMode.HALF_UP).doubleValue();
        return TimeUtils.format(seconds);
    }

    public Duration getCurrentTime() {
        return currentTime.get();
    }

    public ReadOnlyObjectProperty<Duration> currentTimeProperty() {
        return currentTime;
    }
}
