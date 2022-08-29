module qq.music {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires javafx.media;
    requires java.net.http;
    requires lombok;
    requires com.github.benmanes.caffeine;
    requires rxcontrols;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;

    opens com.durex.music.controller to javafx.fxml;
    opens com.durex.music.model.bind to java.base;
    opens com.durex.music.model.qq to com.fasterxml.jackson.databind;

    exports com.durex.music;
    exports com.durex.music.response.qq;
    exports com.durex.music.model.qq;
    exports com.durex.music.model.bind;
}