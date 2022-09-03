package com.durex.music.model;

public class PlayListContext {

    private PlayType type;
    private String dataId;


    public PlayType getType() {
        return type;
    }

    public void setType(PlayType type) {
        this.type = type;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
