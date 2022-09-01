package com.durex.music.controller;

import com.durex.music.constant.MusicConstant;
import com.durex.music.model.MusicPlayer;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.model.qq.Music;
import com.durex.music.model.qq.Singer;
import com.durex.music.model.qq.SongDetail;
import com.durex.music.ui.MusicPlayPane;
import com.durex.music.utils.TimeUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
    private TableColumn<MusicProperty, Label> name;
    @FXML
    private TableColumn<MusicProperty, Label> singer;
    @FXML
    private TableColumn<MusicProperty, Label> album;
    @FXML
    private TableColumn<MusicProperty, Label> duration;
    private long songId;
    private final ObservableList<MusicProperty> musicPropertyList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Label nameLabel, boolean empty) {
                super.updateItem(nameLabel, empty);
                if (nameLabel != null && !empty) {
                    final MusicProperty music = musicListTable.getItems().get(this.getIndex());
                    HBox hBox = new HBox();
                    hBox.setSpacing(5);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    nameLabel.setTextFill(Color.BLACK);
                    hBox.getChildren().add(nameLabel);
                    if (music.isVip()) {
                        StackPane stackPane = new StackPane();
                        Rectangle rectangle = new Rectangle(20, 12);
                        rectangle.setFill(Color.WHITE);
                        rectangle.setStroke(MusicConstant.MENU_SELECTED_COLOR);
                        Label vipLabel = new Label("vip");
                        vipLabel.setFont(Font.font(10));
                        vipLabel.setTextFill(MusicConstant.MENU_SELECTED_COLOR);
                        stackPane.getChildren().addAll(rectangle, vipLabel);
                        hBox.getChildren().add(stackPane);
                    }

                    if (music.isNotCanPlay()) {
                        nameLabel.setTextFill(Color.web("#a6a6a6"));
                    }
                    this.setGraphic(hBox);
                }
            }
        });
        singer.setCellValueFactory(new PropertyValueFactory<>("singer"));
        album.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        musicPropertyList.clear();
        musicListTable.setItems(musicPropertyList);
    }

    public void init(SongDetail songDetail) {
        final List<Music> musicList = songDetail.getSonglist();
        songId = songDetail.getDissid();

        musicList.forEach(music -> {
            MusicProperty musicProperty = new MusicProperty();
            musicProperty.setId(music.getAlbumid());

            musicProperty.setName(new Label(music.getSongname()));
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
            musicProperty.setPay(music.getPay());
            musicPropertyList.add(musicProperty);
        });

        if (songId == MusicPlayer.getMusicPlayList().getCurrentSongId()) {
            // 如果是则设置为绿色的播放状态
            MusicPlayer.setCurrPlayMusicColor(musicPropertyList.get(MusicPlayer.getMusicPlayList().getLastMusicIndex()), MusicConstant.MENU_SELECTED_COLOR);
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
        if (MusicPlayer.getMusicPlayList().getCurrentSongId() != songId) {
            MusicPlayer.getMusicPlayList().getMusicPropertyList().clear();
            MusicPlayer.getMusicPlayList().getMusicPaneList().clear();
            MusicPlayer.getMusicPlayList().setCurrentSongId(songId);

            for (MusicProperty musicProperty : musicPropertyList) {
                MusicPlayer.getMusicPlayList().getMusicPropertyList().add(musicProperty);
                AnchorPane anchorPane = MusicPlayPane.build(musicProperty);
                MusicPlayPane.bind(anchorPane, musicProperty);
                MusicPlayer.getMusicPlayList().getMusicPaneList().add(anchorPane);
            }

            MusicPlayer.getMusicPlayList().setSize(String.valueOf(MusicPlayer.getMusicPlayList().getMusicPropertyList().size()));

            MusicPlayer.getMusicPlayList().setLastMusicIndex(-1);
        }
    }
}
