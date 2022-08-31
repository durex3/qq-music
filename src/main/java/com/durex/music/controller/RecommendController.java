package com.durex.music.controller;

import com.durex.music.model.qq.Banner;
import com.durex.music.model.qq.Song;
import com.durex.music.service.RecommendService;
import com.durex.music.ui.MainPane;
import com.durex.music.ui.SongVBox;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * <h2>推荐面板 controller</h2>
 */
@Slf4j
public class RecommendController implements Initializable {

    private SongDetailController controller = null;
    private Parent songDetail = null;

    @FXML
    private TilePane songListPane;
    @FXML
    private RXCarousel carousel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final List<Song> songList = RecommendService.getRecommendSongList();
        final FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/song-detail.fxml")));

        try {
            songDetail = fxmlLoader.load();
            controller = fxmlLoader.getController();
        } catch (IOException e) {
            log.error("加载歌单详情页面失败: ", e);
        }
        initCarousel(RecommendService.getBannerList());
        songListPane.getChildren().addAll(buildSongList(songList));
    }

    private List<VBox> buildSongList(List<Song> songList) {
        if (songList == null || songList.isEmpty()) {
            return Collections.emptyList();
        }
        final List<VBox> songVboxList = songList.stream()
                .map(song -> SongVBox.build(song.getTid().toString(), song.getCoverUrlSmall(), song.getTitle(), song.getAccessNum()))
                .toList();

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

        return songVboxList;
    }

    private void initCarousel(List<Banner> bannerList) {
        // 为了保持更佳的切换效果,建议所有的RXCarouselPane和RXCarousel大小保持一致
        bannerList.forEach(banner -> {
            final Image image = new Image(banner.getPicUrl(), 720, 200, true, false, true);
            RXCarouselPane pane = new RXCarouselPane(new ImageView(image));
            carousel.getPaneList().add(pane);
        });
        //给轮播图设置动画类型;
        //CarouselAnimation 是接口. AnimVerBlinds是实现类
        //所有的实现类都在 com.leewyatt.rxcontrols.animation.carousel.*;
        CarouselAnimation anim = new AnimAround();
        carousel.setCarouselAnimation(anim);
        //设置页面切换的动画时间
        //carousel.setAnimationTime(Duration.millis(600));
        //设置导航条为显示
        //carousel.setNavDisplayMode(DisplayMode.SHOW);
        //设置前进后退按钮为 鼠标移入显示. 鼠标移除隐藏
        carousel.setArrowDisplayMode(DisplayMode.AUTO);
        //设置当鼠标移入轮播图时停止自动播放
        //carousel.setHoverPause(true);
        //设置每一页的显示时间
        //carousel.setShowTime(Duration.millis(1500));
        //设置初始选中下标为2的页面
        //carousel.setSelectedIndex(2);
        //设置自动播放/切换 轮播图
        carousel.setAutoSwitch(true);
    }
}
