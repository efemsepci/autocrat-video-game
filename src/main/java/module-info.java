module com.efemsepci.autocratvideogame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.efemsepci.autocratvideogame.controller to javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires com.fasterxml.jackson.databind;

    opens com.efemsepci.autocratvideogame to javafx.fxml;
    exports com.efemsepci.autocratvideogame;
    exports com.efemsepci.autocratvideogame.controller;
    exports com.efemsepci.autocratvideogame.classes;
}