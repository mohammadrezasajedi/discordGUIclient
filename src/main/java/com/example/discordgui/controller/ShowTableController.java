package com.example.discordgui.controller;

import com.example.discordgui.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ShowTableController {
    UI ui;

    @FXML
    private Text header;

    @FXML
    private VBox table;

    @FXML
    void exit(ActionEvent event) throws IOException {
        ui.methodWrite("exit");
    }

    public void init (ArrayList<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            Text text = new Text();
            text.setText(strings.get(i));
            text.setStyle("-fx-font-size: 20px ; -fx-text-fill: White");
            table.getChildren().add(text);
        }
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
