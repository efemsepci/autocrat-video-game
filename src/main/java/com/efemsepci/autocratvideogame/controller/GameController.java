package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Client;
import com.efemsepci.autocratvideogame.classes.GameSession;
import com.efemsepci.autocratvideogame.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.efemsepci.autocratvideogame.Client.loginID;
import static com.efemsepci.autocratvideogame.Client.selectedSession;
import static com.efemsepci.autocratvideogame.classes.GameSessionDAO.addPlayer;
import static com.efemsepci.autocratvideogame.classes.GameSessionDAO.getGameSessions;
import static com.efemsepci.autocratvideogame.classes.UserDAO.getUserByID;

public class GameController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameSessionsList();
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                try {
                    User loginedUser = getUserByID(loginID);
                    System.out.println(loginID);
                    selectedSession = newValue;
                    System.out.println(loginedUser.getId());
                    System.out.println(selectedSession.getName());
                    if(newValue.getNumOfPlayers() < 4){
                        addPlayer(newValue, loginedUser);
                        Client m = new Client();
                        m.changeScene("otokratGameRoom.fxml");
                    }
                }catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    ListView<GameSession> listView;

    @FXML
    private Button quitButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private Button profileButton;

    @FXML
    private void quitButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("otokratLoginScreen.fxml");
    }

    @FXML
    private void createRoomButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("otokratCreateRoom.fxml");
    }

    @FXML
    private void gameSessionsList(){
        ObservableList<GameSession> gameSessionNames = FXCollections.observableArrayList();
        try {
            List<GameSession> gameSessions = getGameSessions();
            System.out.println(gameSessions);
            for (GameSession session : gameSessions) {
                System.out.println(session.getNumOfPlayers());
                gameSessionNames.add(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listView.setItems(gameSessionNames);
    }

}

