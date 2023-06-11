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
					System.out.println("DB Connected");
					
					GUI_Consumer frame = new GUI_Consumer(conn);
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
	public GUI_Consumer(Connection conn) throws SQLException{
		String query;
		String str = new String();
		int temp;
		boolean sucess;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		stat = conn.createStatement();
		
		//List
		JLabel lbList = new JLabel("店家清單List");
		lbList.setBounds(30, 10, 120, 40);
		lbList.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lbList.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbList);
		
		//Info
		JButton btnInfo = new JButton("資訊Info");
		btnInfo.setBounds(225, 400, 160, 40);
		btnInfo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				C_Info infoFrame;
				try {
					infoFrame = new C_Info(conn);
					infoFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnInfo);
		
		
		//order
		JButton btnOrder = new JButton("預訂Order");
		btnOrder.setBounds(425, 400, 180, 40);
		btnOrder.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				C_Order orderFrame;
				try {
					orderFrame = new C_Order(conn);
					orderFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnOrder);
		
		//textArea
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		textArea.setBounds(25, 55, 640, 330);
		textArea.setEditable(false);
		
		//String firm = (String)firmCombo.getSelectedItem();
		query = "SELECT Name FROM businessInfo WHERE 1;";
		try {
			stat.execute(query);
			ResultSet result = stat.executeQuery(query);
			str=showResultSet(result).replace("Name", "").replace(" ", "");
			textArea.setText(str);
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(textArea);
		
		//spinner_chooseID
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		spinner.setBounds(30, 400, 50, 40);
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
