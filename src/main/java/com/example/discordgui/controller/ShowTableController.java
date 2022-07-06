package com.example.discordgui.controller;

import com.example.discordgui.Client;
import com.example.discordgui.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ShowTableController {
    private UI ui;
    private final Image online = new Image(String.valueOf(Client.class.getResource("PNG/online.png")));
    private final Image offline = new Image(String.valueOf(Client.class.getResource("PNG/offline.png")));
    private final Image idle = new Image(String.valueOf(Client.class.getResource("PNG/idle.png")));
    private final Image dnd = new Image(String.valueOf(Client.class.getResource("PNG/dnd.png")));
    private final Image invisible = new Image(String.valueOf(Client.class.getResource("PNG/invisible.png")));

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
            String[] strs = strings.get(i).split(" : ");
            String user = strs[0];
            String status = strs[1];

            table.getChildren().add(createBox(user,status));
        }
    }

    private Pane createBox (String user, String status){
        Pane box = new Pane();

        box.setMinHeight(Region.USE_PREF_SIZE);
        box.setMaxHeight(Region.USE_PREF_SIZE);
        box.setMinWidth(Region.USE_PREF_SIZE);
        box.setMaxWidth(Region.USE_PREF_SIZE);

        box.setPadding(new Insets(10,10,10,10));

        box.getStyleClass().add("tableItem");

        Text text = new Text(user);
        text.getStyleClass().add("tableText");

        ImageView imageView = new ImageView();

        switch (status) {
            case "Online":
                imageView.setImage(online);
                break;
            case "Idle":
                imageView.setImage(idle);
                break;
            case "Do Not Disturb":
                imageView.setImage(dnd);
                break;
            case "Invisible":
                imageView.setImage(invisible);
                break;
            case "offline":
                imageView.setImage(offline);
                break;
        }

        imageView.setFitHeight(15);
        imageView.setFitWidth(15);

        box.getChildren().add(text);
        box.getChildren().add(imageView);

        text.setLayoutX(10);
        text.setLayoutY(50);

        imageView.setLayoutX(350);
        imageView.setLayoutY(35);

        return box;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
