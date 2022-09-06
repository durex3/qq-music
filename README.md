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

- jpackage 参数选项

```shell
Generic Options:
  @<filename>
          Read options and/or mode from a file
          This option can be used multiple times.
  --type -t <type>
          The type of package to create
          Valid values are: {"app-image", "exe", "msi"}
          If this option is not specified a platform dependent
          default type will be created.
  --app-version <version>
          Version of the application and/or package
  --copyright <copyright string>
          Copyright for the application
  --description <description string>
          Description of the application
  --help -h
          Print the usage text with a list and description of each valid
          option for the current platform to the output stream, and exit
  --icon <file path>
          Path of the icon of the application package
          (absolute path or relative to the current directory)
  --name -n <name>
          Name of the application and/or package
  --dest -d <destination path>
          Path where generated output file is placed
          (absolute path or relative to the current directory)
          Defaults to the current working directory.
  --temp <directory path>
          Path of a new or empty directory used to create temporary files
          (absolute path or relative to the current directory)
          If specified, the temp dir will not be removed upon the task
          completion and must be removed manually.
          If not specified, a temporary directory will be created and
          removed upon the task completion.
  --vendor <vendor string>
          Vendor of the application
  --verbose
          Enables verbose output
  --version
          Print the product version to the output stream and exit.
```