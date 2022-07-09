package com.example.discordgui.controller;

import com.example.discordgui.Command;
import com.example.discordgui.UI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private UI ui;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    void exit(ActionEvent event) throws IOException {
        ui.methodWrite(Command.EXIT.getStr());
        Platform.exit();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        if (!(userName.getText().isEmpty() || password.getText().isEmpty())){
            ui.methodWrite("Info");
            ui.methodWrite(userName.getText());
            ui.methodWrite(password.getText());
        }
        else {
            ui.sendPopUp("ERROR","Please fill out the required fields");
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        ui.methodWrite(Command.SIGNUP.getStr());
    }

    public void init(UI ui){
        this.ui=ui;
    }

}
