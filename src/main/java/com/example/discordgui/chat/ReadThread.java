package com.example.discordgui.chat;

import com.example.discordgui.Command;
import com.example.discordgui.UI;
import com.example.discordgui.controller.ChatPageController;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadThread implements Runnable{
    private UI ui;
    private ChatPageController chatPageController;

    public ReadThread(UI ui, ChatPageController chatPageController) {
        this.ui = ui;
        this.chatPageController = chatPageController;
    }

    @Override
    public void run() {
        try {
            String str = methodRead();
            while (!str.equals(Command.EXITCHATMODE.getStr())){
                chatPageController.newMessage(str);
                str = methodRead();
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String methodRead () throws IOException {
        return ui.methodRead();
    }
}
