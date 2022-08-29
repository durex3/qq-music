package com.durex.music.model.qq;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Music {


    private String albumdesc;

    private Long albumid;

    private String albummid;

    private String albumname;

    private Long alertid;

    private Long belongCD;

    private Long cdIdx;

    private Long interval;

    private Long isonly;

    private String label;

    private Long msgid;

    private Pay pay;

    private Preview preview;

    private Long rate;

    private List<Singer> singer;

    private Long size128;

    private Long size320;
    @JsonProperty(value = "size5_1")
    private Long size51;

    private Long sizeape;

    private Long sizeflac;

    private Long sizeogg;

    private Long songid;

    private String songmid;

    private String songname;

    private String songorig;

    private Long songtype;

    private String strMediaMid;

    private Long stream;

    @JsonProperty(value = "switch")
    private Long switch_;

    private Long type;

    private String vid;


    public String getAlbumdesc() {
        return albumdesc;
    }

    public void setAlbumdesc(String albumdesc) {
        this.albumdesc = albumdesc;
    }

    public Long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Long albumid) {
        this.albumid = albumid;
    }

    public String getAlbummid() {
        return albummid;
    }

    public void setAlbummid(String albummid) {
        this.albummid = albummid;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public Long getAlertid() {
        return alertid;
    }

    public void setAlertid(Long alertid) {
        this.alertid = alertid;
    }

    public Long getBelongCD() {
        return belongCD;
    }

    public void setBelongCD(Long belongCD) {
        this.belongCD = belongCD;
    }

    public Long getCdIdx() {
        return cdIdx;
    }

    public void setCdIdx(Long cdIdx) {
        this.cdIdx = cdIdx;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getIsonly() {
        return isonly;
    }

    public void setIsonly(Long isonly) {
        this.isonly = isonly;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public List<Singer> getSinger() {
        return singer;
    }

    public void setSinger(List<Singer> singer) {
        this.singer = singer;
    }

    public Long getSize128() {
        return size128;
    }

    public void setSize128(Long size128) {
        this.size128 = size128;
    }

    public Long getSize320() {
        return size320;
    }

    public void setSize320(Long size320) {
        this.size320 = size320;
    }

    public Long getSize51() {
        return size51;
    }

    public void setSize51(Long size51) {
        this.size51 = size51;
    }

    public Long getSizeape() {
        return sizeape;
    }

    public void setSizeape(Long sizeape) {
        this.sizeape = sizeape;
    }

    public Long getSizeflac() {
        return sizeflac;
    }

    public void setSizeflac(Long sizeflac) {
        this.sizeflac = sizeflac;
    }

    public Long getSizeogg() {
        return sizeogg;
    }

    public void setSizeogg(Long sizeogg) {
        this.sizeogg = sizeogg;
    }

    public Long getSongid() {
        return songid;
    }

    public void setSongid(Long songid) {
        this.songid = songid;
    }

    public String getSongmid() {
        return songmid;
    }

    public void setSongmid(String songmid) {
        this.songmid = songmid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSongorig() {
        return songorig;
    }

    public void setSongorig(String songorig) {
        this.songorig = songorig;
    }

    public Long getSongtype() {
        return songtype;
    }

    public void setSongtype(Long songtype) {
        this.songtype = songtype;
    }

    public String getStrMediaMid() {
        return strMediaMid;
    }

    public void setStrMediaMid(String strMediaMid) {
        this.strMediaMid = strMediaMid;
    }

    public Long getStream() {
        return stream;
    }

    public void setStream(Long stream) {
        this.stream = stream;
    }

    public Long getSwitch_() {
        return switch_;
    }

    public void setSwitch_(Long switch_) {
        this.switch_ = switch_;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
