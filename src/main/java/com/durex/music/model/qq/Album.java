package com.durex.music.model.qq;

public class Album {
    private int id;
    private String mid;
    private String name;
    private String title;
    private String subtitle;
    private String timePublic;
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

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setTimePublic(String timePublic) {
        this.timePublic = timePublic;
    }

    public String getTimePublic() {
        return timePublic;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getPmid() {
        return pmid;
    }
}