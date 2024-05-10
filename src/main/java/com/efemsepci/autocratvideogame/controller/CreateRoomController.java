package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Client;
import com.efemsepci.autocratvideogame.classes.GameSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.efemsepci.autocratvideogame.classes.GameSessionDAO.addGameSession;

public class CreateRoomController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField roomName;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private void backButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("otokratGameScreen.fxml");
    }

    @FXML
    private void createButtonAction(ActionEvent event) throws Exception{
        String roomNameEntered = roomName.getText();
        //tarih eklenecek
        GameSession gameSession = new GameSession(roomNameEntered,null, null);
        addGameSession(gameSession);
        Client m = new Client();
        m.changeScene("otokratGameScreen.fxml");
    }
}
