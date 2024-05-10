package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Client;
import com.efemsepci.autocratvideogame.classes.GameSession;
import com.efemsepci.autocratvideogame.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.efemsepci.autocratvideogame.Client.*;
import static com.efemsepci.autocratvideogame.Server.*;
import static com.efemsepci.autocratvideogame.classes.GameSessionDAO.getGameSessionById;
import static com.efemsepci.autocratvideogame.classes.GameSessionDAO.removePlayer;
import static com.efemsepci.autocratvideogame.classes.UserDAO.getUserByID;

public class GameRoomController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameSession currentGameSession;
        try {
             currentGameSession = getGameSessionById(selectedSession.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        roomName.setText(currentGameSession.getName());
        numOfPlayers.setText(currentGameSession.getNumOfPlayers() + "/4");
        System.out.println(user2name);
        if(currentGameSession.getNumOfPlayers() == 1){
                player1.setText(user1name);
            }
        else if(currentGameSession.getNumOfPlayers() == 2){
                player2.setText(user2name);
            }
        else if(currentGameSession.getNumOfPlayers() == 3){
                player3.setText(user3name);
            }
        else if(currentGameSession.getNumOfPlayers() == 4){
                player4.setText(user4name);
            }
    }

    @FXML
    private Text roomName;

    @FXML
    private Text numOfPlayers;

    @FXML
    private Text player1;

    @FXML
    private Text player2;

    @FXML
    private Text player3;

    @FXML
    private Text player4;

    @FXML
    private Button backButton;

    @FXML
    private Button startGameButton;

    @FXML
    private void backButtonAction(ActionEvent event) throws Exception{
        User tmpUser = getUserByID(loginID);
        removePlayer(selectedSession,tmpUser);
        Client m = new Client();
        m.changeScene("otokratGameScreen.fxml");

    }

    @FXML
    private void startGameButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("test.fxml");
    }
}
