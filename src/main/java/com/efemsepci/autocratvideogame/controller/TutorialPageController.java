package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TutorialPageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button backButton;

    @FXML
    private void backButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("otokratLoginScreen.fxml");
    }


}
