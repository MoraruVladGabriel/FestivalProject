package com.example.festivalapp;

import com.example.festivalapp.controllers.AfterLogin;
import com.example.festivalapp.controllers.LogIn;
import com.example.festivalapp.controllers.Register;
import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.repository.ArtistRepository;
import com.example.festivalapp.repository.CasierRepository;
import com.example.festivalapp.repository.CumparareRepository;
import com.example.festivalapp.repository.SpectacolRepository;
import com.example.festivalapp.repository.repoDB.ArtistDBRepository;
import com.example.festivalapp.repository.repoDB.CasierDBRepository;
import com.example.festivalapp.repository.repoDB.CumparareDBRepository;
import com.example.festivalapp.repository.repoDB.SpectacolDBRepository;
import com.example.festivalapp.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;

public class HelloApplication extends Application {
    private SpectacolRepository spectacolRepo;

    private static CasierRepository casierRepo;

    private static ArtistRepository artistRepo;

    private static CumparareRepository cumparareRepo;

    private static CasierServices casierSer;

    private static ArtistServices artistSer;
    private static CumparareServices cumparareSer;
    private static SpectacolServices spectacolSer;

    @Override
    public void start(Stage stage) throws IOException, ServicesException {
        Properties props = new Properties();
        try{
            props.load(new FileReader("bd.config"));

        }catch (IOException e){
            System.out.println("Cannot find bd.config " + e);
        }

        casierRepo = new CasierDBRepository(props);
        casierSer = new CasierServices(casierRepo);

        artistRepo = new ArtistDBRepository(props);
        artistSer = new ArtistServices(artistRepo);

        spectacolRepo = new SpectacolDBRepository(props,artistRepo);
        spectacolSer = new SpectacolServices(spectacolRepo,artistRepo);

        cumparareRepo = new CumparareDBRepository(props,casierRepo,spectacolRepo);
        cumparareSer = new CumparareServices(cumparareRepo,spectacolRepo,casierRepo);



        spectacolSer.updateSpectacol(1,"s1",5,spectacolSer.findById(1).getData(),"l1",100,50);
        System.out.println(spectacolSer.findById(1).getLocuriDisponibile());
        System.out.println(spectacolSer.findById(1).getLocuriVandute());
        initView(stage);
        stage.show();

    }

    public void initView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root)); //new Scene(fxmlLoader.load(), 350 , 400);
        LogIn ctrl = fxmlLoader.getController();
        ctrl.setLogIN(artistSer,casierSer,cumparareSer,spectacolSer);

        //stage.setScene(scene);
        stage.setTitle("LogIn");
        stage.show();
    }

//    public void showAfterLoginScene() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("afterLogin.fxml"));
//        Parent root = fxmlLoader.load();
//        AfterLogin ctrl = fxmlLoader.getController();
//        ctrl.setAfterLoginTableViewlogin(spectacolSer);
//        Scene scene = new Scene(root); //new Scene(fxmlLoader.load(), 350 , 400);
//        stg.setScene(scene);
//        stg.setTitle("Festival");
//        stg.show();
//    }

//    public void showRegisterScene() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registerScene.fxml"));
//        Parent root = fxmlLoader.load();
//        Register ctrl = fxmlLoader.getController();
//        ctrl.setRegister(casierSer);
//        Scene scene = new Scene(root); //new Scene(fxmlLoader.load(), 350 , 400);
//        stg.setScene(scene);
//        stg.setTitle("Register");
//        stg.show();
//    }
//
//    public void changeScene(String fxml) throws IOException{
//        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
//        stg.getScene().setRoot(pane);
//    }

//    static CasierServices getServices(){
//
//        try{
//            Properties props = new Properties();
//            props.load(new FileReader("bd.config"));
//            casierRepo = new CasierDBRepository(props);
//            casierSer = new CasierServices(casierRepo);
//            return casierSer;
//        }catch (IOException e){
//            System.out.println("Cannot find bd.config " + e);
//        }
//        return null;
//    }

//    private void initView(Stage primaryStage) throws IOException {
//
//        // FXMLLoader fxmlLoader = new FXMLLoader();
//        //fxmlLoader.setLocation(getClass().getResource("com/example/guiex1/views/UtilizatorView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/UtilizatorView.fxml"));
//
//        AnchorPane userLayout = fxmlLoader.load();
//        primaryStage.setScene(new Scene(userLayout));
//
//        LogIn logIn = fxmlLoader.getController();
//        logIn.setLogIN(service, fservice);
//
//    }

    public static void main(String[] args) {
        launch();
    }
}