module org.example.plugin {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.plugin;
    exports org.example.plugin;
}