package com.durex.music.model.qq;

public class Volume {
    private Object gain;
    private int peak;
    private Object lra;

    public void setGain(Object gain) {
        this.gain = gain;
    }

    public Object getGain() {
        return gain;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public int getPeak() {
        return peak;
    }

    public void setLra(Object lra) {
        this.lra = lra;
    }

    public Object getLra() {
        return lra;
    }
}