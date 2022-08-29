
package com.durex.music.response.qq;

import com.durex.music.model.qq.Song;

import java.util.List;


public class RecommendPlayListResp {

    private Data data;
    private Long result;


    public static class Data {

        private List<Song> list;
        private Long total;

        public List<Song> getList() {
            return list;
        }

        public void setList(List<Song> list) {
            this.list = list;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
