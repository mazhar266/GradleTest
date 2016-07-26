/**
 * @file Main.java
 * @author Mazhar Ahmed
 */

package com.inkchild.l2nsoft.greenhour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // load the login window from fxml
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        // set the window title
        primaryStage.setTitle("Green Hour");
        // set the scene for this window
        primaryStage.setScene(new Scene(root, 437, 500));
        // disable the resizing feature of this window
        primaryStage.setResizable(false);
        // show the window
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
