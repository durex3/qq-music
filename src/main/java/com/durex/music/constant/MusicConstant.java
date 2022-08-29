package com.durex.music.constant;

import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liugelong
 * @date 2022/8/16 17:51
 */
public class MusicConstant {

    public static final String QQ_MUSIC_HOST = "http://yumbo.top:3300";

    public static final Color MENU_SELECTED_COLOR = Color.rgb(30, 207, 157);

    public static final CornerRadii MENU_CORNER_RADII = new CornerRadii(5);

    public static final String RECOMMEND_SONG_LIST_KEY = "recommend_song_list";

    public static final String IMAGE_PREFIX = "https://y.gtimg.cn/music/photo_new/T002R500x500M000%s.jpg?max_age=2592000";

    // 59：经典，71：情歌，3056：网络歌曲，64：KTV热歌
    public static final List<String> SONG_CATEGORY_RANDOM = Arrays.asList("59", "64", "71", "3056");

    private MusicConstant() {
    }
}
