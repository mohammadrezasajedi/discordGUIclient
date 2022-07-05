package com.example.discordgui.controller;

import com.example.discordgui.UI;
import com.example.discordgui.chat.ReadThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class ChatPageController {

    UI ui;


    @FXML
    private VBox chat;

    @FXML
    private ImageView avatar;

    @FXML
    private TextField field;

    @FXML
    private Text name;

    @FXML
    void attach(ActionEvent event) {

    }

    @FXML
    void send(ActionEvent event) throws IOException {
        String str = field.getText();
        ui.methodWrite(str);
        field.setText("");
    }


    public void init (File avatar,String name){
        if (avatar != null && avatar.exists()){
            this.avatar.setImage(new Image(avatar.getAbsolutePath()));
        }
        this.name.setText(name);
        Thread thread = new Thread(new ReadThread(ui,this));
        thread.start();
    }

    public void newMessage (String str) {
        Text text = new Text();
        text.setText(str);
        text.setStyle("-fx-font-size: 20px ; -fx-text-fill: White");
        Platform.runLater(() -> chat.getChildren().add(text));
    }


    public void setUi(UI ui) {
        this.ui = ui;
    }
}
