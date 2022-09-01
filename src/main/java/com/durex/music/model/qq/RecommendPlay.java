package com.durex.music.model.qq;

public class RecommendPlay {
    private String albumPicMid;
    private long contentId;
    private String cover;
    private long creator;
    private String edgeMark;
    private int id;
    private boolean isDj;
    private boolean isVip;
    private String jumpUrl;
    private int listenNum;
    private String picMid;
    private String rcmdcontent;
    private String rcmdtemplate;
    private int rcmdtype;
    private int singerid;
    private String title;
    private String tjreport;
    private int type;
    private String username;

    public void setAlbumPicMid(String albumPicMid) {
        this.albumPicMid = albumPicMid;
    }

    public String getAlbumPicMid() {
        return albumPicMid;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getContentId() {
        return contentId;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public long getCreator() {
        return creator;
    }

    public void setEdgeMark(String edgeMark) {
        this.edgeMark = edgeMark;
    }

    public String getEdgeMark() {
        return edgeMark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIsDj(boolean isDj) {
        this.isDj = isDj;
    }

    public boolean isIsDj() {
        return isDj;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public boolean isIsVip() {
        return isVip;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setListenNum(int listenNum) {
        this.listenNum = listenNum;
    }

    public int getListenNum() {
        return listenNum;
    }

    public void setPicMid(String picMid) {
        this.picMid = picMid;
    }

    public String getPicMid() {
        return picMid;
    }

    public void setRcmdcontent(String rcmdcontent) {
        this.rcmdcontent = rcmdcontent;
    }

    public String getRcmdcontent() {
        return rcmdcontent;
    }

    public void setRcmdtemplate(String rcmdtemplate) {
        this.rcmdtemplate = rcmdtemplate;
    }

    public String getRcmdtemplate() {
        return rcmdtemplate;
    }

    public void setRcmdtype(int rcmdtype) {
        this.rcmdtype = rcmdtype;
    }

    public int getRcmdtype() {
        return rcmdtype;
    }

    public void setSingerid(int singerid) {
        this.singerid = singerid;
    }

    public int getSingerid() {
        return singerid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTjreport(String tjreport) {
        this.tjreport = tjreport;
    }

    public String getTjreport() {
        return tjreport;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return
                "ListItem{" +
                        "album_pic_mid = '" + albumPicMid + '\'' +
                        ",content_id = '" + contentId + '\'' +
                        ",cover = '" + cover + '\'' +
                        ",creator = '" + creator + '\'' +
                        ",edge_mark = '" + edgeMark + '\'' +
                        ",id = '" + id + '\'' +
                        ",is_dj = '" + isDj + '\'' +
                        ",is_vip = '" + isVip + '\'' +
                        ",jump_url = '" + jumpUrl + '\'' +
                        ",listen_num = '" + listenNum + '\'' +
                        ",pic_mid = '" + picMid + '\'' +
                        ",rcmdcontent = '" + rcmdcontent + '\'' +
                        ",rcmdtemplate = '" + rcmdtemplate + '\'' +
                        ",rcmdtype = '" + rcmdtype + '\'' +
                        ",singerid = '" + singerid + '\'' +
                        ",title = '" + title + '\'' +
                        ",tjreport = '" + tjreport + '\'' +
                        ",type = '" + type + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}