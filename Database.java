package edu.sjsu.cs151.database;

import edu.sjsu.cs151.models.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";

    private static Database instance;
    private Connection conn;

    private Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY, title TEXT, year TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void saveMovie(Movie movie) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO movies (title, year) VALUES (?, ?)");
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getYear());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

