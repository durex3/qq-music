package com.durex.music.model.qq;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SongDetail {

    @JsonProperty(value = "album_pic_mid")
    private String albumPicMid;

    private Long buynum;

    private Long cmtnum;

    private String coveradurl;

    private Long ctime;
    @JsonProperty(value = "cur_song_num")
    private Long curSongNum;

    private String desc;
    @JsonProperty(value = "dir_pic_url2")
    private String dirPicUrl2;
    @JsonProperty(value = "dir_show")
    private Long dirShow;

    private Long dirid;

    private Long dissid;

    private String dissname;

    private String disstid;

    private Long disstype;
    @JsonProperty(value = "encrypt_uin")
    private String encryptUin;

    private String headurl;

    private String ifpicurl;

    private Long isAd;

    private Long isdj;

    private Long isvip;

    private String login;

    private String logo;

    private Long mtime;

    private String nick;

    private String nickname;

    private Long owndir;
    @JsonProperty(value = "pic_dpi")
    private Long picDpi;
    @JsonProperty(value = "pic_mid")
    private String picMid;

    private String scoreavage;

    private Long scoreusercount;

    private Long singerid;

    private String singermid;
    @JsonProperty(value = "song_begin")
    private Long songBegin;
    @JsonProperty(value = "song_update_num")
    private Long songUpdateNum;
    @JsonProperty(value = "song_update_time")
    private Long songUpdateTime;

    private String songids;

    private List<Music> songlist;

    private Long songnum;

    private String songtypes;

    private List<Tag> tags;
    @JsonProperty(value = "total_song_num")
    private Long totalSongNum;

    private Long type;

    private String uin;

    private Long visitnum;

    public String getAlbumPicMid() {
        return albumPicMid;
    }

    public void setAlbumPicMid(String albumPicMid) {
        this.albumPicMid = albumPicMid;
    }

    public Long getBuynum() {
        return buynum;
    }

    public void setBuynum(Long buynum) {
        this.buynum = buynum;
    }

    public Long getCmtnum() {
        return cmtnum;
    }

    public void setCmtnum(Long cmtnum) {
        this.cmtnum = cmtnum;
    }

    public String getCoveradurl() {
        return coveradurl;
    }

    public void setCoveradurl(String coveradurl) {
        this.coveradurl = coveradurl;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getCurSongNum() {
        return curSongNum;
    }

    public void setCurSongNum(Long curSongNum) {
        this.curSongNum = curSongNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDirPicUrl2() {
        return dirPicUrl2;
    }

    public void setDirPicUrl2(String dirPicUrl2) {
        this.dirPicUrl2 = dirPicUrl2;
    }

    public Long getDirShow() {
        return dirShow;
    }

    public void setDirShow(Long dirShow) {
        this.dirShow = dirShow;
    }

    public Long getDirid() {
        return dirid;
    }

    public void setDirid(Long dirid) {
        this.dirid = dirid;
    }

    public Long getDissid() {
        return dissid;
    }

    public void setDissid(Long dissid) {
        this.dissid = dissid;
    }

    public String getDissname() {
        return dissname;
    }

    public void setDissname(String dissname) {
        this.dissname = dissname;
    }

    public String getDisstid() {
        return disstid;
    }

    public void setDisstid(String disstid) {
        this.disstid = disstid;
    }

    public Long getDisstype() {
        return disstype;
    }

    public void setDisstype(Long disstype) {
        this.disstype = disstype;
    }

    public String getEncryptUin() {
        return encryptUin;
    }

    public void setEncryptUin(String encryptUin) {
        this.encryptUin = encryptUin;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getIfpicurl() {
        return ifpicurl;
    }

    public void setIfpicurl(String ifpicurl) {
        this.ifpicurl = ifpicurl;
    }

    public Long getIsAd() {
        return isAd;
    }

    public void setIsAd(Long isAd) {
        this.isAd = isAd;
    }

    public Long getIsdj() {
        return isdj;
    }

    public void setIsdj(Long isdj) {
        this.isdj = isdj;
    }

    public Long getIsvip() {
        return isvip;
    }

    public void setIsvip(Long isvip) {
        this.isvip = isvip;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getOwndir() {
        return owndir;
    }

    public void setOwndir(Long owndir) {
        this.owndir = owndir;
    }

    public Long getPicDpi() {
        return picDpi;
    }

    public void setPicDpi(Long picDpi) {
        this.picDpi = picDpi;
    }

    public String getPicMid() {
        return picMid;
    }

    public void setPicMid(String picMid) {
        this.picMid = picMid;
    }

    public String getScoreavage() {
        return scoreavage;
    }

    public void setScoreavage(String scoreavage) {
        this.scoreavage = scoreavage;
    }

    public Long getScoreusercount() {
        return scoreusercount;
    }

    public void setScoreusercount(Long scoreusercount) {
        this.scoreusercount = scoreusercount;
    }

    public Long getSingerid() {
        return singerid;
    }

    public void setSingerid(Long singerid) {
        this.singerid = singerid;
    }

    public String getSingermid() {
        return singermid;
    }

    public void setSingermid(String singermid) {
        this.singermid = singermid;
    }

    public Long getSongBegin() {
        return songBegin;
    }

    public void setSongBegin(Long songBegin) {
        this.songBegin = songBegin;
    }

    public Long getSongUpdateNum() {
        return songUpdateNum;
    }

    public void setSongUpdateNum(Long songUpdateNum) {
        this.songUpdateNum = songUpdateNum;
    }

    public Long getSongUpdateTime() {
        return songUpdateTime;
    }

    public void setSongUpdateTime(Long songUpdateTime) {
        this.songUpdateTime = songUpdateTime;
    }

    public String getSongids() {
        return songids;
    }

    public void setSongids(String songids) {
        this.songids = songids;
    }

    public List<Music> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<Music> songlist) {
        this.songlist = songlist;
    }

    public Long getSongnum() {
        return songnum;
    }

    public void setSongnum(Long songnum) {
        this.songnum = songnum;
    }

    public String getSongtypes() {
        return songtypes;
    }

    public void setSongtypes(String songtypes) {
        this.songtypes = songtypes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Long getTotalSongNum() {
        return totalSongNum;
    }

    public void setTotalSongNum(Long totalSongNum) {
        this.totalSongNum = totalSongNum;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public Long getVisitnum() {
        return visitnum;
    }

    public void setVisitnum(Long visitnum) {
        this.visitnum = visitnum;
    }
}
