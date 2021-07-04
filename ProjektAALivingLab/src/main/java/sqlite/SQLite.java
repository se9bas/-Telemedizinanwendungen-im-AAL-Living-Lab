package sqlite;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import messobjekte.*;



public class SQLite {
	
	//TODO change URL to path to history.db on raspberry pi
//	private final static String URL = "C:\\Users\\User\\Desktop\\Datenklo\\klo\\gui\\history.db"; 
	
	private static Connection con = DBConnection.connect();
		
	public static ResultSet selectLastEntry(String tabellenName) {
		ResultSet rs=null;
		
		try {
			String sql = "SELECT * "
					+ "FROM "+tabellenName
					+ " WHERE id=(SELECT max(id) "
								+ "FROM " + tabellenName
								+ ")";	
			
//			Connection con = DBConnection.connect(URL);
			Statement sm = con.createStatement();
			ResultSet tmp = sm.executeQuery(sql);
			rs=tmp;
		
//			tmp.close();
//			con.close();
//			sm.close();
		
		}catch(SQLException e) {
			System.out.println(e);
		};
		
		return rs;
	}
	
	public static ResultSet selectLastEntry(Messung tabelle) {
		String tabellenName=tabelle.getClassName();
		ResultSet rs=null;
		
		try {
			String sql = "SELECT * "
					+ "FROM "+tabellenName
					+ " WHERE id=(SELECT max(id) "
								+ "FROM " + tabellenName
								+ ")";	
			
//			Connection con = DBConnection.connect(URL);
			Statement sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
//			rs.close();
//			con.close();
//			sm.close();
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return rs;
	}
	
	public static ResultSet selectAll(String tabelle) {
		ResultSet rs=null;
		try {
			String sql = "SELECT * FROM "+tabelle;
			
//			Connection con = DBConnection.connect(URL);
			Statement sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
//			rs.close();
//			con.close();
//			sm.close();
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return rs;
	}
	
		//für Urin Messwerte sind oft Messwerte der letzten 24 Std relevant! unfertig!!
/*	public static ResultSet selectAllLast24Hour(Calendar calendar, String tabelle) {
	// unfertig!!!!
	
//		  Date(int year, int month, int date, int hrs, int min)
//			Deprecated. 
//			As of JDK version 1.1, replaced by Calendar.set(year + 1900, month, date, hrs, min) or GregorianCalendar(year + 1900, month, date, hrs, min).

		 
		
		
		ResultSet rs=null;
		//current Date
		Calendar today = Calendar.getInstance();	
		int sec = today.get(Calendar.SECOND);
		int min = today.get(Calendar.MINUTE);
		int hour = today.get(Calendar.HOUR_OF_DAY);
		
		
		int day = today.get(Calendar.DAY_OF_MONTH);
		int month = today.get(Calendar.MONTH)+1;
		int year = today.get(Calendar.YEAR);
		
		String d, m, y;
		if(day<10) {
			d="0"+day;
		}else {
			d=String.valueOf(day);
		}
		if(month<10) {
			m="0"+month;
		}else {
			m=String.valueOf(month);
		}
		y=String.valueOf(year);
		
		String dateString = d+"."+m+"."+y;
		
		try {
			String sql = "SELECT * FROM "+tabelle
							+" WHERE datum like ('"
							+dateString +"%"
							+"')"
							+" ORDER BY (id) ASC";
			
			
//			Connection con = DBConnection.connect(URL);
			Statement sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
//			rs.close();
//			con.close();
//			sm.close();
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return rs;
		
	}*/
	
	//die Tabellen sind/waren leer.
	
	public static int insertBlutzucker(int id, String datum, String blutzucker) {
		 final String INSERT_SQL = "INSERT INTO Blutzucker (id, datum, blutzucker) VALUES(?, ?, ?)";
		
		Connection connection = DBConnection.connect();
		
      int numRowsInserted = 0;
      PreparedStatement ps = null;
      try {
          ps = connection.prepareStatement(INSERT_SQL);
          ps.setInt(1, id);
          ps.setString(2, datum);
          ps.setString(3, blutzucker);
          
          numRowsInserted = ps.executeUpdate();

      } catch (SQLException e) {
          e.printStackTrace();
      } finally {
          close(ps);
      }
      return numRowsInserted;
  }
	
	
	public static int insertGewicht(int id, String datum, String gewicht) {
		 final String INSERT_SQL = "INSERT INTO Gewicht (id, datum, masse) VALUES(?, ?, ? )";
		
		Connection connection = DBConnection.connect();
		
     int numRowsInserted = 0;
     PreparedStatement ps = null;
     try {
         ps = connection.prepareStatement(INSERT_SQL);
         ps.setInt(1, id);
         ps.setString(2, datum);
         ps.setString(3, gewicht);
         
         numRowsInserted = ps.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         close(ps);
     }
     return numRowsInserted;
 }
	
	public static int insertUrin(int id, String datum, String p1, String p2, String p3) {
		 final String INSERT_SQL = "INSERT INTO urin (id, datum, p1, p2,p3) VALUES(?, ?, ?, ?,?)";
		
		Connection connection = DBConnection.connect();
		
     int numRowsInserted = 0;
     PreparedStatement ps = null;
     try {
         ps = connection.prepareStatement(INSERT_SQL);
         ps.setInt(1, id);
         ps.setString(2, datum);
         ps.setString(3, p1);
         ps.setString(4, p2);
         ps.setString(5, p3);
         
         numRowsInserted = ps.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         close(ps);
     }
     return numRowsInserted;
 }


	private static void close(Statement statement) {
      try {
          if (statement != null) {
              statement.close();
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

	
	
	private static void printAllBlutzucker() {
		String i="Blutzucker";
		
		ResultSet rs = SQLite.selectAll(i);
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i);
			}else {
				do {
					int id = rs.getInt("id");
					String dat = rs.getString("datum");
					String bz= rs.getString("blutzucker");
					System.out.println("id: "+id + "Datum: " + dat + " Blutzucker: "+bz);
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void printAllUrin() {
		String i="Urin";
		
		ResultSet rs = SQLite.selectAll(i);
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i);
			}else {
				do {
					int id = rs.getInt("id");
					String dat = rs.getString("datum");
					String p1= rs.getString("p1");
					String p2= rs.getString("p2");
					String p3= rs.getString("p3");
					System.out.println("id: "+id + "Datum: " + dat + " P1: "+p1+ " P2: "+p2+ " P3: "+p3);
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void printAllGewicht() {
		String i="Gewicht";
		
		ResultSet rs = SQLite.selectAll(i);
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i);
			}else {
				do {
					int id = rs.getInt("id");
					String dat = rs.getString("datum");
					String m= rs.getString("masse");
					
					System.out.println("id: "+id + "Datum: " + dat + " Masse: "+m);
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
