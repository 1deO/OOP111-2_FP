import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.EventQueue;
import javax.swing.border.CompoundBorder;

public class GUI_Seller extends JFrame{
	private JFrame frame;
	private JLabel firmLabel, itemLabel, infoLabel;
	private JTextArea itemArea, infoArea;
	private JButton confirmButton, addButton, delButton, modButton;
	private JComboBox firmCombo;
	private JPanel panel_1, panel_2;
	private String storeID;
	Statement stat;
	
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
					
					GUI_Seller frame = new GUI_Seller(conn);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI_Seller(Connection conn) throws SQLException{
		getContentPane().setBackground(new Color(245, 245, 245));
		this.setTitle("店家介面");
		setBounds(100, 100, 700, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		stat = conn.createStatement();
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBorder(null);
		panel_1.setBounds(0, 10, 717, 34);
		panel_1.setLayout(null);
		
		firmLabel = new JLabel("選擇你的店家");
		firmLabel.setBounds(143, 0, 157, 34);
		firmLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		panel_1.add(firmLabel);
		
		firmCombo = new JComboBox();
		firmCombo.setBackground(new Color(250, 250, 250));
		firmCombo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		firmCombo.setModel(new DefaultComboBoxModel(new String[] {"集英樓", "政大小吃部", "盛奇士", "H.I.Feeling", "伊果咖啡", "越南麵包"
				, "貍66日式炒麵", "新巨輪海海人生", "日式行丼餐車", "胖都日式脆皮雞蛋糕"}));
		firmCombo.setBounds(298, 0, 126, 34);
		panel_1.add(firmCombo);
		
		confirmButton = new JButton("確認");
		confirmButton.setForeground(new Color(0, 0, 0));
		confirmButton.setBackground(new Color(250, 250, 250));
		confirmButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		confirmButton.setBounds(474, 0, 102, 34);
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
				String query1 = "SELECT * FROM `menu` WHERE StoreID = '" + storeID + "'";
				String query2 = "SELECT * FROM `businessInfo` WHERE Name = '" + firm + "'";
				try {
					ResultSet result1 = stat.executeQuery(query1);
					itemArea.setText(showResultSet1(result1));
					ResultSet result2 = stat.executeQuery(query2);
					infoArea.setText(showResultSet2(result2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_1.add(confirmButton);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(220, 220, 220));
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
		panel_2.setBounds(10, 54, 670, 384);
		panel_2.setLayout(null);
		
		itemLabel = new JLabel("販售品項");
		itemLabel.setBounds(20, 10, 136, 38);
		itemLabel.setForeground(new Color(178, 34, 34));
		itemLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 24));
		panel_2.add(itemLabel);
		
		infoLabel = new JLabel("營業資訊");
		infoLabel.setBounds(345, 10, 136, 38);
		infoLabel.setForeground(new Color(178, 34, 34));
		infoLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 24));
		panel_2.add(infoLabel);
		
		itemArea = new JTextArea();
		itemArea.setBounds(15, 55, 305, 240);
		itemArea.setEditable(false);
		panel_2.add(itemArea);
		
		infoArea = new JTextArea();
		infoArea.setBounds(340, 55, 310, 240);
		infoArea.setEditable(false);
		panel_2.add(infoArea);
		
		addButton = new JButton("新增 New");
		addButton.setForeground(new Color(0, 0, 0));
		addButton.setBackground(new Color(245, 255, 245));
		addButton.setBounds(20, 320, 120, 45);
		addButton.setBorder(new CompoundBorder());
		addButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFrame addFrame = new JFrame();
				String name = JOptionPane.showInputDialog(addFrame, "新增項目名稱:");
				String price = JOptionPane.showInputDialog(addFrame, "價格:");
				String isAvail = JOptionPane.showInputDialog(addFrame, "目前是否供應");
				String supply = JOptionPane.showInputDialog(addFrame, "供應數量");
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
				String query = "INSERT INTO `menu` (StoreID,Product,Price,isAvail,Supply) VALUES ('" + storeID + "','" + name
						+ "','" + price + "','" + isAvail + "','" + supply + "')";
				String query1 = "SELECT * FROM `menu` WHERE StoreID = '" + storeID + "'";
				String query2 = "SELECT * FROM `businessInfo` WHERE Name = '" + firm + "'";
				try {
					stat.execute(query);
					ResultSet result1 = stat.executeQuery(query1);
					itemArea.setText(showResultSet1(result1));
					ResultSet result2 = stat.executeQuery(query2);
					infoArea.setText(showResultSet2(result2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_2.add(addButton);
		
		delButton = new JButton("刪除 Delete");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		delButton.setForeground(new Color(0, 0, 0));
		delButton.setBackground(new Color(255, 245, 245));
		delButton.setBounds(160, 320, 135, 45);
		delButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		delButton.setBorder(new CompoundBorder());
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFrame delFrame = new JFrame();
				String product = JOptionPane.showInputDialog(delFrame, "刪除項目名稱:");
				String query = "DELETE FROM `menu` WHERE Product = '" + product + "'";
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
				String query1 = "SELECT * FROM `menu` WHERE StoreID = '" + storeID + "'";
				String query2 = "SELECT * FROM `businessInfo` WHERE Name = '" + firm + "'";
				try {
					stat.execute(query);
					ResultSet result1 = stat.executeQuery(query1);
					itemArea.setText(showResultSet1(result1));
					ResultSet result2 = stat.executeQuery(query2);
					infoArea.setText(showResultSet2(result2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_2.add(delButton);
		
		modButton = new JButton("修改 Edit");
		modButton.setForeground(new Color(0, 0, 0));
		modButton.setBackground(new Color(245, 245, 255));
		modButton.setBounds(315, 320, 120, 45);
		modButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		modButton.setBorder(new CompoundBorder());
		modButton.addActionListener(new ActionListener() {
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
				JFrame modFrame = new JFrame();
				String[] block = {"販賣品項", "營業資訊"};
				String modBlock = (String) JOptionPane.showInputDialog(modFrame, "請輸入修改區塊", "", JOptionPane.QUESTION_MESSAGE, null, block, block[0]);	
				String query1 = "SELECT * FROM `menu` WHERE StoreID = '" + storeID + "'";
				String query2 = "SELECT * FROM `businessInfo` WHERE Name = '" + firm + "'";
				try {
					if(modBlock.equals("販賣品項")) {
						String productName = JOptionPane.showInputDialog(modFrame, "請輸入產品名稱");
						String columnLabel = JOptionPane.showInputDialog(modFrame, "請輸入修改欄位名稱:");
						String content = JOptionPane.showInputDialog(modFrame, "請輸入修改內容:");
						String query = "update menu set " + columnLabel + " = " + content + " where Product = '" + productName + "'";
						stat.execute(query);
					}else if(modBlock.equals("營業資訊")) {
						String columnLabel = JOptionPane.showInputDialog(modFrame, "請輸入修改項目名稱:");
						String content = JOptionPane.showInputDialog(modFrame, "請輸入修改內容:");
						String query = "update businessInfo set " + columnLabel + " = '" + content + "' where StoreID = '" + storeID + "'";
						stat.execute(query);
					}
					ResultSet result1 = stat.executeQuery(query1);
					itemArea.setText(showResultSet1(result1));
					ResultSet result2 = stat.executeQuery(query2);
					infoArea.setText(showResultSet2(result2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_2.add(modButton);
		getContentPane().add(panel_1);
		getContentPane().add(panel_2);
		
		//S_order button
		JButton btnOrder = new JButton("檢視訂單Order");
		btnOrder.setBounds(460, 320, 180, 45);
		btnOrder.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		modButton.setBorder(new CompoundBorder());
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				S_Order orderFrame;
				try {
					orderFrame = new S_Order(conn);
					orderFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnOrder);
	}
		
	
	public static String showResultSet1(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		for (int i = 1; i <= columnCount; i++) {
			output += String.format("%-12s", metaData.getColumnLabel(i));
		}
		output += "\n";
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%-12s", result.getString(i));
			}
			output += "\n";
		}
		return output;
	}
	
	public static String showResultSet2(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		while(result.next()){
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%20s", metaData.getColumnLabel(i));
				output += String.format("%20s\n", result.getString(i));
			}
		}
		output += "\n";
		return output;
	}
}
