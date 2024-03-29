package com.durex.music.model;

import com.durex.music.api.MusicApi;
import com.durex.music.constant.MusicConstant;
import com.durex.music.model.bind.CurrPlaySecondsBinding;
import com.durex.music.model.bind.MusicProperty;
import com.durex.music.model.bind.PlayListMusic;
import com.leewyatt.rxcontrols.controls.RXMediaProgressBar;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;


public class MusicPlayer {

    private MusicPlayer() {
    }

    private static final PlayListMusic MUSIC_PLAY_LIST = new PlayListMusic();

    private static final SimpleStringProperty CUR_MUSIC_PLAY_MID = new SimpleStringProperty();
    private static final ObjectProperty<Image> CUR_MUSIC_PLAY_IMAGE = new SimpleObjectProperty<>();
    private static final SimpleStringProperty CUR_MUSIC_PLAY_NAME = new SimpleStringProperty();

    private static final SimpleStringProperty CUR_MUSIC_PLAY_ALBUM = new SimpleStringProperty();

    private static final SimpleBooleanProperty PLAY_BUTTON_SELECTED = new SimpleBooleanProperty();

    private static final SimpleStringProperty CUR_MUSIC_TOTAL_SECONDS = new SimpleStringProperty("00:00");

    private static final SimpleDoubleProperty CURRENT_SOUND_VALUE = new SimpleDoubleProperty(50.0);

    private static Label curMusicSeconds;
    private static MediaPlayer player;
    private static RXMediaProgressBar currMusicProgress;

    private static synchronized void play(MusicProperty music) {
        final String playUrl = MusicApi.getMusicPlay(music.getId());
        if (playUrl == null || playUrl.isBlank()) {
            return;
        }

        // 清除旧的播放信息
        if (player != null) {
            disposeMediaPlayer();
        }

        player = new MediaPlayer(new Media(playUrl));
        // 音乐播放前执行这里的操作
        final String imageUrl = String.format(MusicConstant.IMAGE_PREFIX, music.getAlbummid());
        final Image image = new Image(imageUrl, 40, 40, false, false, true);
        CUR_MUSIC_PLAY_IMAGE.set(image);

        CUR_MUSIC_PLAY_MID.set(music.getId());
        CUR_MUSIC_PLAY_NAME.set(music.getName().getText() + " - " + music.getSinger().getText());
        CUR_MUSIC_PLAY_ALBUM.set(music.getAlbumName().getText());
        CUR_MUSIC_TOTAL_SECONDS.set(music.getDuration().getText());

        // 设置播放音乐的时长
        player.setStopTime(Duration.seconds(music.getInterval()));

        // 绑定当前播放时间
        final ReadOnlyObjectProperty<Duration> timeProperty = player.currentTimeProperty();

        // 绑定进度条
        curMusicSeconds.textProperty().bind(new CurrPlaySecondsBinding(timeProperty));
        currMusicProgress.durationProperty().set(Duration.seconds(music.getInterval()));
        currMusicProgress.bufferProgressTimeProperty().bind(player.bufferProgressTimeProperty());

        // 监听播放时间进度
        player.currentTimeProperty().addListener(getDurationChangeListener());

        // 进度条拖动和点击事件
        currMusicProgress.setOnMouseDragged(event -> {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.seek(currMusicProgress.getCurrentTime());
            }
        });

        currMusicProgress.setOnMouseClicked(event -> {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.seek(currMusicProgress.getCurrentTime());
            }
        });

        player.setVolume(CURRENT_SOUND_VALUE.getValue() / 100);

        // 播放结束自动播放下一首
        player.setOnEndOfMedia(() -> {
            BigDecimal currentTime = BigDecimal.valueOf(player.currentTimeProperty().get().toSeconds())
                    .setScale(1, RoundingMode.HALF_UP);
            if (currentTime.compareTo(BigDecimal.valueOf(player.getStopTime().toSeconds())) < 0) {
                // mac bug 没播放就结束了 重新加载
                play(music);
            } else {
                playNextMusic();
            }
        });


        player.setOnPaused(() -> PLAY_BUTTON_SELECTED.set(false));

        player.setOnReady(() -> {
            player.play();
            PLAY_BUTTON_SELECTED.set(true);
            setCurrPlayMusicColor(music, MusicConstant.MENU_SELECTED_COLOR);
        });
    }

    public static synchronized void play(int index, MusicProperty music) {

        if (music.isNotCanPlay()) {
            return;
        }
        MusicProperty lastMusic = null;
        if (MUSIC_PLAY_LIST.getLastMusicIndex() >= 0) {
            lastMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(MUSIC_PLAY_LIST.getLastMusicIndex());
        }
        if (lastMusic != null && Objects.equals(lastMusic.getId(), music.getId())) {
            replayOrPause();
        } else {
            if (lastMusic != null) {
                setCurrPlayMusicColor(lastMusic, Color.BLACK);
            }
            MUSIC_PLAY_LIST.setLastMusicIndex(index);
            play(music);
        }
    }

    public static synchronized void play() {
        if (player != null) {
            player.play();
        }
    }

    public static synchronized void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public static synchronized void playNextMusic() {
        int size = MUSIC_PLAY_LIST.getMusicPropertyList().size();
        if (size < 2) {
            return;
        }

        int index = MUSIC_PLAY_LIST.getLastMusicIndex();

        final MusicProperty lastMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(index);
        if (lastMusic != null) {
            setCurrPlayMusicColor(lastMusic, Color.BLACK);
        }

        // 如果是最后一首歌, 那么下一首歌曲就是播放第一首歌曲
        index = (index == size - 1) ? 0 : index + 1;
        MusicProperty nextMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(index);

        while (nextMusic.isNotCanPlay()) {
            index = (index == size - 1) ? 0 : index + 1;
            nextMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(index);
        }

        MUSIC_PLAY_LIST.setLastMusicIndex(index);
        play(nextMusic);
    }

    public static synchronized void playPreMusic() {
        int size = MUSIC_PLAY_LIST.getMusicPropertyList().size();
        if (size < 2) {
            return;
        }

        int index = MUSIC_PLAY_LIST.getLastMusicIndex();

        final MusicProperty lastMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(index);
        if (lastMusic != null) {
            MusicPlayer.setCurrPlayMusicColor(lastMusic, Color.BLACK);
        }

        // 如果是第一首歌, 那么上一首歌曲就是播放第后一首歌曲
        index = (index == 0) ? size - 1 : index - 1;
        MusicProperty preMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(index);

        while (preMusic.isNotCanPlay()) {
            index = (index == 0) ? size - 1 : index - 1;
            preMusic = MUSIC_PLAY_LIST.getMusicPropertyList().get(index);
        }

        MUSIC_PLAY_LIST.setLastMusicIndex(index);
        play(preMusic);
    }

    public static void setCurrPlayMusicColor(MusicProperty music, Color menuSelectedColor) {
        music.getSinger().setTextFill(menuSelectedColor);
        music.getName().setTextFill(menuSelectedColor);
        music.getAlbumName().setTextFill(menuSelectedColor);
        music.getDuration().setTextFill(menuSelectedColor);
        music.setBackground(new Background(new BackgroundFill(menuSelectedColor, null, null)));
    }

    public static void refreshPlayList(PlayType type, String dataId, List<MusicProperty> musicPropertyList) {
        MusicPlayer.getMusicPlayList().getMusicPropertyList().forEach(musicProperty -> {
            if (musicProperty.getName().getTextFill() == MusicConstant.MENU_SELECTED_COLOR) {
                setCurrPlayMusicColor(musicProperty, Color.BLACK);
            }
        });
        MusicPlayer.getMusicPlayList().getMusicPropertyList().clear();
        MusicPlayer.getMusicPlayList().getContext().setType(type);
        MusicPlayer.getMusicPlayList().getContext().setDataId(dataId);

        MusicPlayer.getMusicPlayList().getMusicPropertyList().addAll(musicPropertyList);

        MusicPlayer.getMusicPlayList().setSize(String.valueOf(MusicPlayer.getMusicPlayList().getMusicPropertyList().size()));

        MusicPlayer.getMusicPlayList().setLastMusicIndex(-1);

    }

    private static void disposeMediaPlayer() {
        currMusicProgress.durationProperty().unbind();
        currMusicProgress.bufferProgressTimeProperty().unbind();
        currMusicProgress.setCurrentTime(Duration.ZERO);
        player.currentTimeProperty().removeListener(getDurationChangeListener());
        curMusicSeconds.textProperty().unbind();
        player.setOnEndOfMedia(null);
        player.dispose();
        player = null;
    }


    private static ChangeListener<Duration> getDurationChangeListener() {
        return (o, oldValue, newValue) -> currMusicProgress.setCurrentTime(newValue);
    }

    private static synchronized void replayOrPause() {
        if (PLAY_BUTTON_SELECTED.get()) {
            // 暂停
            pause();
        } else {
            // 暂停后重新播放
            play();
        }
        PLAY_BUTTON_SELECTED.set(!PLAY_BUTTON_SELECTED.get());
    }


    public static MediaPlayer getPlayer() {
        return player;
    }

    public static Label getCurMusicSeconds() {
        return curMusicSeconds;
    }

    public static void setCurMusicSeconds(Label curMusicSeconds) {
        MusicPlayer.curMusicSeconds = curMusicSeconds;
    }

    public static RXMediaProgressBar getCurrMusicProgress() {
        return currMusicProgress;
    }

    public static void setCurrMusicProgress(RXMediaProgressBar currMusicProgress) {
        MusicPlayer.currMusicProgress = currMusicProgress;
    }

    public static ObjectProperty<Image> getCurMusicPlayImage() {
        return CUR_MUSIC_PLAY_IMAGE;
    }

    public static SimpleStringProperty getCurMusicPlayName() {
        return CUR_MUSIC_PLAY_NAME;
    }

    public static SimpleBooleanProperty getPlayButtonSelected() {
        return PLAY_BUTTON_SELECTED;
    }

    public static SimpleStringProperty getCurMusicTotalSeconds() {
        return CUR_MUSIC_TOTAL_SECONDS;
    }

    public static PlayListMusic getMusicPlayList() {
        return MUSIC_PLAY_LIST;
    }

    public static SimpleStringProperty getCurMusicPlayAlbum() {
        return CUR_MUSIC_PLAY_ALBUM;
    }

    public static SimpleStringProperty getCurMusicPlayMid() {
        return CUR_MUSIC_PLAY_MID;
    }

    public static SimpleDoubleProperty getCurrentSoundValue() {
        return CURRENT_SOUND_VALUE;
    }
}
