package com.durex.music.model.qq;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Banner {

    private String id;
    @JsonProperty(value = "picUrl")
    private String picUrl;
    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
