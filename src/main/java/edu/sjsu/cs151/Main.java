package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import edu.sjsu.cs151.network.MovieRequest;
import edu.sjsu.cs151.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieRequest connection = RetrofitInstance.getRetrofitInstance();


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


    }
}
