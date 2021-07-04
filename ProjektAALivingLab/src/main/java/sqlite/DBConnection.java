package sqlite;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * 
 * @author sebastian
 * Klasse für den Connector zur Datenbank
 *
 */
public class DBConnection {
	
	/**
	 * greift auf die Projektinteren history.db zu. !!Nur zu Testzwecken!! 
	 * @return Verbindung zu veralteter history.db im diesem java-Projektordner.
	 */
	public static Connection connect() {
		Connection con=null;
			try {
				Class.forName("org.sqlite.JDBC");
				con=DriverManager.getConnection("jdbc:sqlite:history.db");//connection to database
				System.out.println("Connected!");
			} catch (ClassNotFoundException  | SQLException e) {
				System.out.println("" + e);
			}
			
			return con;
	
		}
	/**
	 * 
	 * @param url benötigt Pfad zur Datenbank
	 * @return Verbindung zur Datenbank
	 */
	public static Connection connect(String url) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:" + url);//connection to database
			System.out.println("Connected!");
		} catch (ClassNotFoundException  | SQLException e) {
			System.out.println("" + e);
		}
		return con;
	}
	

}
