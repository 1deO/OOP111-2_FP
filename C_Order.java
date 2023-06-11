import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

public class C_Order extends JFrame {

	private JPanel contentPane;
	private String storeID;
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
				
				C_Order frame = new C_Order(conn);
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
	public C_Order(Connection conn) throws SQLException {
		setTitle("線上預訂");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		stat = conn.createStatement();
		
		JLabel lblNewLabel = new JLabel("菜單 Menu");
		lblNewLabel.setBounds(30, 10, 219, 40);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JTextArea taMenu = new JTextArea();
		taMenu.setFont(new Font("Monospaced", Font.PLAIN, 16));
		taMenu.setBounds(25, 55, 415, 270);
		taMenu.setEditable(false);
		contentPane.add(taMenu);
		
		JLabel lblNewLabel_1 = new JLabel("購物車 Cart");
		lblNewLabel_1.setBounds(460, 10, 168, 40);
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		JTextArea taCart = new JTextArea();
		taCart.setFont(new Font("Monospaced", Font.PLAIN, 16));
		taCart.setBounds(460, 55, 205, 330);
		taCart.setEditable(false);
		contentPane.add(taCart);
		
		JComboBox firmCombo = new JComboBox();
		firmCombo.setBackground(new Color(250, 250, 250));
		firmCombo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		firmCombo.setModel(new DefaultComboBoxModel(new String[] {"集英樓", "政大小吃部", "盛奇士", "H.I.Feeling", "伊果咖啡", "越南麵包"
				, "貍66日式炒麵", "新巨輪海海人生", "日式行丼餐車", "胖都日式脆皮雞蛋糕"}));
		firmCombo.setBounds(200, 10, 130, 40);
		contentPane.add(firmCombo);
		
		JButton confirmButton = new JButton("選取");
		confirmButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		confirmButton.setBounds(350, 10, 80, 40);
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
				String query1 = "SELECT ID,Product,Price FROM `menu` WHERE StoreID = '" + storeID + "'";
				try {
					ResultSet result1 = stat.executeQuery(query1);
					taMenu.setText(showResultSet_3(result1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(confirmButton);
		
		JButton btnReturn = new JButton("返回 Return");
		btnReturn.setBounds(30, 400, 150, 40);
		btnReturn.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
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
		
		JButton btnPayment = new JButton("結帳 Payment");
		btnPayment.setBounds(465, 400, 195, 40);
		btnPayment.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				C_Payment paymentFrame;
				try {
					paymentFrame = new C_Payment(conn);
					paymentFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnPayment);
		
		JLabel lblQuantity = new JLabel("數量 Quantity");
		lblQuantity.setBounds(245, 345, 132, 40);
		lblQuantity.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblQuantity);
		
		JSpinner spinner_q = new JSpinner();
		spinner_q.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner_q.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		spinner_q.setBounds(380, 345, 50, 40);
		contentPane.add(spinner_q);
		
		JLabel lblID = new JLabel("商品代號 ID");
		lblID.setBounds(30, 345, 119, 40);
		lblID.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblID);
		
		JSpinner spinner_ID = new JSpinner();
		spinner_ID.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner_ID.setBounds(145, 345, 50, 40);
		spinner_ID.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(spinner_ID);
		
		JButton btnAddToCart = new JButton("加入購物車 AddToCart");
		btnAddToCart.setBounds(190, 400, 245, 40);
		btnAddToCart.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						spinner_ID.commitEdit();
					} catch ( java.text.ParseException e2 ) { 
						
					}
					int ID = (Integer)spinner_ID.getValue();
					String Product=new String();
					int amount = (Integer)spinner_q.getValue();
					int singlePrice = 0;
					
					String query = "SELECT Product FROM `menu` WHERE ID = '" + ID + "'";
					ResultSet result = stat.executeQuery(query);
					while(result.next()){
							Product = String.format("%s", result.getString(1));
					}
					query = "SELECT Price FROM `menu` WHERE ID = '" + ID + "'";
					result = stat.executeQuery(query);
					while(result.next()){
							singlePrice = result.getInt(1);
					}
					taCart.append(String.format("%s *%s\nTotal: $%s ID:%s\n",Product,amount,singlePrice*amount,ID));
					String sql = "INSERT INTO `ShoppingCart` ( `Product`, `amount`, `singlePrice`) VALUES ('"
							+ Product + "', '" + amount + "','" + singlePrice + "');";
					stat.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnAddToCart);
		
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
				if(i%3==1) {
					output += String.format("ID: %s", result.getString(i));
				}
				else if(i%3==2) {
					output += String.format(" %-5s", result.getString(i));
				}
				else {
					output += String.format(" $%s\n", result.getString(i));
				}
			}
		}
		return output;
	}
}
