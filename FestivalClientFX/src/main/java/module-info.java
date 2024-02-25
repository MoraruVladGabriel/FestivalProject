module com.example.festivalclientfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.festivalclientfx to javafx.fxml;
    exports com.example.festivalclientfx;
}