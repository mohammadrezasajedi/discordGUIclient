package com.example.discordgui.controller;

import com.example.discordgui.UI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.util.ArrayList;


public class DinamicMenuController {

    private ArrayList<String> items;
    private UI ui;

    @FXML
    private VBox menu;



    public void setItems(ArrayList<String> items) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            Button button=new Button();
            button.setText(items.get(i).split("\\.")[1]);
            button.setId(String.valueOf(i+1));
            button.setOnAction(actionEvent -> {
                Button button1=(Button) actionEvent.getSource();
                try {
                    ui.methodWrite(button1.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            button.getStyleClass().add("DynamicMenuItem");
            menu.getChildren().add(button);
        }
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }


}
