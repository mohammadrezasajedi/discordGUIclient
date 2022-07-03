package com.example.discordgui;

import com.example.discordgui.controller.DinamicMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UI {
    private Stage stage;
    private ClientController clientController;

    public UI(Stage stage,ClientController clientController) {
        this.stage = stage;
        this.clientController=clientController;
    }

    public void showMenu(ArrayList<String> items) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/DinamicMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DinamicMenuController dinamicMenuController = fxmlLoader.getController();
        dinamicMenuController.setUi(this);
        dinamicMenuController.setItems(items);
        stage.setScene(scene);
    }

    public void welcome(String str) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public String methodRead () throws IOException {
        return clientController.methodRead();
    }

    public void methodWrite (String str) throws IOException {
        clientController.methodWrite(str);
    }

}
