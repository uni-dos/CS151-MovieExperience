package edu.sjsu.cs151.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Movie implements Serializable {

    @SerializedName("Title")
    String title;

    @SerializedName("Rated")
    String rated;
    @SerializedName("Year")
    String year;

    @SerializedName("Runtime")
    String runtime;

    @SerializedName("Poster")
    String posterURL;

    @SerializedName("Plot")
    String plot;

    @SerializedName("Genre")
    String genre;


    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getRated() {
        return rated;
    }

    public String getYear() {
        return year;
    }

    public String getPlot(){return plot;}

    public String getRuntime() {
        return runtime;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String toString() {
        return getTitle() + " " + getYear() + " " + getPlot() + " " + getGenre() + " " + getRated();
    }
}
