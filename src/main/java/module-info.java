module com.example.sticky_hero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sticky_hero to javafx.fxml;
    exports com.example.sticky_hero;
}