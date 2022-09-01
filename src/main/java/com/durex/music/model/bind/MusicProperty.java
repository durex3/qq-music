package com.durex.music.model.bind;

import com.durex.music.model.qq.Pay;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class MusicProperty {

    private Long id;

    private String mid;

    private final SimpleObjectProperty<Label> name = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Label> singer = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Label> albumName = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<Label> duration = new SimpleObjectProperty<>();

    private Long interval;

    private String albummid;

    private Long msgid;

    private Pay pay;


    public boolean isVip() {
        return pay.getPayplay() == 1;
    }

    public boolean isNotCanPlay() {
        return msgid == 1 || msgid == 3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }
}
