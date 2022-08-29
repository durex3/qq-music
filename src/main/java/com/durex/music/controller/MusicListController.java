package com.durex.music.controller;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.model.MusicPlayer;
import com.durex.music.model.qq.Music;
import com.durex.music.model.qq.Singer;
import com.durex.music.model.qq.SongDetail;
import com.durex.music.ui.MusicNameHBox;
import com.durex.music.ui.MusicPlayPane;
import com.durex.music.utils.TimeUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
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
    private TableColumn<MusicProperty, SimpleObjectProperty<HBox>> name;
    @FXML
    private TableColumn<MusicProperty, SimpleObjectProperty<Label>> singer;
    @FXML
    private TableColumn<MusicProperty, String> album;
    @FXML
    private TableColumn<MusicProperty, String> duration;
    private long songId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        singer.setCellValueFactory(new PropertyValueFactory<>("singer"));
        album.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        MusicPlayer.getTablePlayList().clear();
    }

    public void init(SongDetail songDetail) {
        final List<Music> musicList = songDetail.getSonglist();
        songId = songDetail.getDissid();



        musicList.forEach(music -> {
            MusicProperty musicProperty = new MusicProperty();
            musicProperty.setId(music.getAlbumid());

            musicProperty.setName(MusicNameHBox.build(music));
            String singerName = music.getSinger().stream().map(Singer::getName).collect(Collectors.joining("/"));

            final Label singerNameLabel = new Label(singerName);
            singerNameLabel.setTextFill(Color.BLACK);
            musicProperty.setSinger(singerNameLabel);
            final Label albumNameLabel = new Label(music.getAlbumname());
            albumNameLabel.setTextFill(Color.BLACK);
            musicProperty.setAlbumName(albumNameLabel);

            musicProperty.setMid(music.getSongmid());
            final Label intervalLabel = new Label(TimeUtils.format((double) music.getInterval()));
            intervalLabel.setTextFill(Color.BLACK);
            musicProperty.setDuration(intervalLabel);
            musicProperty.setInterval(music.getInterval());
            musicProperty.setMsgid(music.getMsgid());
            musicProperty.setAlbummid(music.getAlbummid());

            MusicPlayer.getTablePlayList().add(musicProperty);
        });

        musicListTable.setItems(MusicPlayer.getTablePlayList());

        if (songId == MusicPlayer.getCurrentPlaySongId().get()) {
            // 如果是则设置为绿色的播放状态
            MusicPlayer.setCurrPlayMusicColor(MusicPlayer.getTablePlayList().get(MusicPlayer.getLastMusicIndex()), MusicConstant.MENU_SELECTED_COLOR);
        }

        // 高度适应行数
        musicListTable.setFixedCellSize(40);
        musicListTable.prefHeightProperty().bind(
                musicListTable.fixedCellSizeProperty().multiply(Bindings.size(musicListTable.getItems()).add(0.75))
        );
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
        if (MusicPlayer.getCurrentPlaySongId().get() != songId) {
            MusicPlayer.getPlayList().clear();
            MusicPlayer.getTablePlayList()
                    .forEach(musicProperty -> MusicPlayer.getPlayList().add(MusicPlayPane.build(musicProperty)));
            MusicPlayer.getCurrentPlaySongId().set(songId);
            MusicPlayer.getCurrentPlayListNum().set(String.valueOf(MusicPlayer.getPlayList().size()));

            MusicPlayer.setLastMusicIndex(-1);
        }
    }
}
