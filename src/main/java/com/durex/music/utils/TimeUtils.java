package com.durex.music.utils;

public class TimeUtils {

    private TimeUtils() {
    }

    /**
     * 将秒转换成几时几分几秒 (2:9:20)
     */
    public static String format(Double second) {
        String time = "0";
        if (second != null) {
            String format;
            int hours = (int) (second / (60 * 60));
            int minutes = (int) (second / 60 - hours * 60);
            int seconds = (int) (second - minutes * 60 - hours * 60 * 60);
            if (hours > 0) {
                format = "%02d:%02d:%02d";
                time = String.format(format, hours, minutes, seconds);
            } else {
                format = "%02d:%02d";
                time = String.format(format, minutes, seconds);
            }
        }
        return time;

    }
}
