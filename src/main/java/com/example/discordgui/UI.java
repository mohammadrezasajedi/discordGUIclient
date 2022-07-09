package com.example.discordgui;

import com.example.discordgui.controller.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UI {
    private Stage stage;
    private ClientController clientController;
    private DynamicFormController dynamicFormController;
    private FriendsChartController friendsChartController;

    public UI(Stage stage,ClientController clientController) {
        this.stage = stage;
        this.clientController=clientController;
    }

    public void showMenu(ArrayList<String> items) throws IOException {
        dynamicFormController = null;
        friendsChartController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/DinamicMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DinamicMenuController dinamicMenuController = fxmlLoader.getController();
        dinamicMenuController.setUi(this);
        dinamicMenuController.setItems(items);
        changeStage(scene);
    }

    public void welcome(String str) throws IOException {
        dynamicFormController = null;
        friendsChartController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        WelcomeController welcomeController = fxmlLoader.getController();
        welcomeController.setText(str);
        Platform.runLater(() -> {
            stage.setScene(scene);
            if (!stage.isShowing()) {
                stage.show();
            }
        });
    }

    public void getInfo (String header,String req) throws IOException {
        if (dynamicFormController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/DynamicForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            dynamicFormController = fxmlLoader.getController();
            dynamicFormController.setUi(this);
            dynamicFormController.init(header,req);
            changeStage(scene);
        } else {
            dynamicFormController.next(header,req);
        }
    }

    public void getAgain (String str) {
        dynamicFormController.again(str);
    }

    public void resetMenu () {
        dynamicFormController = null;
    }

    public void chatMode () throws IOException {
        dynamicFormController = null;
        friendsChartController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/ChatPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ChatPageController chatPageController = fxmlLoader.getController();
        chatPageController.setUi(this);
        String header = methodRead();
        File file = null;
        if (methodRead().equals("pic")){
            file = new File("./Profiles/" + receiveFileName());
            receiveFile(file);
        }
        chatPageController.init(file,header);
        changeStage(scene);
        chatPageController.start();
    }

    public void Table (int num) throws IOException {
        String mode = methodRead();
        dynamicFormController = null;
        friendsChartController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/ShowTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ShowTableController showTableController = fxmlLoader.getController();
        showTableController.setUi(this);
        ArrayList<String> strings = new ArrayList<>(num);
        HashMap<String,File> pics = new HashMap<>();
        for (int i = 0; i < num; i++) {
            String str = methodRead();
            strings.add(str);
            if (methodRead().equals("pic")){
                File file = new File("./Profiles/" + receiveFileName());
                receiveFile(file);
                pics.put(str,file);
            }
        }
        showTableController.init(strings,mode,pics);
        changeStage(scene);
    }

    public void getProfilePicture() throws IOException {
        dynamicFormController = null;
        friendsChartController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/SendPicture.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SendPictureController sendPictureController = fxmlLoader.getController();
        File file = null;
        if (methodRead().equals("pic")){
            file = new File("./Profiles/" + receiveFileName());
            receiveFile(file);
        }
        sendPictureController.init(this,file);
        changeStage(scene);
    }

    public void showFriendsChart() throws IOException{
        dynamicFormController = null;
        if (friendsChartController == null){
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/FriendsChart.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            friendsChartController = fxmlLoader.getController();
            friendsChartController.init(this);
            changeStage(scene);
        }

        int num = Integer.parseInt(methodRead());
        ArrayList<String> strings = new ArrayList<>(num);
        HashMap<String,File> pics = new HashMap<>();
        for (int i = 0; i < num; i++) {
            String str = methodRead();
            strings.add(str);
            if (methodRead().equals("pic")){
                File file = new File("./Profiles/" + receiveFileName());
                receiveFile(file);
                pics.put(str,file);
            }
        }
        friendsChartController.setChart(strings,pics);
    }

    public void ProfilePage () throws IOException {
        dynamicFormController = null;
        friendsChartController = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/ProfilePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ProfilePageController profilePageController = fxmlLoader.getController();
        String userName = methodRead();
        String email = methodRead();
        String phone = methodRead();
        String status = methodRead();
        File file = null;
        if (methodRead().equals("pic")){
            file = new File("./Profiles/" + receiveFileName());
            receiveFile(file);
        }

        profilePageController.Init(this,userName,email,phone,status,file);
        changeStage(scene);
    }

    public void login() throws IOException {
        dynamicFormController=null;
        friendsChartController=null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController loginController = fxmlLoader.getController();
        loginController.init(this);
        changeStage(scene);
    }
    public void signUp() throws IOException {
        dynamicFormController=null;
        friendsChartController=null;
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("FXML/SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SignUpController signUpController= fxmlLoader.getController();
        signUpController.init(this);
        changeStage(scene);
    }

    private void changeStage (Scene scene){
        Platform.runLater(() -> stage.setScene(scene));
    }



    public void sendFile (File file) {
        clientController.getFileStreamThread().sendFile(file);
    }

    public void receiveFile(File file){
        clientController.getFileStreamThread().receiveFile(file);
    }

    public String receiveFileName() throws IOException {
        return clientController.getFileStreamThread().methodRead();
    }

    public void sendPopUp (String title,String desc){
        try {
            clientController.getNotificationThread().showPopup(title, desc);
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
    public String methodRead () throws IOException {
        return clientController.methodRead();
    }

    public void methodWrite (String str) throws IOException {
        clientController.methodWrite(str);
    }

}
