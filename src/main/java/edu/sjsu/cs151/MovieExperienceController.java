package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import edu.sjsu.cs151.network.MovieRequest;
import edu.sjsu.cs151.network.RetrofitInstance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MovieExperienceController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    private Movie temp = null;



    //elements on homePage
    @FXML
    private Button userButton;
    @FXML
    private Button adminButton;


    //elements on userPage;
    @FXML
    private TextField userStartTimeTextField;
    @FXML
    private TextField userEndTimeTextField;
    @FXML
    private Button userSubmitButton;

    private String movieStartTime;
    private String movieEndTime;


    //elements on availableMovieList page;
    @FXML
    private ListView<String> myMovieListView = new ListView<>();
    @FXML
    private Button confirmMovieSelectionButton;
    @FXML
    private Button newTimeRangeButton;
    @FXML
    private Label selectedMovieLabel;

    private String selectedMovie;



    //elements on adminPage
    @FXML
    private TextField networkMovieSearchTextField;
    @FXML
    private TextField networkMovieYearTextField;
    @FXML
    private Button networkSearchMovieButton;
    @FXML
    private Button networkAddMovieButton;
    @FXML
    private Label movieFoundLabel;
    @FXML
    private Button networkRemoveMovieButton;
    @FXML
    private Label addOrRemoveMovieNameLabel;
    @FXML
    private Label queriedMovieLabel;
    @FXML
    private Button adminNewMovieSearchButton;

    private String movieName;
    private String movieYear;
    private boolean movieFound;




//START OF USER RELATED CODE ----------------------------------------------

    //Homepage, when selecting user button takes you to userpage
    public void onUserButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

/*  if we want the login page
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
 */

    //Any of the user pages. Clears all values stored from text fields and sends back to homePage.
    public void onUserLogoutButtonClick(ActionEvent event) throws IOException {
        movieStartTime = "";
        movieEndTime = "";
        selectedMovie = "";

        root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //reads startTime and endTime range used in generating the output of available movies in availableMovieList
    public void onUserSubmitButtonClick(ActionEvent event) throws IOException {
        movieStartTime = userStartTimeTextField.getText();
        movieEndTime = userEndTimeTextField.getText();

        root = FXMLLoader.load(getClass().getResource("userMovieList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //initialize the list with movies this line is just an example, but we have to take movies from the database.
    List<String> movies= new ArrayList<>(Arrays.asList("Spider-man", "How To Train Your Dragon", "Wall-E"));
    /*
        if (movie from database > movieStartTime && movie from database < movieEndTime)
        {
            movies.add("movie StringName from database");
        }
     */

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myMovieListView.getItems().addAll(movies);      //adds movies from the movies array

        myMovieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedMovie = myMovieListView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void onUserConfirmMovieSelection(ActionEvent event) throws IOException {
        selectedMovieLabel.setText("Selected Movie: " + selectedMovie);
        selectedMovieLabel.setVisible(true);
    }


    //clears values in startTime and endTime range, and sends the user back to the userPage, where they are prompted for a new time range
    public void onUserNewTimeRangeButtonClick(ActionEvent event) throws IOException {
        movieStartTime = "";
        movieEndTime = "";
        selectedMovie = "";

        root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//END OF USER RELATED CODE ---------------------------------------------------




//START OF ADMIN RELATED CODE ------------------------------------------------

    //from the home page, selecting this button takes you to the admin page
    public void onAdminButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

/*  if we want thelogin page
    public void onAdminLoginButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
*/

    //sends you back to the homeview, clears any values stored from text fields.
    public void onAdminLogoutButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //takes the text from the textfield and searches for it from the database
    public void onAdminSearchMovieButtonClick() {
        networkSearchMovieButton.setVisible(false);
        movieName = networkMovieSearchTextField.getText();
        movieYear = networkMovieYearTextField.getText();

        String movieToSearch = networkMovieSearchTextField.getText();
        queriedMovieLabel.setText("Queried Movie: " + movieToSearch);
        queriedMovieLabel.setVisible(true);
        adminNewMovieSearchButton.setVisible(true);
        MovieRequest connection = RetrofitInstance.getRetrofitInstance();

        Database db = Database.getInstance();
        connection.getMovieData(movieToSearch, null).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                assert response.body() != null;
                temp = response.body();
                System.out.println(response.body());
                db.saveMovie(response.body());
                /////FIX THIS, SET movieFound TRUE IF THE MOVIE IS IN THE DATABASE
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable throwable) {
            }
        });

        if(movieFound) {
            movieFoundLabel.setVisible(true);
            movieFoundLabel.setText("Movie Found");
            addOrRemoveMovieNameLabel.setVisible(true);          //it would be better to grab these movie details from the database, but idk how to do that
            addOrRemoveMovieNameLabel.setText("Remove movie: " + movieName + " " + movieYear);
            networkRemoveMovieButton.setVisible(true);
        }
        else {
            movieFoundLabel.setVisible(true);
            movieFoundLabel.setText("Movie Not Found");
            addOrRemoveMovieNameLabel.setVisible(true);         //it would be better to grab these movie details from the database, but idk how to do that
            addOrRemoveMovieNameLabel.setText("Add movie: " + movieName + " " + movieYear);
            networkAddMovieButton.setVisible(true);
        }
    }

    //takes from the strings from movieName and year textfields, and adds movie to the database.
    public void onAdminAddMovieButtonClick() {
        if (temp != null && temp.getTitle() != null) {
            // add to database here
            Database.getInstance().saveMovie(temp);
        }
    }

    //removes the corresponding string inputted from the movieName and year textfields.
    public void onAdminRemoveMovieButtonClick() {
        //find movieName in database and delete it
    }


    public void onAdminNewMovieSearchButton() {
        adminNewMovieSearchButton.setVisible(false);
        movieFoundLabel.setVisible(false);
        addOrRemoveMovieNameLabel.setVisible(false);
        networkRemoveMovieButton.setVisible(false);
        networkAddMovieButton.setVisible(false);
        queriedMovieLabel.setVisible(false);
        networkMovieSearchTextField.clear();
        networkMovieYearTextField.clear();
        queriedMovieLabel.setText("DefaultQueriedMovieText");
        movieFoundLabel.setText("DefaultFoundOrNotFoundText");
        addOrRemoveMovieNameLabel.setText("DefaultAddOrRemoveMovieText");
        movieName = "";
        movieYear = "";
        movieFound = false;
        networkSearchMovieButton.setVisible(true);
    }

//END OF ADMIN RELATED CODE -------------------------------------------------
}