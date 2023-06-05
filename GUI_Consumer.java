import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;

public class GUI_Consumer extends JFrame {

	private JPanel contentPane;
	Statement stat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				String server = "jdbc:mysql://140.119.19.73:3315/";
				String database = "111306079"; // change to your own database
				String url = server + database + "?useSSL=false";
				String username = "111306079"; // change to your own user name
				String password = "d7w00"; // change to your own password　　　　
				
				try {
					Connection conn = DriverManager.getConnection(url, username, password);
					System.out.println("DB Connectd");
					
					GUI_Consumer frame = new GUI_Consumer();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_Consumer() throws SQLException{
		//Connection conn ;
		
		//stat = conn.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//List
		JLabel lbList = new JLabel("店家清單List");
		lbList.setBounds(30, 10, 81, 30);
		lbList.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lbList.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbList);
		
		//Info
		JButton btnInfo = new JButton("資訊Info");
		btnInfo.setBounds(95, 300, 100, 35);
		btnInfo.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				C_Info infoFrame = new C_Info();
				infoFrame.setVisible(true);
			}
		});
		contentPane.add(btnInfo);
		
		//reviews
		JButton btnReviews = new JButton("評價Reviews");
		btnReviews.setBounds(205, 300, 125, 35);
		btnReviews.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				C_Reviews reviewsFrame = new C_Reviews();
				reviewsFrame.setVisible(true);
			}
		});
		contentPane.add(btnReviews);
		
		//order
		JButton btnOrder = new JButton("預訂Order");
		btnOrder.setBounds(340, 300, 115, 35);
		btnOrder.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				C_Order orderFrame = new C_Order();
				orderFrame.setVisible(true);
			}
		});
		contentPane.add(btnOrder);
		
		//textArea
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		textArea.setBounds(30, 42, 425, 236);
		textArea.setEditable(false);
		/*
		String query = "SELECT * FROM `TWICE` WHERE 1";
		boolean sucess = stat.execute(query);
		if (sucess) {
			ResultSet result = stat.getResultSet();
			textArea.setText(showResultSet(result));
			result.close();
		}
		*/
		contentPane.add(textArea);
		
		//spinner_chooseID
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 7, 1));
		spinner.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		spinner.setBounds(30, 300, 47, 35);
		contentPane.add(spinner);
		
	}
	public static String showResultSet(ResultSet result) throws SQLException {
			
			ResultSetMetaData metaData = result.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			String output = "";
			
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%15s", metaData.getColumnLabel(i));
			}
			output += "\n";
			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					output += String.format("%15s", result.getString(i));
				}
				output += "\n";
			}
			return output;
		}
}
