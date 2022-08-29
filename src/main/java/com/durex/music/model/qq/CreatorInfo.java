package com.durex.music.model.qq;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatorInfo {

    private String avatar;
    @JsonProperty(value = "is_dj")
    private Long isDj;
    private String nick;
    @JsonProperty(value = "taoge_avatar")
    private String taogeAvatar;
    @JsonProperty(value = "taoge_nick")
    private String taogeNick;
    private Long uin;
    @JsonProperty(value = "vip_type")
    private Long vipType;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getIsDj() {
        return isDj;
    }

    public void setIsDj(Long isDj) {
        this.isDj = isDj;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTaogeAvatar() {
        return taogeAvatar;
    }

    public void setTaogeAvatar(String taogeAvatar) {
        this.taogeAvatar = taogeAvatar;
    }

    public String getTaogeNick() {
        return taogeNick;
    }

    public void setTaogeNick(String taogeNick) {
        this.taogeNick = taogeNick;
    }

    public Long getUin() {
        return uin;
    }

    public void setUin(Long uin) {
        this.uin = uin;
    }

    public Long getVipType() {
        return vipType;
    }

    public void setVipType(Long vipType) {
        this.vipType = vipType;
    }
}
