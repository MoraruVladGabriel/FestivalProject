package com.example.festivalclientfx.controllers;

import com.example.festivalapp.service.ArtistServices;
import com.example.festivalapp.service.CasierServices;
import com.example.festivalapp.service.CumparareServices;
import com.example.festivalapp.service.SpectacolServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CumparareController {
    private ArtistServices artistServices;
    private CasierServices casierServices;
    private CumparareServices cumparareServices;
    private SpectacolServices spectacolServices;

    @FXML
    TextField idSpectacol, numeCumparator, numarLocuri,idCasier;

    public void setCumparareController(ArtistServices artistService, CasierServices casierService, CumparareServices cumparareService, SpectacolServices spectacolService){
        this.artistServices = artistService;
        this.casierServices = casierService;
        this.cumparareServices = cumparareService;
        this.spectacolServices = spectacolService;
    }


    public void cumparaBilete(ActionEvent event) {
        try {

            int number = Integer.parseInt(numarLocuri.getText());
            int id = Integer.parseInt(idSpectacol.getText());
            int idCas = Integer.parseInt(idCasier.getText());
            cumparareServices.addCumparare(numeCumparator.getText(),number,id,idCas);

            spectacolServices.cumparaBilete(id,number);

            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/afterLogin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            AfterLogin ctrl = fxmlLoader.getController();
            ctrl.setAfterLoginTableViewlogin(artistServices,casierServices,cumparareServices,spectacolServices);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Festival");
            stage.show();
            actualStage.hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
