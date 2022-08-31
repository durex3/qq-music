## 打包成镜像文件

```shell
mvn javafx:jlink
```

## 将镜像文件打包成安装包

- mac

```shell
jpackage --runtime-image target/image --type dmg --name qq-music --icon AppIcon.icns  --module qq.music/com.durex.music.QQMusicApplication
```

- windows

```shell
jpackage --runtime-image target/image --name qq-music  --module qq.music/com.durex.music.QQMusicApplication --icon QMDriverHelperx64.ico --win-dir-chooser --win-shortcut --win-menu-group
```