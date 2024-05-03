package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception{
        Main m = new Main();
        m.changeScene("otokratLoginForm.fxml");
    }

    @FXML
    private void registerButtonAction(ActionEvent event) throws Exception{
        Main m = new Main();
        m.changeScene("otokratRegisterForm.fxml");
    }

    @FXML
    private void tutorialButtonAction(ActionEvent event) throws Exception{
        Main m = new Main();
        m.changeScene("otokratTutorialPage.fxml");
    }
}
