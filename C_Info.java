import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class C_Info extends JFrame {

	private JPanel contentPane;
	private String storeID=new String();
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
					
					C_Info frame = new C_Info(conn);
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
	 * @throws SQLException 
	 */
	public C_Info(Connection conn) throws SQLException {
		String query;
		String str = new String();
		int temp;
		boolean sucess;
		setTitle("檢視店家資訊");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		stat = conn.createStatement();
		
		JLabel lblNewLabel = new JLabel("菜單 Menu");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 10, 130, 40);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(25, 55, 310, 330);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("營業資訊");
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(360, 10, 130, 40);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea_1.setBounds(355, 55, 310, 330);
		textArea.setEditable(false);
		contentPane.add(textArea_1);
		
		JComboBox firmCombo = new JComboBox();
		firmCombo.setBackground(new Color(250, 250, 250));
		firmCombo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		firmCombo.setModel(new DefaultComboBoxModel(new String[] {"集英樓", "政大小吃部", "盛奇士", "H.I.Feeling", "伊果咖啡", "越南麵包"
				, "貍66日式炒麵", "新巨輪海海人生", "日式行丼餐車", "胖都日式脆皮雞蛋糕"}));
		firmCombo.setBounds(360, 400, 130, 40);
		contentPane.add(firmCombo);
		
		JButton confirmButton = new JButton("確認");
		confirmButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		confirmButton.setBounds(510, 400, 150, 40);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String firm = (String)firmCombo.getSelectedItem();
				switch(firm){
					case "集英樓" : storeID = "A1"; break;
					case "政大小吃部" : storeID = "A2"; break;
					case "盛奇士" : storeID = "A3"; break;
					case "H.I.Feeling" : storeID = "A4"; break;
					case "伊果咖啡" : storeID = "A5"; break;
					case "越南麵包" : storeID = "B6"; break;
					case "貍66日式炒麵" : storeID = "B7"; break;
					case "新巨輪海海人生" : storeID = "B8"; break;
					case "日式行丼餐車" : storeID = "B9"; break;
					case "胖都日式脆皮雞蛋糕" : storeID = "B10"; break;
				}
				String query1 = "SELECT Product,Price FROM `menu` WHERE StoreID = '" + storeID + "'";
				String query2 = "SELECT Name,Location,Hours FROM `businessInfo` WHERE Name = '" + firm + "'";
				try {
					ResultSet result1 = stat.executeQuery(query1);
					textArea.setText(showResultSet_3(result1));
					ResultSet result2 = stat.executeQuery(query2);
					textArea_1.setText(showResultSet_2(result2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(confirmButton);
		
		JButton btnReturn = new JButton("返回 Return");
		btnReturn.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnReturn.setBounds(30, 400, 150, 40);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Consumer buyerFrame;
				try {
					buyerFrame = new GUI_Consumer(conn);
					buyerFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnReturn);
		
	}
	public static String showResultSet_1(ResultSet result) throws SQLException {
		
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
	public static String showResultSet_2(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		while(result.next()){
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%10s", metaData.getColumnLabel(i));
				output += String.format(" %s\n", result.getString(i));
			}
		}
		output += "\n";
		return output;
	}
	public static String showResultSet_3(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		while(result.next()){
			for (int i = 1; i <= columnCount; i++) {
				if(i%2==1) {
					output += String.format(" %s", result.getString(i));
				}
				else {
					output += String.format(" $ %s\n", result.getString(i));
				}
			}
		}
		output += "\n";
		return output;
	}

}
