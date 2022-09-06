package com.durex.music.controller;

import com.durex.music.model.MusicPlayer;
import com.durex.music.model.PlayListContext;
import com.durex.music.model.PlayType;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.model.qq.Banner;
import com.durex.music.model.qq.MusicDetail;
import com.durex.music.model.qq.RecommendPlay;
import com.durex.music.model.qq.SingerDetail;
import com.durex.music.service.RecommendService;
import com.durex.music.ui.BasePagePane;
import com.durex.music.ui.MainPane;
import com.durex.music.ui.MusicPane;
import com.durex.music.ui.PaneFactory;
import com.durex.music.ui.SongDetailPagePane;
import com.durex.music.ui.SongVBox;
import com.durex.music.utils.TimeUtils;
import com.leewyatt.rxcontrols.animation.carousel.AnimAround;
import com.leewyatt.rxcontrols.animation.carousel.CarouselAnimation;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.leewyatt.rxcontrols.controls.RXCarousel;
import com.leewyatt.rxcontrols.enums.DisplayMode;
import com.leewyatt.rxcontrols.pane.RXCarouselPane;
import javafx.fxml.FXML;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * <h2>推荐面板 controller</h2>
 */
@Slf4j
public class RecommendController implements Initializable {

    private final List<MusicProperty> newMusicPropertyList = new ArrayList<>();
    private final List<VBox> curShowNewMusicList = new ArrayList<>();

    private int lastMusicShowIndex = 0;
    private static final int MUSIC_SIZE = 6;

    private boolean isForward = true;

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
        curShowNewMusicList.forEach(musicPane -> musicListPane.getChildren().remove(musicPane));
        curShowNewMusicList.clear();
        newMusicForward();
        setMusicListPane();
    }

    @FXML
    public void handleMusicBackClicked(MouseEvent e) {
        curShowNewMusicList.forEach(musicPane -> musicListPane.getChildren().remove(musicPane));
        curShowNewMusicList.clear();
        newMusicBack();
        setMusicListPane();
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
            musicProperty.setAlbumName(new Label(musicDetail.getAlbum().getName()));
            final Label intervalLabel = new Label(TimeUtils.format((double) musicDetail.getInterval()));
            musicProperty.setDuration(intervalLabel);
            musicProperty.setInterval((long) musicDetail.getInterval());
            musicProperty.setAlbummid(musicDetail.getAlbum().getMid());
            musicProperty.setMsgid((long) musicDetail.getAction().getMsgid());
            musicProperty.setPayplay(musicDetail.getPay().getPayPlay());

            newMusicPropertyList.add(musicProperty);
        });

        for (int i = lastMusicShowIndex; i < MUSIC_SIZE; i++) {
            curShowNewMusicList.add(MusicPane.build(newMusicPropertyList.get(i)));
        }
        lastMusicShowIndex = MUSIC_SIZE - 1;
        setMusicListPane();
    }

    private void bindPlayClicked() {
        PlayListContext context = MusicPlayer.getMusicPlayList().getContext();

        for (int i = 0; i < curShowNewMusicList.size(); i++) {
            VBox vBox = curShowNewMusicList.get(i);
            int curIndex = i;
            vBox.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    if (context.getType() != PlayType.NEW_MUSIC) {
                        MusicPlayer.refreshPlayList(PlayType.NEW_MUSIC, "-1", newMusicPropertyList);
                    }
                    int playListIndex;
                    if (isForward) {
                        playListIndex = (lastMusicShowIndex + 1 - MUSIC_SIZE + curIndex) % newMusicPropertyList.size();
                    } else {
                        playListIndex = (lastMusicShowIndex + curIndex) % newMusicPropertyList.size();
                    }
                    MusicPlayer.play(playListIndex, newMusicPropertyList.get(playListIndex));
                }
            });
        }
    }

    private void newMusicForward() {
        int newMusicSize = newMusicPropertyList.size();
        if (!isForward) {
            isForward = true;
            lastMusicShowIndex = (lastMusicShowIndex + MUSIC_SIZE - 1) % newMusicSize;
        }
        // 读取 size 个值 逆向读取
        int curIndex = 0;
        for (int i = lastMusicShowIndex; i < lastMusicShowIndex + MUSIC_SIZE; i++) {
            curIndex = (i + 1) % newMusicSize;
            curShowNewMusicList.add(MusicPane.build(newMusicPropertyList.get(curIndex)));
        }
        lastMusicShowIndex = curIndex;
    }

    private void newMusicBack() {
        int newMusicSize = newMusicPropertyList.size();

        if (isForward) {
            isForward = false;
            lastMusicShowIndex = (lastMusicShowIndex - MUSIC_SIZE + 1 + newMusicSize) % newMusicSize;
        }
        // 读取 size 个值 如果超过了最大数量则从头读取
        int curIndex = lastMusicShowIndex;
        for (int i = lastMusicShowIndex; i > lastMusicShowIndex - MUSIC_SIZE; i--) {
            curIndex = (i - 1 + newMusicSize) % newMusicSize;
            curShowNewMusicList.add(MusicPane.build(newMusicPropertyList.get(curIndex)));
        }
        Collections.reverse(curShowNewMusicList);
        lastMusicShowIndex = curIndex;
    }

    private void setMusicListPane() {
        bindPlayClicked();
        musicListPane.add(curShowNewMusicList.get(0), 0, 0);
        musicListPane.add(curShowNewMusicList.get(1), 0, 1);
        musicListPane.add(curShowNewMusicList.get(2), 0, 2);
        musicListPane.add(curShowNewMusicList.get(3), 1, 0);
        musicListPane.add(curShowNewMusicList.get(4), 1, 1);
        musicListPane.add(curShowNewMusicList.get(5), 1, 2);
    }

    private void initSongList() {
        final List<RecommendPlay> songList = RecommendService.getRecommendSongList();


        final List<VBox> songVboxList = songList.stream().map(song -> SongVBox.build(String.valueOf(song.getContentId()), song.getCover(), song.getTitle(), song.getListenNum())).toList();

        songVboxList.forEach(song -> {
            Node songImage = song.lookup("#song-image");
            songImage.setOnMouseClicked(event -> {
                // 传递数据
                RXAvatar image = (RXAvatar) event.getSource();
                String dissId = image.getUserData().toString();
                BasePagePane pane = PaneFactory.newInstance(SongDetailPagePane.class);
                MainPane.getScrollPane().setContent(null);
                Parent songDetail = pane.load(dissId);
                if (songDetail != null) {
                    MainPane.getScrollPane().setContent(songDetail);
                }
            });
        });
        songListPane.getChildren().addAll(songVboxList);
    }

    private void initCarousel() {
        final List<Banner> bannerList = RecommendService.getBannerList();
        // 为了保持更佳的切换效果,建议所有的RXCarouselPane和RXCarousel大小保持一致
        bannerList.forEach(banner -> {
            final Image image = new Image(banner.getPicUrl(), 730, 200, true, false, true);
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
