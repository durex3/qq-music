package com.durex.music.model.bind;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class MusicProperty {

    /**
     * 获取播放链接
     */
    private String id;

    private final SimpleObjectProperty<Label> name = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Label> singer = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Label> albumName = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Label> duration = new SimpleObjectProperty<>();

    private Long interval;

    /**
     * 获取图片
     */
    private String albummid;

    private Long msgid;

    private long payplay;


    public boolean isVip() {
        return payplay == 1;
    }

    public boolean isNotCanPlay() {
        return msgid == 1 || msgid == 3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Label getName() {
        return name.get();
    }

    public SimpleObjectProperty<Label> nameProperty() {
        return name;
    }

    public void setName(Label name) {
        this.name.set(name);
    }

    public Label getSinger() {
        return singer.get();
    }

    public SimpleObjectProperty<Label> singerProperty() {
        return singer;
    }

    public void setSinger(Label singer) {
        this.singer.set(singer);
    }

    public Label getAlbumName() {
        return albumName.get();
    }

    public SimpleObjectProperty<Label> albumNameProperty() {
        return albumName;
    }

    public void setAlbumName(Label albumName) {
        this.albumName.set(albumName);
    }

    public Label getDuration() {
        return duration.get();
    }

    public SimpleObjectProperty<Label> durationProperty() {
        return duration;
    }

    public void setDuration(Label duration) {
        this.duration.set(duration);
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public String getAlbummid() {
        return albummid;
    }

    public void setAlbummid(String albummid) {
        this.albummid = albummid;
    }

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public long getPayplay() {
        return payplay;
    }

    public void setPayplay(long payplay) {
        this.payplay = payplay;
    }
}
