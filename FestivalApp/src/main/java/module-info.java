module com.example.festivalapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;


    //opens com.example.festivalapp to javafx.fxml;
    exports com.example.festivalapp;
    exports com.example.festivalapp.controllers;
    opens com.example.festivalapp.controllers to javafx.fxml;
    opens com.example.festivalapp to javafx.base;
    opens com.example.festivalapp.domain to javafx.base;
}