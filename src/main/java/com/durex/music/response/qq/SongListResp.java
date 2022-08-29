package com.durex.music.response.qq;

import com.durex.music.model.qq.SongDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SongListResp {

    @JsonProperty(value = "data")
    private SongDetail songDetail;
    private Long result;

    public SongDetail getSongDetail() {
        return songDetail;
    }

    public void setSongDetail(SongDetail songDetail) {
        this.songDetail = songDetail;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
