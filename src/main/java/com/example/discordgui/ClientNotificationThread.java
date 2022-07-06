package com.example.discordgui;

import com.example.discordgui.controller.PopUpController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

public class ClientNotificationThread implements Runnable{
    private Socket socket;
    private ObjectInputStream in;

    public ClientNotificationThread() throws IOException {
        socket = new Socket("localhost",8787);
        in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            String com = in.readUTF();
            while (!com.equals("exit")){
                showPopup(in.readUTF(),in.readUTF());
                in.readUTF();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void showPopup (String title,String desc) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/PopUpDialoge.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),400,150);
        PopUpController controller = fxmlLoader.getController();
        controller.setTitle(title);
        controller.setDesc(desc);
        AtomicReference<Stage> stage = new AtomicReference<>();
        Platform.runLater(() -> {
            stage.set(new Stage());
            stage.get().setX(Screen.getPrimary().getVisualBounds().getMinX() + Screen.getPrimary().getVisualBounds().getWidth() - 400);
            stage.get().setY(Screen.getPrimary().getVisualBounds().getMinY() + Screen.getPrimary().getVisualBounds().getHeight() - 150);
            stage.get().initStyle(StageStyle.UNDECORATED);
            stage.get().setAlwaysOnTop(true);
            stage.get().setScene(scene);
            stage.get().show();
        });
        Thread.sleep(5*1000);
        Platform.runLater(() -> {
            stage.get().close();
        });
    }
}
