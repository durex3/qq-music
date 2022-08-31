package com.durex.music.service;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.qq.Banner;
import com.durex.music.model.qq.Song;
import com.durex.music.response.qq.RecommendBannerResp;
import com.durex.music.response.qq.RecommendPlayListResp;
import com.durex.music.utils.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author liugelong
 * @date 2022/8/17 09:27
 */
@Slf4j
public class RecommendService {

    private static final Random RANDOM = new Random();
    private static final List<Song> SONG_LIST = new ArrayList<>();


    private RecommendService() {
    }


    /**
     * <<h2>获取推荐歌单</h2>
     *
     * @return {@link  List<Song>}
     */
    public static List<Song> getRecommendSongList() {
        if (!SONG_LIST.isEmpty()) {
            return SONG_LIST;
        }

        int index = RANDOM.nextInt(MusicConstant.SONG_CATEGORY_RANDOM.size());
        final String id = MusicConstant.SONG_CATEGORY_RANDOM.get(index);
        String url = MusicConstant.QQ_MUSIC_HOST + "/recommend/playlist?id=" + id;
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            RecommendPlayListResp recommendPlayListResp = JsonMapper.string2Object(response.body(), new TypeReference<>() {
            });
            final List<Song> songList = recommendPlayListResp.getData().getList();
            SONG_LIST.addAll(songList);
        } catch (IOException | InterruptedException e) {
            log.error("获取推荐歌单失败: ", e);
        }

        return SONG_LIST;
    }

    /**
     * <h2>获取轮播图</h2>
     *
     * @return {@link List<Banner>}
     */
    public static List<Banner> getBannerList() {
        String url = MusicConstant.QQ_MUSIC_HOST + "/recommend/banner";
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            RecommendBannerResp recommendBannerResp = JsonMapper.string2Object(response.body(), new TypeReference<>() {
            });
            return recommendBannerResp.getData();
        } catch (IOException | InterruptedException e) {
            log.error("获取轮播图失败: ", e);
        }


        return Collections.emptyList();
    }
}
