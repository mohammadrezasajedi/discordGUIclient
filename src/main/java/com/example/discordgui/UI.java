package com.example.discordgui;

import com.example.discordgui.controller.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UI {
    private Stage stage;
    private ClientController clientController;
    private DynamicFormController dynamicFormController;

    public UI(Stage stage,ClientController clientController) {
        this.stage = stage;
        this.clientController=clientController;
    }

    public void showMenu(ArrayList<String> items) throws IOException {
        dynamicFormController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/DinamicMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DinamicMenuController dinamicMenuController = fxmlLoader.getController();
        dinamicMenuController.setUi(this);
        dinamicMenuController.setItems(items);
        changeStage(scene);
    }

    public void welcome(String str) throws IOException {
        dynamicFormController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        WelcomeController welcomeController = fxmlLoader.getController();
        welcomeController.setText(str);
        Platform.runLater(() -> {
            stage.setScene(scene);
            if (!stage.isShowing()) {
                stage.show();
            }
        });
    }

    public void getInfo (String header,String req) throws IOException {
        if (dynamicFormController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/DynamicForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            dynamicFormController = fxmlLoader.getController();
            dynamicFormController.setUi(this);
            dynamicFormController.init(header,req);
            changeStage(scene);
        } else {
            dynamicFormController.next(header,req);
        }
    }

    public void getAgain (String str) {
        dynamicFormController.again(str);
    }

    public void resetMenu () {
        dynamicFormController = null;
    }

    public void chatMode () throws IOException {
        dynamicFormController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/ChatPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ChatPageController chatPageController = fxmlLoader.getController();
        chatPageController.setUi(this);
        chatPageController.init(null,methodRead());
        changeStage(scene);
    }

    public void Table (int num) throws IOException {
        dynamicFormController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/ShowTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ShowTableController showTableController = fxmlLoader.getController();
        showTableController.setUi(this);
        ArrayList<String> strings = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            strings.add(methodRead());
        }
        showTableController.init(strings);
        changeStage(scene);
    }

    private void changeStage (Scene scene){
        Platform.runLater(() -> stage.setScene(scene));
    }

    public String methodRead () throws IOException {
        return clientController.methodRead();
    }

    public void methodWrite (String str) throws IOException {
        clientController.methodWrite(str);
    }

}
