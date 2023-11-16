package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import edu.sjsu.cs151.network.MovieRequest;
import edu.sjsu.cs151.network.RetrofitInstance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class MovieExperienceController {

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private TextField networkMovieSearchTextField;

    private Movie temp = null;


    //homepage page when choosing between user or admin
    public void onUserButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //second page when choosing to go back from user or admin login page
    public void onBackButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //second page when logging in as a user, sends to third page
    public void onUserLoginButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //set all field entries to respective variables
        //check for username and password restrictions

    }

    //third page, when choosing to logout. Sends back to homepage
    public void onUserLogoutButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //clear user info and stuff
    }

    public void onUserSubmitButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userMovieList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onUserNewTimeRangeButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void onAdminButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAdminLoginButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void onAdminLogoutButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAdminSearchMovieButtonClick() {

        String movieToSearch = networkMovieSearchTextField.getText();

        MovieRequest connection = RetrofitInstance.getRetrofitInstance();

        Database db = Database.getInstance();
        connection.getMovieData(movieToSearch, null).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                assert response.body() != null;
                temp = response.body();
                System.out.println(response.body());
                db.saveMovie(response.body());

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable throwable) {

            }
        });
    }

    public void onAdminAddMovieButtonClick() {
        if (temp != null) {
            // add to database here
        }
    }

    public void onAdminRemoveMovieButtonClick() {

    }
}