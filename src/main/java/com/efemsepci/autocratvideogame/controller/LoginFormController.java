package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;

import java.util.ResourceBundle;

import static com.efemsepci.autocratvideogame.Client.loginID;
import static com.efemsepci.autocratvideogame.classes.UserDAO.*;

public class LoginFormController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;


    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception{
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        if(validateUser(usernameEntered,passwordEntered)){
            loginID = getUserID(usernameEntered,passwordEntered);
            Client m = new Client();
            m.changeScene("otokratGameScreen.fxml");
        }
        else{
            System.out.println("Register please!");
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("otokratLoginScreen.fxml");
    }
}
