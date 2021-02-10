package com.movietheater.MovieTheater.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Database {

    public static Connection getDBConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/movietheater";
            String username = "root";
            String password = "**OMITTED**";
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void commitToDB(String statement) {
        try {
            Connection conn = getDBConnection();
            assert conn != null;
            PreparedStatement posted = conn.prepareStatement(statement);
            posted.executeUpdate();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static ResultSet pullFromDB(String statement) {
        try {
            Connection conn = getDBConnection();
            assert conn != null;
            PreparedStatement posted = conn.prepareStatement(statement);
            return posted.executeQuery();
        } catch(Exception e){
            //System.out.println(e);
            return null;
        }
    }

    public static void restartDB() {
        clearTable("movies");
        clearTable("showtimes");
        clearTable("tickets");
    }

    public static void clearTable(String table) {
        String statement = "DELETE FROM " + table;
        Database.commitToDB(statement);
    }

    public static void resetAutoIncrement(String table) {
        String statement = "ALTER TABLE " + table + " AUTO_INCREMENT = 1";
        commitToDB(statement);
    }

    public static void buildDatabase() throws Exception {
        restartDB();
        resetAutoIncrement("movies");
        resetAutoIncrement("showtimes");
        resetAutoIncrement("tickets");
        String[] titleArray = new String[] {"Avengers: Endgame", "The Social Dilemma",
                "Arrival", "Mission Impossible 45", "The Dark Knight Rises", "Knives Out!",
                "Bee Movie", "Cars 3"};
        String[] imgStringArray = new String[] {"avengers_endgame.jpg",
                "social_dilemma.jpg",
                "arrival.jpg",
                "mission_impossible.jpg",
                "dark_knight_rises.jpg",
                "knives_out.jpg",
                "bee_movie.jpg",
                "cars_3.jpg"};
        Map<String, String> titleImageMap = new HashMap<>();
        titleImageMap.put(titleArray[0],imgStringArray[0]);
        titleImageMap.put(titleArray[1],imgStringArray[1]);
        titleImageMap.put(titleArray[2],imgStringArray[2]);
        titleImageMap.put(titleArray[3],imgStringArray[3]);
        titleImageMap.put(titleArray[4],imgStringArray[4]);
        titleImageMap.put(titleArray[5],imgStringArray[5]);
        titleImageMap.put(titleArray[6],imgStringArray[6]);
        titleImageMap.put(titleArray[7],imgStringArray[7]);
        String[] timeArray = new String[] {"1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM"};
        for (String key: titleImageMap.keySet()) {
            Random r = new Random();
            int rating = r.nextInt(50) + 50;
            String statement = "INSERT INTO movies (movieid, title, rating, image) VALUES (NULL, '" + key + "', " + rating + ", '" + titleImageMap.get(key) + "')";
            commitToDB(statement);
        }
        String pullMoviesStatement = "SELECT * FROM movies ORDER BY movieid";
        ResultSet moviesResult = pullFromDB(pullMoviesStatement);
        while (true) {
            assert moviesResult != null;
            if (!moviesResult.next()) break;
            for (String time: timeArray) {
                String subStatement = "INSERT INTO showtimes (showtimeid, movieid, time) VALUES (NULL, " + moviesResult.getInt("movieid") + ", '" + time + "')";
                commitToDB(subStatement);
            }
        }
        String pullShowtimesStatement = "SELECT * FROM showtimes ORDER BY showtimeid";
        ResultSet showtimesResult = pullFromDB(pullShowtimesStatement);
        while (true) {
            assert showtimesResult != null;
            if (!showtimesResult.next()) break;
            Random tr = new Random();
            int quantity = tr.nextInt(6) + 2;
            String subSubStatement = "INSERT INTO tickets (ticketid, showtimeid, movieid, quantity) VALUES (NULL, " + showtimesResult.getInt("showtimeid") + ", " + showtimesResult.getInt("movieid") + ", " + quantity + ")";
            commitToDB(subSubStatement);
        }
    }
}
