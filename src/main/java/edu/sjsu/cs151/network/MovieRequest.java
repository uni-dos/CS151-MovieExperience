package edu.sjsu.cs151.network;


import edu.sjsu.cs151.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRequest {


    @GET("/")
    Call<Movie> getMovieData(@Query("t") String title, @Query("y") String year);
}
