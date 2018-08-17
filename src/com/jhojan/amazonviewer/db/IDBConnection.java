package com.jhojan.amazonviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.jhojan.amazonviewer.db.DataBase.*;
public interface IDBConnection {
	default Connection connectToDB() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL+DB_NAME, USER, PASSWORD);
			if(conn != null) { 
				System.out.println("Se establecio la conexión");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}
