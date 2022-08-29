package com.durex.music.service;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.qq.Banner;
import com.durex.music.model.qq.Song;
import com.durex.music.response.qq.RecommendBannerResp;
import com.durex.music.response.qq.RecommendPlayListResp;
import com.durex.music.utils.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liugelong
 * @date 2022/8/17 09:27
 */
@Slf4j
public class RecommendService {

    private static final Random RANDOM = new Random();

    private static final Cache<String, String> CACHE = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS)
            .expireAfterAccess(1, TimeUnit.DAYS)
            .maximumSize(10)
            .build();

    private RecommendService() {
    }


    /**
     * <<h2>获取推荐歌单</h2>
     *
     * @return {@link  List<Song>}
     */
    public static List<Song> getRecommendSongList() {

        final String value = CACHE.getIfPresent(MusicConstant.RECOMMEND_SONG_LIST_KEY);
        if (value != null && !value.isBlank()) {
            return JsonMapper.string2Object(value, new TypeReference<>() {
            });
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
            CACHE.put(MusicConstant.RECOMMEND_SONG_LIST_KEY, JsonMapper.object2String(songList));
            return songList;
        } catch (IOException | InterruptedException e) {
            log.error("获取推荐歌单失败: ", e);
        }

        return Collections.emptyList();
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
