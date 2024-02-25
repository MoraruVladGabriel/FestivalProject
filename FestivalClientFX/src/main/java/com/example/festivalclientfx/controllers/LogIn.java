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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn {

    private CasierServices casierServices;

    private ArtistServices artistServices;

    private CumparareServices cumparareServices;

    private SpectacolServices spectacolServices;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label wrongLogin;

    @FXML
    private TextField email;

    @FXML
    PasswordField password;

    @FXML
    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin(event);
    }

    public void setLogIN(ArtistServices artistServices, CasierServices casierServices, CumparareServices cumparareServices, SpectacolServices spectacolServices){
        this.artistServices = artistServices;
        this.casierServices = casierServices;
        this.cumparareServices = cumparareServices;
        this.spectacolServices = spectacolServices;
    }

    private void checkLogin(ActionEvent event){
        try {
            if(email.getText().isEmpty() || password.getText().isEmpty()){
                wrongLogin.setText("Please enter your data!");
            }else{
                if(casierServices.getCasierByEmail(email.getText()) == null){
                    wrongLogin.setText("Wrong email or password!");
                }else{
                    log_in(event);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void log_in(ActionEvent event) {
        try {
            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/afterLogin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            AfterLogin afterLogin = fxmlLoader.getController();
            afterLogin.setAfterLoginTableViewlogin(artistServices,casierServices,cumparareServices,spectacolServices);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Festival");
            stage.show();
            actualStage.hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void userRegister(ActionEvent event){
        try {
            Stage actualStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/registerScene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Register register = fxmlLoader.getController();
            register.setRegister(artistServices,casierServices,cumparareServices,spectacolServices);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Register");
            stage.show();
            actualStage.hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
