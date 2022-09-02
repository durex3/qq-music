package com.durex.music.model.qq;

public class SingerDetail {
    private int id;
    private String mid;
    private String name;
    private String title;
    private int type;
    private long uin;
    private String pmid;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMid() {
        return mid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getPmid() {
        return pmid;
    }
}