package com.durex.music.service;

import com.durex.music.constant.MusicConstant;
import com.durex.music.response.qq.LyricResp;
import com.durex.music.utils.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liugelong
 * @date 2022/8/22 10:09
 */
@Slf4j
public class MusicService {

    private MusicService() {
    }

    /**
     * <h2>获取播放链接</h2>
     *
     * @param id mid 歌曲id
     * @return String
     */
    public static String getMusicPlay(String id) {

        String url = MusicConstant.QQ_MUSIC_HOST + "/song/url?id=" + id;
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        Map<String, Object> result = new HashMap<>();
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = JsonMapper.string2Object(response.body(), new TypeReference<>() {
            });
        } catch (IOException | InterruptedException e) {
            log.error("获取播放链接失败: ", e);
        }

        return result.getOrDefault("data", "").toString();
    }

    /**
     * <h2>获取歌词</h2>
     *
     * @param songmid 歌曲 id
     * @return 歌词
     */
    public static String getLyric(String songmid) {
        String url = MusicConstant.QQ_MUSIC_HOST + "/lyric?songmid=" + songmid;
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            LyricResp lyricResp = JsonMapper.string2Object(response.body(), new TypeReference<LyricResp>() {
            });
            return lyricResp.getData().getLyric();
        } catch (IOException | InterruptedException e) {
            log.error("获取歌词失败: ", e);
        }
        return null;
    }
}
