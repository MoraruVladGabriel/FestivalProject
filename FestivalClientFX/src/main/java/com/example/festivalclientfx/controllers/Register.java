package com.example.festivalclientfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register {

    private ArtistServices artistServices;

    private CasierServices casierServices;

    private CumparareServices cumparareServices;

    private SpectacolServices spectacolServices;

    @FXML
    TextField numeRegister;

    @FXML
    TextField emailRegister;

    @FXML
    TextField parolaRegister;

    @FXML
    TextField oficiuRegister;

    @FXML
    Label wrongRegister;

    public void setRegister(ArtistServices artistServices, CasierServices services, CumparareServices cumparareServices, SpectacolServices spectacolServices){
        this.artistServices = artistServices;
        this.casierServices = services;
        this.cumparareServices = cumparareServices;
        this.spectacolServices = spectacolServices;
    }


    public void userSave(ActionEvent event) {
        try {
            if (numeRegister.getText().isEmpty() || emailRegister.getText().isEmpty() || parolaRegister.getText().isEmpty() || oficiuRegister.getText().isEmpty()) {
                wrongRegister.setText("Please enter your data!");
            } else {
                if(casierServices.getCasierByEmail(emailRegister.getText()) != null){
                    wrongRegister.setText("Email already used!");
                }else{
                    wrongRegister.setText("Succes!");
                    casierServices.addCasier(numeRegister.getText(), parolaRegister.getText(), emailRegister.getText(), oficiuRegister.getText());

                    go_register(event);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void go_register(ActionEvent event) {
        try {
            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
            Parent root = fxmlLoader.load();
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

    public void backToLogIn(ActionEvent event) {
        try {
            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
            Parent root = fxmlLoader.load();
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
}
