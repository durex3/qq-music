package com.durex.music.controller;

import com.durex.music.model.MusicPlayer;
import com.durex.music.model.PlayListContext;
import com.durex.music.model.PlayType;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.model.qq.Music;
import com.durex.music.model.qq.Singer;
import com.durex.music.model.qq.SongDetail;
import com.durex.music.ui.MusicTableNameCell;
import com.durex.music.utils.TimeUtils;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MusicListController implements Initializable {

    @FXML
    private TableView<MusicProperty> musicListTable;
    @FXML
    private TableColumn<MusicProperty, Integer> id;
    @FXML
    private TableColumn<MusicProperty, HBox> name;
    @FXML
    private TableColumn<MusicProperty, Label> singer;
    @FXML
    private TableColumn<MusicProperty, Label> album;
    @FXML
    private TableColumn<MusicProperty, Label> duration;
    private final ObservableList<MusicProperty> musicPropertyList;
    private final long songId;
    private final List<Music> musicList;

    public MusicListController(SongDetail songDetail) {
        this.songId = songDetail.getDissid();
        this.musicList = songDetail.getSonglist();
        this.musicPropertyList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(cell -> {
            final SimpleObjectProperty<HBox> property = new SimpleObjectProperty<>();
            property.set(MusicTableNameCell.build(cell.getValue()));
            return property;
        });
        singer.setCellValueFactory(new PropertyValueFactory<>("singer"));
        album.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        musicPropertyList.clear();
        musicListTable.setItems(musicPropertyList);
        Platform.runLater(this::initMusicList);
    }

    private void initMusicList() {
        PlayListContext context = MusicPlayer.getMusicPlayList().getContext();
        if (context.getType() == PlayType.SONG && context.getDataId().equals(String.valueOf(songId))) {
            musicPropertyList.addAll(MusicPlayer.getMusicPlayList().getMusicPropertyList());
        } else {
            musicList.forEach(music -> {
                MusicProperty musicProperty = new MusicProperty();
                musicProperty.setId(music.getSongmid());

                musicProperty.setName(new Label(music.getSongname()));
                String singerName = music.getSinger().stream().map(Singer::getName).collect(Collectors.joining("/"));

                final Label singerNameLabel = new Label(singerName);
                singerNameLabel.setTextFill(Color.BLACK);
                musicProperty.setSinger(singerNameLabel);
                final Label albumNameLabel = new Label(music.getAlbumname());
                albumNameLabel.setTextFill(Color.BLACK);
                musicProperty.setAlbumName(albumNameLabel);

                final Label intervalLabel = new Label(TimeUtils.format((double) music.getInterval()));
                intervalLabel.setTextFill(Color.BLACK);
                musicProperty.setDuration(intervalLabel);
                musicProperty.setInterval(music.getInterval());
                musicProperty.setMsgid(music.getMsgid());
                musicProperty.setAlbummid(music.getAlbummid());
                musicProperty.setPayplay(music.getPay().getPayplay());
                musicPropertyList.add(musicProperty);
            });
        }


        // 高度适应行数
        musicListTable.setFixedCellSize(40);
        musicListTable.prefHeightProperty().bind(musicListTable.fixedCellSizeProperty().multiply(Bindings.size(musicListTable.getItems()).add(0.75)));
        musicListTable.minHeightProperty().bind(musicListTable.prefHeightProperty());
        musicListTable.maxHeightProperty().bind(musicListTable.prefHeightProperty());

        musicListTable.setRowFactory(param -> {
            TableRow<MusicProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    initPlayList();
                    MusicPlayer.play(row.getIndex(), row.getItem());
                }
            });
            return row;
        });
    }

    private void initPlayList() {
        // 不是当前播放列表的歌单
        PlayListContext context = MusicPlayer.getMusicPlayList().getContext();
        if (context.getType() != PlayType.SONG || !context.getDataId().equals(String.valueOf(songId))) {
            MusicPlayer.refreshPlayList(PlayType.SONG, String.valueOf(songId), musicPropertyList);
        }
    }
}
