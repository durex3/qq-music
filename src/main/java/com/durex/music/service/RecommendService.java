package com.durex.music.service;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.qq.Banner;
import com.durex.music.model.qq.MusicDetail;
import com.durex.music.model.qq.RecommendPlay;
import com.durex.music.response.qq.NewMusicResp;
import com.durex.music.response.qq.RecommendBannerResp;
import com.durex.music.response.qq.RecommendPlayListUResp;
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

/**
 * @author liugelong
 * @date 2022/8/17 09:27
 */
@Slf4j
public class RecommendService {

    private static final List<RecommendPlay> SONG_LIST = new ArrayList<>();


    private RecommendService() {
    }


    /**
     * <<h2>获取推荐歌单</h2>
     *
     * @return {@link  List<RecommendPlay>}
     */
    public static List<RecommendPlay> getRecommendSongList() {
        if (!SONG_LIST.isEmpty()) {
            return SONG_LIST;
        }

        String url = MusicConstant.QQ_MUSIC_HOST + "/recommend/playlist/u";
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            RecommendPlayListUResp recommendPlayListUResp = JsonMapper.string2Object(response.body(), new TypeReference<>() {
            });
            final List<RecommendPlay> songList = recommendPlayListUResp.getData().getList();
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


    /**
     * <h2>获取新歌列表</h2>
     *
     * @return {@link List<MusicDetail>}
     */
    public static List<MusicDetail> getNewMusicList() {
        String url = MusicConstant.QQ_MUSIC_HOST + "/new/songs?type=0";
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            NewMusicResp newMusicResp = JsonMapper.string2Object(response.body(), new TypeReference<>() {
            });
            return newMusicResp.getData().getList();
        } catch (IOException | InterruptedException e) {
            log.error("获取新歌列表: ", e);
        }

        return Collections.emptyList();
    }

}
