package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import edu.sjsu.cs151.network.MovieRequest;
import edu.sjsu.cs151.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// test class to see if networking works
public class Main {

    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";
    private static final String DB_USER = "User";
    private static final String DB_PASSWORD = "Password";
    private static void saveMovieToDatabase(Connection conn, Movie movie) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO movies (title, rated, year, genre, plot, poster_url) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getRated());
            stmt.setString(3, movie.getYear());
            stmt.setString(4, movie.getGenre());
            stmt.setString(5, movie.getPlot());
            stmt.setString(6, movie.getPosterURL());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving movie to database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MovieRequest connection = RetrofitInstance.getRetrofitInstance();

        MovieExperienceApplication.main(args);
        Call<Movie> m = connection.getMovieData("M3GAN", null);

        m.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                if (response.isSuccessful()) {
                    Movie movie = response.body();

                    System.out.println(movie.getTitle());
                    System.out.println(movie.getRated());
                    System.out.println(movie.getPosterURL());
                    System.out.println(movie.getGenre());
                    System.out.println(movie.getPlot());

                } else {
                    System.out.println(response.errorBody());
                }


            }


            @Override
            public void onFailure(Call<Movie> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });

        connection.getMovieData("Ant-man", "2023").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();

                    System.out.println(movie.getTitle());
                    System.out.println(movie.getRated());
                    System.out.println(movie.getPosterURL());
                    System.out.println(movie.getGenre());
                    System.out.println(movie.getPlot());

                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable throwable) {

            }
        });

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            m.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.isSuccessful()) {
                        Movie m3ganMovie = response.body();
                        saveMovieToDatabase(conn, m3ganMovie);
                    } else {
                        System.out.println("Error retrieving M3GAN data: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable throwable) {
                    System.out.println("Failed to retrieve M3GAN data: " + throwable.getMessage());
                }
            });

            // Retrieve movie data for "Ant-man (2023)"
            Call<Movie> antmanCall = connection.getMovieData("Ant-man", "2023");
            antmanCall.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.isSuccessful()) {
                        Movie antmanMovie = response.body();
                        saveMovieToDatabase(conn, antmanMovie);
                    } else {
                        System.out.println("Error retrieving Ant-man data: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable throwable) {
                    System.out.println("Failed to retrieve Ant-man data: " + throwable.getMessage());
                }
            });

        } catch (
                SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }


    }
}


