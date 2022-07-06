package com.example.discordgui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class MessageController {

    private ChatPageController chat;
    private String id;

    public void init (String message,ChatPageController chat){
        message = message.substring(8,message.length() - 1);
        String[] tokens = message.split(",");
        txt.setText(tokens[0].substring(6,tokens[0].length() - 1));
        aut.setText(tokens[1].substring(8));
        id =tokens[3].substring(4);
        this.chat = chat;
        if (txt.getText().contains("-- #download")) {
            txt.setText(txt.getText().substring(0,txt.getText().length() - 12));
            downBut.setVisible(true);
        }
    }

    @FXML
    private Text aut;

    @FXML
    private Text txt;

    @FXML
    private Button downBut;

    @FXML
    void dislike(ActionEvent event) throws IOException {
        chat.sendCommand("#dislike-" + id);
    }

    @FXML
    void happy(ActionEvent event) throws IOException {
        chat.sendCommand("#laughter-" + id);
    }

    @FXML
    void like(ActionEvent event) throws IOException {
        chat.sendCommand("#like-" + id);
    }

    @FXML
    void pin(ActionEvent event) throws IOException {
        chat.sendCommand("#pin-" + id);
    }

    @FXML
    void download(ActionEvent event) throws IOException {
        chat.download("#download-" + id);
    }
}
