package com.efemsepci.autocratvideogame.controller;

import com.efemsepci.autocratvideogame.Main;
import com.efemsepci.autocratvideogame.classes.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;

import java.util.ResourceBundle;

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
            Main m = new Main();
            m.changeScene("test.fxml");
        }
        else{
            System.out.println("Register please!");
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws Exception{
        Main m = new Main();
        m.changeScene("otokratLoginScreen.fxml");
    }
}
