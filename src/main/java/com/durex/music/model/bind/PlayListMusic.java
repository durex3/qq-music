package com.durex.music.model.bind;

import com.durex.music.model.PlayListContext;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author liugelong
 * @date 2022/9/1 10:30
 */
public class PlayListMusic {

    private final ObservableList<MusicProperty> musicPropertyList = FXCollections.observableArrayList();
    private final SimpleStringProperty size = new SimpleStringProperty("0");

    private final PlayListContext context = new PlayListContext();

    private int lastMusicIndex;

    public PlayListMusic() {
        this.lastMusicIndex = -1;
    }

    public ObservableList<MusicProperty> getMusicPropertyList() {
        return musicPropertyList;
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

    public PlayListContext getContext() {
        return context;
    }
}
