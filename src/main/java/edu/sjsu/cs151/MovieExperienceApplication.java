package edu.sjsu.cs151;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

import static com.sun.javafx.application.PlatformImpl.exit;

public class MovieExperienceApplication extends Application {

    @Override
    public void start (Stage stage)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Movie Experience");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
        // need this or else the retrofit instance keeps running
        System.exit(0);
    }
}