package com.example.festivalapp.controllers;

import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.service.ArtistServices;
import com.example.festivalapp.service.CasierServices;
import com.example.festivalapp.service.CumparareServices;
import com.example.festivalapp.service.SpectacolServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CautareController {
    private ArtistServices artistServices;
    private CasierServices casierServices;
    private CumparareServices cumparareServices;
    private SpectacolServices spectacolServices;

    private String data;

    ObservableList<Spectacol> model = FXCollections.observableArrayList();

    @FXML
    TableView<Spectacol> cautareTableView;

    @FXML
    TableColumn<Spectacol, String> artistColumnTable;

    @FXML
    TableColumn<Spectacol, String> locatieColumnTable;

    @FXML
    TableColumn<Spectacol, LocalDateTime> oraColumnTable;

    @FXML
    TableColumn<Spectacol, Integer> locuriDisponibileColumnTable;

    public void setCautareControler(String data, ArtistServices artistServices, CasierServices casierServices, CumparareServices cumparareServices, SpectacolServices spectacolServices){
        this.data = data;
        this.artistServices = artistServices;
        this.casierServices = casierServices;
        this.cumparareServices = cumparareServices;
        this.spectacolServices = spectacolServices;

        initModel(data);
    }

    @FXML
    public void initialize(){
        artistColumnTable.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("nume"));
        locatieColumnTable.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("locatie"));
        oraColumnTable.setCellValueFactory(new PropertyValueFactory<Spectacol, LocalDateTime>("data"));
        locuriDisponibileColumnTable.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("locuriDisponibile"));

        cautareTableView.setItems(model);
    }

    private void initModel(String data){
        try {
            LocalDate dateTime = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            model.setAll(spectacolServices.filterByDay(dateTime));
        }catch (Exception e){
            System.out.println(e);}
    }


}
