package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import edu.sjsu.cs151.network.MovieRequest;
import edu.sjsu.cs151.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main {
    public static void main(String[] args) {
        MovieRequest connection = RetrofitInstance.getRetrofitInstance();

        Call<Movie> m = connection.getMovieData("Super Mario", "2023");

        m.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                if (response.isSuccessful()) {
                    Movie movie = response.body();

                    System.out.println(movie.getTitle());
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
