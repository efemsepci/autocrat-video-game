package com.efemsepci.autocratvideogame;

import com.efemsepci.autocratvideogame.classes.GameSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Application {

    private static Stage stg;
    public static int loginID;
    public static GameSession selectedSession;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);

        socket = new Socket("192.168.1.156", 12345);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Parent root = FXMLLoader.load(getClass().getResource("otokratLoginScreen.fxml"));
        primaryStage.setTitle("Autocrat Video Game");
        primaryStage.setScene(new Scene(root, 761, 557));
        primaryStage.show();

        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void sendMessage(String message) throws IOException {
            out.writeUTF(message);
        }
}

