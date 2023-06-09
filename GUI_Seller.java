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
				String query = "SELECT * FROM `businessInfo` WHERE Name = '" + firm + "'";
				try {
					stat.execute(query);
					ResultSet result = stat.executeQuery(query);
					infoArea.setText(showResultSet(result));
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
		panel_2.add(itemArea);
		
		infoArea = new JTextArea();
		infoArea.setBounds(340, 55, 310, 240);
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
				String getName = JOptionPane.showInputDialog(addFrame, "新增項目名稱:");
				String getPrice = JOptionPane.showInputDialog(addFrame, "價格:");
				//String op = ;
				
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
				String getName = JOptionPane.showInputDialog(delFrame, "刪除項目名稱:");
				//for
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
				
			}
		});
		panel_2.add(modButton);
		getContentPane().add(panel_1);
		getContentPane().add(panel_2);
	}
	public static String showResultSet(ResultSet result) throws SQLException {
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