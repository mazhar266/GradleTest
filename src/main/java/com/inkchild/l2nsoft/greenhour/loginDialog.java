package com.inkchild.l2nsoft.greenhour;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * 
 * @author Mazhar Ahmed
 */
public class loginDialog {
    // the email field
    @FXML private TextField email;
    // the password field
    @FXML private PasswordField password;
    // the login button
    @FXML private Button login;
    
    // the main stage
    Stage stage;
    
    /**
     * @name loginAction
     * @author Mazhar Ahmed <info@mazharAhmed.me>
     * 
     * called when the button is pressed
     */
    @FXML
    protected void loginAction() {
        System.out.println(email.getText());
        System.out.println(password.getText());
        
        
    }
}
