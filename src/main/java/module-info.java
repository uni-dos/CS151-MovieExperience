module com.example.movieexperiencegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.movieexperiencegui to javafx.fxml;
    exports com.example.movieexperiencegui;
}