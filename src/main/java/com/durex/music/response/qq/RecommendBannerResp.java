
package com.durex.music.response.qq;

import com.durex.music.model.qq.Banner;

import java.util.List;

public class RecommendBannerResp {

    private List<Banner> data;
    private Long result;

    public List<Banner> getData() {
        return data;
    }

    public void setData(List<Banner> data) {
        this.data = data;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
