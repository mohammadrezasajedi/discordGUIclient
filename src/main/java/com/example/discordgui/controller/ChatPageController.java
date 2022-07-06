package com.example.discordgui.controller;

import com.example.discordgui.Client;
import com.example.discordgui.UI;
import com.example.discordgui.chat.ReadThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ChatPageController {

    private UI ui;
    private ReadThread readThread;


    @FXML
    private VBox chat;

    @FXML
    private ImageView avatar;

    @FXML
    private TextField field;

    @FXML
    private Text name;

    @FXML
    void attach(ActionEvent event) throws IOException {
        ui.methodWrite("#sendFile");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter ef = new FileChooser.ExtensionFilter("All Files","*.*");
        fileChooser.getExtensionFilters().add(ef);
        fileChooser.setSelectedExtensionFilter(ef);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file.exists()){
            Thread t = new Thread(() -> {
                try {
                    ui.methodWrite(file.getName());
                    ui.sendFile(file);
                    ui.sendPopUp("File Sent",file.getName() + " has been Sent Successfully.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        } else {
            ui.sendPopUp("Error","File not Found.");
        }
    }

    @FXML
    void send(ActionEvent event) throws IOException {
        String str = field.getText();
        ui.methodWrite(str);
        field.setText("");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        sendCommand("#exit");
    }

    @FXML
    void getpin(ActionEvent event) throws IOException {
        sendCommand("#getpm");
    }

    public void sendCommand(String str) throws IOException {
        ui.methodWrite(str);
    }

    public void download (String str) throws IOException {
        ui.methodWrite(str);
        Thread t = new Thread(() -> {
            final String name;
            try {
                name = ui.receiveFileName();
                File file = new File("./ClientContent/" + name);
                ui.receiveFile(file);
                ui.sendPopUp("File Received",file.getName() + " has been Received Successfully.");

                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
                builder.redirectErrorStream(true);
                Process p = builder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
    }


    public void init (File avatar,String name){
        if (avatar != null && avatar.exists()){
            this.avatar.setImage(new Image(avatar.getAbsolutePath()));
        }
        this.name.setText(name);
        readThread = new ReadThread(ui,this);
    }

    public void start() {
        readThread.run();
    }

    public void newMessage (String str) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/Message.fxml"));
        BorderPane pane = fxmlLoader.load();
        MessageController messageController = fxmlLoader.getController();
        messageController.init(str,this);
        Platform.runLater(() -> chat.getChildren().add(pane));
    }


    public void setUi(UI ui) {
        this.ui = ui;
    }
}
