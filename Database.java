package edu.sjsu.cs151;

import edu.sjsu.cs151.models.Movie;
import java.io.*;
import java.util.ArrayList;


public class Database {

    private static ArrayList<Movie> movies = new ArrayList<>();

    public static void addMovie(Movie movie) {
        movies.add(movie);
    }
    private static void saveMovie() {
        try {
            FileOutputStream fileOut = new FileOutputStream("movies.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(movies);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //to retrieve the saved ArrayList of Movie objects from disk
    public static ArrayList<Movie> retrieveMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("movies.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            movies = (ArrayList<Movie>) in.readObject();

            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        return movies;
    }
}

