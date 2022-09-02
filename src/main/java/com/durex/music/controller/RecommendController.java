package com.durex.music.controller;

import com.durex.music.model.bind.MusicProperty;
import com.durex.music.model.qq.Banner;
import com.durex.music.model.qq.MusicDetail;
import com.durex.music.model.qq.RecommendPlay;
import com.durex.music.model.qq.SingerDetail;
import com.durex.music.service.RecommendService;
import com.durex.music.ui.MainPane;
import com.durex.music.ui.MusicPane;
import com.durex.music.ui.SongVBox;
import com.durex.music.utils.TimeUtils;
import com.leewyatt.rxcontrols.animation.carousel.AnimAround;
import com.leewyatt.rxcontrols.animation.carousel.CarouselAnimation;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXCarousel;
import com.leewyatt.rxcontrols.enums.DisplayMode;
import com.leewyatt.rxcontrols.pane.RXCarouselPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * <h2>推荐面板 controller</h2>
 */
@Slf4j
public class RecommendController implements Initializable {

    private final List<MusicProperty> newMusicPropertyList = new ArrayList<>();
    private final List<AnchorPane> curShowNewMusicList = new ArrayList<>();

    @FXML
    private TilePane songListPane;
    @FXML
    private RXCarousel carousel;
    @FXML
    private GridPane musicListPane;
    @FXML
    private AnchorPane newMusicPane;
    @FXML
    private Label newMusicLeft;
    @FXML
    private Label newMusicRight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCarousel();

        initSongList();

        initNewMusicList();
    }

    @FXML
    public void handleMusicForwardClicked(MouseEvent e) {
        updateNewMusic(6);
    }

    private void initNewMusicList() {

        newMusicPane.setOnMouseEntered(event -> {
            newMusicLeft.setVisible(true);
            newMusicRight.setVisible(true);
        });

        newMusicPane.setOnMouseExited(event -> {
            newMusicLeft.setVisible(false);
            newMusicRight.setVisible(false);
        });

        final List<MusicDetail> newMusicList = RecommendService.getNewMusicList();
        if (newMusicList == null || newMusicList.isEmpty()) {
            return;
        }
        newMusicList.forEach(musicDetail -> {
            MusicProperty musicProperty = new MusicProperty();
            musicProperty.setId(musicDetail.getMid());
            musicProperty.setName(new Label(musicDetail.getName()));
            String singerName = musicDetail.getSinger().stream().map(SingerDetail::getName).collect(Collectors.joining("/"));

            final Label singerNameLabel = new Label(singerName);
            musicProperty.setSinger(singerNameLabel);
            musicProperty.setAlbumName(new Label(musicDetail.getTitle()));
            final Label intervalLabel = new Label(TimeUtils.format((double) musicDetail.getInterval()));
            musicProperty.setDuration(intervalLabel);
            musicProperty.setInterval(musicProperty.getInterval());
            musicProperty.setAlbummid(musicDetail.getAlbum().getMid());
            musicProperty.setMsgid((long) musicDetail.getAction().getMsgid());
            musicProperty.setPayplay(musicDetail.getPay().getPayPlay());

            newMusicPropertyList.add(musicProperty);
        });

        updateNewMusic(0);
    }

    private void updateNewMusic(int index) {
        curShowNewMusicList.forEach(musicPane -> musicListPane.getChildren().remove(musicPane));
        for (int i = 0; i < 6; i++) {
            curShowNewMusicList.add(MusicPane.build(newMusicPropertyList.get(index + i)));
        }

        musicListPane.add(curShowNewMusicList.get(index), 0, 0);
        musicListPane.add(curShowNewMusicList.get(index + 1), 0, 1);
        musicListPane.add(curShowNewMusicList.get(index + 2), 0, 2);
        musicListPane.add(curShowNewMusicList.get(index + 3), 1, 0);
        musicListPane.add(curShowNewMusicList.get(index + 4), 1, 1);
        musicListPane.add(curShowNewMusicList.get(index + 5), 1, 2);
    }


    private void initSongList() {
        final List<RecommendPlay> songList = RecommendService.getRecommendSongList();

        final FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/song-detail.fxml")));
        try {

            Parent songDetail = fxmlLoader.load();
            SongDetailController controller = fxmlLoader.getController();
            final List<VBox> songVboxList = songList.stream().map(song -> SongVBox.build(String.valueOf(song.getContentId()), song.getCover(), song.getTitle(), song.getListenNum())).toList();

            songVboxList.forEach(song -> {
                Node songImage = song.lookup("#song-image");
                songImage.setOnMouseClicked(event -> {
                    // 传递数据
                    RXAvatar image = (RXAvatar) event.getSource();
                    String dissId = image.getUserData().toString();
                    if (controller != null) {
                        controller.init(dissId);
                    }
                    if (songDetail != null) {
                        MainPane.getScrollPane().setContent(songDetail);
                    }
                });
            });
            songListPane.getChildren().addAll(songVboxList);
        } catch (IOException e) {
            log.error("加载歌单详情页面失败: ", e);
        }
    }

    private void initCarousel() {
        final List<Banner> bannerList = RecommendService.getBannerList();
        // 为了保持更佳的切换效果,建议所有的RXCarouselPane和RXCarousel大小保持一致
        bannerList.forEach(banner -> {
            final Image image = new Image(banner.getPicUrl(), 720, 200, true, false, true);
            RXCarouselPane pane = new RXCarouselPane(new ImageView(image));
            carousel.getPaneList().add(pane);
        });
        // 给轮播图设置动画类型
        CarouselAnimation anim = new AnimAround();
        carousel.setCarouselAnimation(anim);
        //设置前进后退按钮为 鼠标移入显示. 鼠标移除隐藏
        carousel.setArrowDisplayMode(DisplayMode.AUTO);
        // 设置自动播放/切换 轮播图
        carousel.setAutoSwitch(true);
    }
}
