module com.example.sticky_hero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.sticky_hero to javafx.fxml;
    exports com.example.sticky_hero;
}