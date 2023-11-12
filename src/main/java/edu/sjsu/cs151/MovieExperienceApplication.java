package edu.sjsu.cs151;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class MovieExperienceApplication extends Application {

    @Override
    public void start (Stage stage) throws IOException
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(MovieExperienceApplication.class.getResource("homeView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Movie Experience");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}