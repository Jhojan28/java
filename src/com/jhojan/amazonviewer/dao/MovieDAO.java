package com.jhojan.amazonviewer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jhojan.amazonviewer.db.IDBConnection;
import com.jhojan.amazoviewer.model.Movie;
import static com.jhojan.amazonviewer.db.DataBase.*;

public interface MovieDAO extends IDBConnection {
	default Movie setMovieViewed(Movie movie) {
		try(Connection conn = connectToDB()) {
			Statement statement = conn.createStatement();
			String query = "INSERT INTO " + TVIEWED + 
					"(" + TVIEWED_ID_MATERIAL + "," + TVIEWED_ID_ELEMENT + "," + TVIEWED_ID_USER + ")" + 
					" VALUES ("+ TMATERIAL_ID[0] +", "+ movie.getId() +", "+ TUSER_ID +")";
			if(statement.executeUpdate(query) > 0) { 
				System.out.println("Se marcó en visto");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return movie;
	}
	
	default ArrayList<Movie> read() {
		ArrayList<Movie> movies = new ArrayList();
		try (Connection conn = connectToDB()) {
			String query = "SELECT * FROM " + TMOVIE;
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) { 
				Movie movie = new Movie(rs.getString(TMOVIE_TITLE), rs.getString(TMOVIE_GENRE), rs.getString(TMOVIE_CREATOR), rs.getInt(TMOVIE_DURATION), rs.getShort(TMOVIE_YEAR));
				movie.setId(rs.getInt(TMOVIE_ID));
				movie.setViewed(getMovieViewed(preparedStatement, conn, rs.getInt(TMOVIE_ID)));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	default boolean getMovieViewed(PreparedStatement preparedStatement, Connection conn, int id_movie) {
		boolean viewed = false;
		String query = "SELECT * FROM " + TVIEWED + 
				" WHERE " + TVIEWED_ID_MATERIAL + " = ?" +
				" AND " + TVIEWED_ID_ELEMENT + " = ?" +
				" AND " + TVIEWED_ID_USER + " = ?";
		ResultSet rs =  null;
		
		try { 
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, TMATERIAL_ID[0]);
			preparedStatement.setInt(2, id_movie);
			preparedStatement.setInt(3, TUSER_ID);
			
			rs = preparedStatement.executeQuery();
			viewed = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewed;
	}
}
