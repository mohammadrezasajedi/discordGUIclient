package com.example.discordgui;

import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class ClientController {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private UI ui;

    public ClientController() throws IOException {
        this.socket = new Socket("localhost",8989);
        new Socket("localhost",8888);
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void start(){
        System.out.println("Connected");
        try {
            String str = methodRead();
            ui.welcome(str);
            boolean end=false;
            while (!end) {
                Command command = Command.valueOfLabel(methodRead());
                String respond = null;
                if (command == null) {
                    continue;
             }

                switch (command) {
                    case GETUSERNAME: {
//                    respond = UI.getUserName();
                        break;
                    }
                    case GETUSERNAMEAGAIN: {
//                    respond = UI.getUserName(methodRead());
                        break;
                    }
                    case GETPASSWORD: {
//                    respond = UI.getPassword();
                        break;
                    }
                    case GETPASSWORDAGAIN: {
//                    respond = UI.getPassword(methodRead());
                        break;
                    }
                    case GETEMAIL: {
//                    respond = UI.getEmail();
                        break;
                    }
                    case GETEMAILAGAIN: {
//                    respond = UI.getEmail(methodRead());
                        break;
                    }
                    case PRINT: {
//                    UI.print(methodRead());
                        break;
                    }
                    case SHOWMENU: {
                        int num = Integer.parseInt(methodRead());
                        ArrayList<String> items = new ArrayList<>(num);
                        for (int i = 0; i < num; i++) {
                            items.add(methodRead());
                        }
                        ui.showMenu(items);
                        break;
                    }
                    case CREATEFRIEND: {
//                    respond = UI.getCreateFriend();
                        break;
                    }
                    case ENTERCHATMODE: {
//                    chatMode();
                        break;
                    }
                    case GETSERVERNAME: {
//                    methodWrite(UI.getServerName());
//                    respond = UI.getWelcome();
                        break;
                    }
                    case GETSERVERNAMEAGAIN: {
//                    methodWrite(UI.getServerName(methodRead()));
//                    respond = UI.getWelcome();
                        break;
                    }
                    case GETROLENAME: {
//                    respond = UI.getRoleName();
                        break;
                    }
                    case GETROLENAMEAGAIN: {
//                    respond = UI.getRoleName(methodRead());
                        break;
                    }
                    case GETCHANNELNAME: {
//                    respond = UI.getChannelName();
                        break;
                    }
                    case GETCHANNELNAMEAGAIN: {
//                    respond = UI.getChannelName(methodRead());
                        break;
                    }
                    case GETPHONE: {
//                    respond = UI.getPhone();
                        break;
                    }
                    case GETPHONEAGAIN: {
//                    respond = UI.getPhone(methodRead());
                        break;
                    }
                    case GETPROFILEPICTURE: {
//                    fileStream.sendFile(UI.getProfilePicture());
                        break;
                    }
                    case EXIT: {
//                    UI.exit();
                        System.exit(0);
                        break;
                    }

                }
            }
        } catch (IOException e){
            e.printStackTrace();
            System.err.println("Server Unreachable!");
            System.exit(-1);
        }
    }



    public String methodRead () throws IOException {
        String str = reader.readLine();
        while (str.equals("")){
            str = reader.readLine();
        }
        return str;
    }

    public void methodWrite (String str) throws IOException {
        writer.write(str);
        writer.newLine();
        writer.flush();
    }

    public void setStage(Stage stage){
        this.ui=new UI(stage,this);
    }

}

