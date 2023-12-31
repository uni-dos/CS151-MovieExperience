package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import edu.sjsu.cs151.network.MovieRequest;
import edu.sjsu.cs151.network.RetrofitInstance;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TextField userGenreTextField;
    @FXML
    private Button userSubmitButton;

    private String userMovieGenre;


    private ObservableList movies = FXCollections.observableArrayList();
    //elements on availableMovieList page;
    @FXML
    private ListView<String> myMovieListView = new ListView<>(movies);
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

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieYear() {
        return movieYear;
    }
    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }


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
        userMovieGenre = "";
        selectedMovie = "";

        root = FXMLLoader.load(getClass().getResource("homeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //reads startTime and endTime range used in generating the output of available movies in availableMovieList
    public void onUserSubmitButtonClick(ActionEvent event) throws IOException {
        userMovieGenre = userGenreTextField.getText();

        root = FXMLLoader.load(getClass().getResource("userMovieList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //initialize the list with movies this line is just an example, but we have to take movies from the database.
    //List<String> movies= new ArrayList<>(Arrays.asList("Spider-man", "How To Train Your Dragon", "Wall-E"));
    /*
        if (movie from database > movieStartTime && movie from database < movieEndTime)
        {
            movies.add("movie StringName from database");
        }
     */

    private void fetchMoviesAndUpdateListView() {
        MovieRequest connection = RetrofitInstance.getRetrofitInstance();

        connection.getMovieData().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = response.body();
                    if (movieList != null && !movieList.isEmpty()) {
                        ObservableList<String> movieTitles = FXCollections.observableArrayList();
                        for (Movie movie : movieList) {
                            String title = movie.getTitle();
                            movieTitles.add(title);
                            System.out.println("Retrieved movie title: " + title); // Check retrieved movie titles
                        }
                        Platform.runLater(() -> myMovieListView.setItems(movieTitles)); // Update UI on the main thread
                    }
                } else {
                    // Handle an unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                // Handle failure scenario
            }
        });
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Perform initializations or setup here when the FXML file is loaded

        // Fetch movies and update the ListView
        fetchMoviesAndUpdateListView();

        // Set up a listener for handling movie selection events in the ListView
        myMovieListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                    // Handle movie selection event here
                    selectedMovie = newValue; // Update selected movie value if needed
                    // Other actions on movie selection
                }
        );
    }

/*
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //movies.add(Database.retrieveMovies());
        ArrayList<Movie> list = Database.retrieveMovies();

        if (list != null) {
            for (Movie m : list) {
                myMovieListView.getItems().add(m.getTitle());
            }
        }
        myMovieListView.getItems().addAll();      //adds movies from the movies array

        //myMovieListView.getItems().addAll(Arrays.asList("Spider-man", "How To Train Your Dragon", "Wall-E"));
        myMovieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedMovie = myMovieListView.getSelectionModel().getSelectedItem();
            }
        });
    }
 */

    public void onUserConfirmMovieSelection(ActionEvent event) throws IOException {
        selectedMovieLabel.setText("Selected Movie: " + selectedMovie);
        selectedMovieLabel.setVisible(true);
    }


    //clears values in startTime and endTime range, and sends the user back to the userPage, where they are prompted for a new time range
    public void onUserNewTimeRangeButtonClick(ActionEvent event) throws IOException {
        userMovieGenre = "";
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
        String adminMovieTitleInput = networkMovieSearchTextField.getText();
        String adminMovieYearInput = networkMovieYearTextField.getText();
        networkSearchMovieButton.setVisible(false);


        String movieToSearch = networkMovieSearchTextField.getText();
        queriedMovieLabel.setText("Queried Movie: " + movieToSearch);
        queriedMovieLabel.setVisible(true);
        adminNewMovieSearchButton.setVisible(true);
        MovieRequest connection = RetrofitInstance.getRetrofitInstance();


        connection.getMovieData(movieToSearch, movieYear).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // Movie found
                        temp = response.body();
                        movieName = response.body().getTitle();
                        movieYear = response.body().getYear();
                        movieFound = true;
                    } else {
                        // Movie not found
                        movieFound = false;
                    }
                } else {
                    // Some error in response, movie not found
                    movieFound = false;
                }
                System.out.println(response.body());

                // Update UI or perform actions here based on movieFound
                updateUIBasedOnMovieFound(adminMovieTitleInput, adminMovieYearInput);
            }


            @Override
            public void onFailure(Call<Movie> call, Throwable throwable) {
                movieFound = false; // Movie not found due to failure


                // Handle failure scenario if needed
                updateUIBasedOnMovieFound(adminMovieTitleInput, adminMovieYearInput);
            }
        });
    }


    private void updateUIBasedOnMovieFound(String adminMovieTitleInput, String adminMovieYearInput) {
        if (movieName == null) {
            movieFound = false;
        }
        Platform.runLater(() -> {
            if (movieFound) {
                movieFoundLabel.setVisible(true);
                movieFoundLabel.setText("Movie Found");
                addOrRemoveMovieNameLabel.setVisible(true);
                addOrRemoveMovieNameLabel.setText("Remove movie: " + movieName + " (" + movieYear + ")");
                networkRemoveMovieButton.setVisible(true);
                temp = null;
            } else {
                movieFoundLabel.setVisible(true);
                movieFoundLabel.setText("Movie Not Found");
                addOrRemoveMovieNameLabel.setVisible(true);
                addOrRemoveMovieNameLabel.setText("Add Movie: " + adminMovieTitleInput + " " + adminMovieYearInput);
                networkAddMovieButton.setVisible(true);
            }
        });
    }

    //takes from the strings from movieName and year textfields, and adds movie to the database.
    public void onAdminAddMovieButtonClick() {
        if (temp != null && temp.getTitle() != null) {

            // add to database here
            Database.addMovie(temp);
            Database.saveMovie();
        }
    }

    //removes the corresponding string inputted from the movieName and year textfields.
    public void onAdminRemoveMovieButtonClick() {
        //find movieName in database and delete it
    }


    public void onAdminNewMovieSearchButton() {
        networkSearchMovieButton.setVisible(true);
        adminNewMovieSearchButton.setVisible(false);
        movieFoundLabel.setVisible(false);
        addOrRemoveMovieNameLabel.setVisible(false);
        networkRemoveMovieButton.setVisible(false);
        networkAddMovieButton.setVisible(false);
        queriedMovieLabel.setVisible(false);
        networkMovieSearchTextField.clear();
        networkMovieYearTextField.clear();
        queriedMovieLabel.setText("DefaultQueriedMovieText");
        movieFoundLabel.setText("DefaultFoundNotFoundText");
        addOrRemoveMovieNameLabel.setText("DefaultAddOrRemoveMovieText");
        movieName = "";
        movieYear = "";
        //movieFound = false;
    }

//END OF ADMIN RELATED CODE -------------------------------------------------
}