package com.durex.music.response.qq;

import com.durex.music.model.qq.MusicDetail;

import java.util.List;

public class NewMusicResp {
    private int result;
    private Data data;

    public static class Data {
        private String lan;
        private List<MusicDetail> list;
        private int type;

        public String getLan() {
            return lan;
        }

        public void setLan(String lan) {
            this.lan = lan;
        }

        public List<MusicDetail> getList() {
            return list;
        }

        public void setList(List<MusicDetail> list) {
            this.list = list;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}