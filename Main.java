import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "111306079"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "111306079"; // change to your own user name
		String password = "d7w00"; // change to your own password　　　　
		
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
			
			GUI_Consumer frame = new GUI_Consumer(conn);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
