package com.efemsepci.autocratvideogame;

import com.efemsepci.autocratvideogame.classes.GameSession;
import com.efemsepci.autocratvideogame.classes.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.efemsepci.autocratvideogame.classes.UserDAO.getUserByID;

public class Client extends Application {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Stage stg;
    public static int loginID;
    public static GameSession selectedSession;
    public static String user1name;
    public static String user2name;
    public static String user3name;
    public static String user4name;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("otokratLoginScreen.fxml"));
        primaryStage.setTitle("Autocrat Video Game");
        primaryStage.setScene(new Scene(root, 761, 557));
        primaryStage.show();

    }

    public void initializeClient() throws IOException {
        socket = new Socket("192.168.1.156", 12345);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    processMessage(message);
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    /*public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }*/


    public void sendUserDataToServer() throws IOException, SQLException {
        List<Object> dataList = new ArrayList<>();

        // Kullanıcı bilgilerini ekle
        User loginedUser = getUserByID(loginID);
        dataList.add(loginedUser);

        // Seçilen oturum bilgilerini ekle (varsa)
        if (selectedSession != null) {
            dataList.add(selectedSession);
        }

        // Objeleri JSON formatına dönüştür
        String userDataJSON = convertObjectListToJson(dataList);
        System.out.println(userDataJSON);
        // JSON'u sunucuya gönder
        out.writeUTF(userDataJSON);
    }

    private String convertObjectListToJson(List<Object> objectList) {
        try {
            return objectMapper.writeValueAsString(objectList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void processMessage(String message) {
        try {
            // Gelen mesajı JSON formatından nesnelere çevir
            JsonNode rootNode = objectMapper.readTree(message);

            // İlk olarak, her bir kullanıcı adını al
            user1name = rootNode.get("user1").asText();
            user2name = rootNode.get("user2").asText();
            user3name = rootNode.get("user3").asText();
            user4name = rootNode.get("user4").asText();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }


}

