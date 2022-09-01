package com.durex.music.model.bind;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author liugelong
 * @date 2022/9/1 10:30
 */
public class PlayListMusic {

    private final ObservableList<MusicProperty> musicPropertyList = FXCollections.observableArrayList();

    // 当前播放队列的播放歌单 id
    private final SimpleLongProperty currentSongId = new SimpleLongProperty(-1);

    private final SimpleStringProperty size = new SimpleStringProperty("0");

    private int lastMusicIndex;

    public PlayListMusic() {
        this.lastMusicIndex = -1;
    }

    public ObservableList<MusicProperty> getMusicPropertyList() {
        return musicPropertyList;
    }

    public long getCurrentSongId() {
        return currentSongId.get();
    }

    public SimpleLongProperty currentSongIdProperty() {
        return currentSongId;
    }

    public void setCurrentSongId(long currentSongId) {
        this.currentSongId.set(currentSongId);
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public int getLastMusicIndex() {
        return lastMusicIndex;
    }

    public void setLastMusicIndex(int lastMusicIndex) {
        this.lastMusicIndex = lastMusicIndex;
    }
}
