module qq.music {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.net.http;
    requires lombok;
    requires rxcontrols;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;

    opens com.durex.music.controller to javafx.fxml;

    exports com.durex.music;
    exports com.durex.music.model;
    exports com.durex.music.model.qq;
    exports com.durex.music.model.bind;
    exports com.durex.music.response.qq;
    exports com.durex.music.ui;
}