package com.durex.music.api;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.qq.SongDetail;
import com.durex.music.response.qq.SongListResp;
import com.durex.music.utils.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author liugelong
 * @date 2022/8/19 08:59
 */
@Slf4j
public class SongDetailApi {

    private SongDetailApi() {
    }

    /**
     * <h2>返回歌单详情</h2>
     *
     * @return {@link SongDetail}
     */
    public static SongDetail getSongDetail(String dissId) {

        String url = MusicConstant.QQ_MUSIC_HOST + "/songlist?id=" + dissId;
        HttpClient client = HttpClient.newBuilder().build();

        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            SongListResp songListResp = JsonMapper.string2Object(response.body(), new TypeReference<>() {
            });
            return songListResp.getSongDetail();
        } catch (IOException | InterruptedException e) {
            log.error("返回歌单详情失败: ", e);
        }

        return null;
    }
}
