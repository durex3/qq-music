package com.durex.music.model.qq;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Song {

    @JsonProperty(value = "access_num")
    private Long accessNum;
    @JsonProperty(value = "album_pic_mid")
    private String albumPicMid;
    @JsonProperty(value = "censor_remark")
    private List<Object> censorRemark;
    @JsonProperty(value = "censor_status")
    private Long censorStatus;
    @JsonProperty(value = "censor_time")
    private Long censorTime;
    @JsonProperty(value = "commit_time")
    private Long commitTime;
    @JsonProperty(value = "cover_mid")
    private String coverMid;
    @JsonProperty(value = "cover_url_big")
    private String coverUrlBig;
    @JsonProperty(value = "cover_url_medium")
    private String coverUrlMedium;
    @JsonProperty(value = "cover_url_small")
    private String coverUrlSmall;
    @JsonProperty(value = "create_time")
    private Long createTime;
    @JsonProperty(value = "creator_info")
    private CreatorInfo creatorInfo;
    @JsonProperty(value = "creator_uin")
    private Long creatorUin;
    private String desc;
    private Long dirid;
    @JsonProperty(value = "fav_num")
    private Long favNum;
    @JsonProperty(value = "modify_time")
    private Long modifyTime;
    @JsonProperty(value = "pic_mid")
    private String picMid;
    private String rcmdcontent;
    private String rcmdtemplate;
    private Long score;
    @JsonProperty(value = "song_ids")
    private List<Long> songIds;
    @JsonProperty(value = "song_types")
    private List<Long> songTypes;
    @JsonProperty(value = "tag_ids")
    private List<Long> tagIds;
    @JsonProperty(value = "tag_names")
    private List<Object> tagNames;
    private Long tid;
    private String title;
    private String tjreport;

    public Long getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(Long accessNum) {
        this.accessNum = accessNum;
    }

    public String getAlbumPicMid() {
        return albumPicMid;
    }

    public void setAlbumPicMid(String albumPicMid) {
        this.albumPicMid = albumPicMid;
    }

    public List<Object> getCensorRemark() {
        return censorRemark;
    }

    public void setCensorRemark(List<Object> censorRemark) {
        this.censorRemark = censorRemark;
    }

    public Long getCensorStatus() {
        return censorStatus;
    }

    public void setCensorStatus(Long censorStatus) {
        this.censorStatus = censorStatus;
    }

    public Long getCensorTime() {
        return censorTime;
    }

    public void setCensorTime(Long censorTime) {
        this.censorTime = censorTime;
    }

    public Long getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Long commitTime) {
        this.commitTime = commitTime;
    }

    public String getCoverMid() {
        return coverMid;
    }

    public void setCoverMid(String coverMid) {
        this.coverMid = coverMid;
    }

    public String getCoverUrlBig() {
        return coverUrlBig;
    }

    public void setCoverUrlBig(String coverUrlBig) {
        this.coverUrlBig = coverUrlBig;
    }

    public String getCoverUrlMedium() {
        return coverUrlMedium;
    }

    public void setCoverUrlMedium(String coverUrlMedium) {
        this.coverUrlMedium = coverUrlMedium;
    }

    public String getCoverUrlSmall() {
        return coverUrlSmall;
    }

    public void setCoverUrlSmall(String coverUrlSmall) {
        this.coverUrlSmall = coverUrlSmall;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public CreatorInfo getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(CreatorInfo creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public Long getCreatorUin() {
        return creatorUin;
    }

    public void setCreatorUin(Long creatorUin) {
        this.creatorUin = creatorUin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getDirid() {
        return dirid;
    }

    public void setDirid(Long dirid) {
        this.dirid = dirid;
    }

    public Long getFavNum() {
        return favNum;
    }

    public void setFavNum(Long favNum) {
        this.favNum = favNum;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPicMid() {
        return picMid;
    }

    public void setPicMid(String picMid) {
        this.picMid = picMid;
    }

    public String getRcmdcontent() {
        return rcmdcontent;
    }

    public void setRcmdcontent(String rcmdcontent) {
        this.rcmdcontent = rcmdcontent;
    }

    public String getRcmdtemplate() {
        return rcmdtemplate;
    }

    public void setRcmdtemplate(String rcmdtemplate) {
        this.rcmdtemplate = rcmdtemplate;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    public List<Long> getSongTypes() {
        return songTypes;
    }

    public void setSongTypes(List<Long> songTypes) {
        this.songTypes = songTypes;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public List<Object> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<Object> tagNames) {
        this.tagNames = tagNames;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTjreport() {
        return tjreport;
    }

    public void setTjreport(String tjreport) {
        this.tjreport = tjreport;
    }
}
