package com.durex.music.model.qq;

public class MusicPay {
    private int payMonth;
    private int priceTrack;
    private int priceAlbum;
    private int payPlay;
    private int payDown;
    private int payStatus;
    private int timeFree;

    public void setPayMonth(int payMonth) {
        this.payMonth = payMonth;
    }

    public int getPayMonth() {
        return payMonth;
    }

    public void setPriceTrack(int priceTrack) {
        this.priceTrack = priceTrack;
    }

    public int getPriceTrack() {
        return priceTrack;
    }

    public void setPriceAlbum(int priceAlbum) {
        this.priceAlbum = priceAlbum;
    }

    public int getPriceAlbum() {
        return priceAlbum;
    }

    public void setPayPlay(int payPlay) {
        this.payPlay = payPlay;
    }

    public int getPayPlay() {
        return payPlay;
    }

    public void setPayDown(int payDown) {
        this.payDown = payDown;
    }

    public int getPayDown() {
        return payDown;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setTimeFree(int timeFree) {
        this.timeFree = timeFree;
    }

    public int getTimeFree() {
        return timeFree;
    }
}