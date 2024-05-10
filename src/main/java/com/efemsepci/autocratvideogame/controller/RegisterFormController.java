package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Client;
import com.efemsepci.autocratvideogame.classes.User;
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

public class RegisterFormController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;


    @FXML
    private void registerButtonAction(ActionEvent event) throws Exception{
        String emailEntered = email.getText();
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        if(!checkUsernameExists(usernameEntered)){
            User userTmp = new User(emailEntered, usernameEntered, passwordEntered);
            addUser(userTmp);
            loginID = getUserID(usernameEntered,passwordEntered);
            Client m = new Client();
            m.changeScene("otokratGameScreen.fxml");
        }
        else{
            System.out.println("Enter another username!");
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws Exception{
        Client m = new Client();
        m.changeScene("otokratLoginScreen.fxml");
    }
}
