package edu.sjsu.cs151.network;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private static Retrofit retrofit;

    private static final String url = "http://www.omdbapi.com";

    public static MovieRequest getRetrofitInstance() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(keyInterceptor())
                    .build();
        }


        return retrofit.create(MovieRequest.class);
    }


    private static OkHttpClient keyInterceptor() {
        Dotenv dotenv = Dotenv.load();
        return new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl url1 = original.url();

                    HttpUrl newUrl = url1.newBuilder()
                            .addQueryParameter("apikey", dotenv.get("API_KEY"))
                            .addQueryParameter("type", "movie")
                            .build();

                    Request.Builder requestBuilder = original.newBuilder().url(newUrl);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

    }
}
