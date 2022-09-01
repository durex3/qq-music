package com.durex.music.response.qq;

import com.durex.music.model.qq.RecommendPlay;

import java.util.List;

public class RecommendPlayListUResp {
    private int result;
    private Data data;


    public static class Data {
        private List<RecommendPlay> list;
        private int count;

        public List<RecommendPlay> getList() {
            return list;
        }

        public void setList(List<RecommendPlay> list) {
            this.list = list;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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