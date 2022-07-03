package com.example.discordgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    static ClientController clientController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 600);
        stage.setTitle("Discord");
        stage.setScene(scene);
        clientController.setStage(stage);
        clientController.start();
    }

    public static void main(String[] args) throws IOException {
        clientController=new ClientController();
        launch();
    }
}