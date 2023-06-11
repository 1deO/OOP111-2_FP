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
import javax.swing.JComboBox;

public class S_Order extends JFrame {

	private JPanel contentPane;
	private String storeID="";
	Connection conn;
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
					
					S_Order frame = new S_Order(conn);
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
	public S_Order(Connection conn) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		stat = conn.createStatement();
		
		JLabel lblNewLabel = new JLabel("訂單 Orders");
		lblNewLabel.setBounds(30, 10, 220, 40);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JTextArea taOrder = new JTextArea();
		taOrder.setFont(new Font("Monospaced", Font.PLAIN, 16));
		taOrder.setBounds(25, 55, 640, 330);
		taOrder.setEditable(false);
		contentPane.add(taOrder);
		
		JComboBox firmCombo = new JComboBox();
		firmCombo.setBackground(new Color(250, 250, 250));
		firmCombo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		firmCombo.setModel(new DefaultComboBoxModel(new String[] {"集英樓", "政大小吃部", "盛奇士", "H.I.Feeling", "伊果咖啡", "越南麵包"
				, "貍66日式炒麵", "新巨輪海海人生", "日式行丼餐車", "胖都日式脆皮雞蛋糕"}));
		firmCombo.setBounds(300, 10, 130, 40);
		contentPane.add(firmCombo);
		
		JButton confirmButton = new JButton("選取");
		confirmButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		confirmButton.setBounds(450, 10, 80, 40);
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
				String query="SELECT Product,amount,singlePrice,PaymentWay FROM `ShoppingCart` WHERE StoreID = '" + storeID + "'";
				try {
					ResultSet result = stat.executeQuery(query);
					taOrder.setText(showResultSet_3(result));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(confirmButton);

		
		JButton btnReturn = new JButton("返回 Return");
		btnReturn.setBounds(30, 400, 150, 45);
		btnReturn.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Seller sellerFrame;
				try {
					sellerFrame = new GUI_Seller(conn);
					sellerFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnReturn);
	}
	public static String showResultSet_3(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		int total=0;
		String output = "";
		while(result.next()){
			for (int i = 1; i <= columnCount; i++) {
				if(i%4==1) {
					output += String.format("Product: %s", result.getString(i));
				}
				else if(i%4==2) {
					output += String.format(" *%-5s", result.getString(i));
				}
				else if(i%4==3) {
					total +=result.getInt(i)*result.getInt(i-1);
					output += String.format(" $%-20s", result.getInt(i)*result.getInt(i-1));
				}
				else {
					output +=String.format("by:%s\n", result.getString(i));
				}

			}
		}
		for(int i=0;i<60;i++) {
			output+="-";
		}
		output+=String.format("\nTotal price: $%s",total);
		return output;
	}
}
