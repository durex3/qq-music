package com.durex.music.response.qq;


/**
 * @author liugelong
 * @date 2022/8/30 11:04
 */
public class LyricResp {

    private int result;

    private Data data;

    public static class Data {
        private int retcode;
        private int code;
        private int subcode;
        private String lyric;
        private String trans;

        public int getRetcode() {
            return retcode;
        }

        public void setRetcode(int retcode) {
            this.retcode = retcode;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getSubcode() {
            return subcode;
        }

        public void setSubcode(int subcode) {
            this.subcode = subcode;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
