package com.example.festivalclientfx.controllers;

import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.service.ArtistServices;
import com.example.festivalapp.service.CasierServices;
import com.example.festivalapp.service.CumparareServices;
import com.example.festivalapp.service.SpectacolServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AfterLogin {

    private ArtistServices artistServices;
    private CasierServices casierServices;
    private CumparareServices cumparareServices;
    private SpectacolServices spectacolServices;

    ObservableList<Spectacol> model = FXCollections.observableArrayList();

    @FXML
    TableView<Spectacol> afterLoginTableView;
    @FXML
    TableColumn<Spectacol, String> tableColumnNumeSpectacol;
    @FXML
    TableColumn<Spectacol, LocalDateTime> tableColumnData;
    @FXML
    TableColumn<Spectacol, String> tableColumnLocatia;
    @FXML
    TableColumn<Spectacol, Integer> tableColumnLocuriDisponibile;
    @FXML
    TableColumn<Spectacol, Integer> tableColumnLocuriVandute;

    @FXML
    TextField cautareData;

    public void setAfterLoginTableViewlogin(ArtistServices artistServices, CasierServices casierServices, CumparareServices cumparareServices, SpectacolServices services) throws SQLException {
        this.artistServices = artistServices;
        this.casierServices = casierServices;
        this.cumparareServices = cumparareServices;
        this.spectacolServices=services;

        initModel();
    }

    @FXML
    public void initialize(){
        tableColumnNumeSpectacol.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("nume"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<Spectacol, LocalDateTime>("data"));
        tableColumnLocatia.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("locatie"));
        tableColumnLocuriDisponibile.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("locuriDisponibile"));
        tableColumnLocuriVandute.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("locuriVandute"));
        afterLoginTableView.setItems(model);
    }

    private void initModel() throws SQLException {
        Iterable<Spectacol> messages = spectacolServices.findAll();
        List<Spectacol> spectacole = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(spectacole);
    }

    public void userLogOut(ActionEvent event) throws IOException {
        try {
            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            LogIn logIn = fxmlLoader.getController();
            logIn.setLogIN(artistServices,casierServices,cumparareServices,spectacolServices);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("LogIN");
            stage.show();
            actualStage.hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void cautareSpectacol(ActionEvent event) {
        try {
            //Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cautareScene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            CautareController ctrl = fxmlLoader.getController();
            ctrl.setCautareControler(cautareData.getText(),artistServices,casierServices,cumparareServices,spectacolServices);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cautare");
            stage.show();
            //actualStage.hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void cumparareBilete(ActionEvent event) {
        try {
            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cumparareScene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            CumparareController ctrl = fxmlLoader.getController();
            ctrl.setCumparareController(artistServices,casierServices,cumparareServices,spectacolServices);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cumparare");
            stage.show();
            actualStage.hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
