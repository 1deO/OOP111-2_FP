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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

public class C_Payment extends JFrame {

	private JPanel contentPane;
	private String BuyerID="";
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
				
				C_Payment frame = new C_Payment(conn);
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
	public C_Payment(Connection conn) throws SQLException {
		setTitle("結帳");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		stat = conn.createStatement();
		
		JLabel lblNewLabel = new JLabel("訂單明細");
		lblNewLabel.setBounds(30, 10, 219, 40);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(25, 55, 640, 280);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JLabel lblUserID = new JLabel("使用者ID: ");
		lblUserID.setBounds(25, 340, 100, 40);
		lblUserID.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblUserID);
		
		JTextField tfUser = new JTextField();
		tfUser.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tfUser.setBounds(125, 340, 130, 40);
		tfUser.setEditable(true);
		contentPane.add(tfUser);
		
		JButton btnBuyer = new JButton("輸入");
		btnBuyer.setBounds(260, 340, 100, 40);
		btnBuyer.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyerID=tfUser.getText();
				String query = "SELECT StoreID,Product,amount,singlePrice FROM `ShoppingCart` WHERE BuyerID='"+BuyerID+"'";
				try {
					ResultSet result = stat.executeQuery(query);
					textArea.setText(showResultSet_3(result)+"        Buyer: "+BuyerID);
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		contentPane.add(btnBuyer);
		
		JLabel lblNewLabel_2 = new JLabel("支付方式");
		lblNewLabel_2.setBounds(30, 375, 80, 40);
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2);
		
		JRadioButton rdbtnCash = new JRadioButton("現金 Cash");
		rdbtnCash.setBackground(new Color(245, 245, 245));
		rdbtnCash.setBounds(30, 410, 120, 30);
		rdbtnCash.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(rdbtnCash);
		
		JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
		rdbtnPaypal.setBackground(new Color(245, 245, 245));
		rdbtnPaypal.setBounds(150, 410, 90, 30);
		rdbtnPaypal.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(rdbtnPaypal);
		
		JRadioButton rdbtnLINEPay = new JRadioButton("LINE pay");
		rdbtnLINEPay.setBackground(new Color(245, 245, 245));
		rdbtnLINEPay.setBounds(240, 410, 110, 30);
		rdbtnLINEPay.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(rdbtnLINEPay);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCash);
		group.add(rdbtnPaypal);
		group.add(rdbtnLINEPay);
		
		JButton btnPayment = new JButton("確認並返回列表頁");
		btnPayment.setBounds(455, 400, 205, 40);
		btnPayment.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send order to Seller
		    	String paymentWay="";
			    if (rdbtnCash.isSelected()) {
			    	paymentWay = "Cash";
			    }else if (rdbtnPaypal.isSelected()) {
			    	paymentWay = "Paypal";
			    }else if (rdbtnLINEPay.isSelected()) {
			    	paymentWay = "LINE pay";
			    }
				GUI_Consumer buyerFrame;
				try {
					String sql = "UPDATE `ShoppingCart` SET `PaymentWay`='"+paymentWay+"' WHERE BuyerID='"+BuyerID+"'";
					stat.executeUpdate(sql);
					buyerFrame = new GUI_Consumer(conn);
					buyerFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnPayment);
		
	}
	
	public static String showResultSet_3(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		int total=0;
		String output = "";
		while(result.next()){
			for (int i = 1; i <= columnCount; i++) {
				if(i%4==1) {
					output += String.format("StoreID: %s", result.getString(i));
				}
				else if(i%4==2) {
					output += String.format(" %-5s", result.getString(i));
				}
				else if(i%4==3) {
					output += String.format(" *%-5s", result.getString(i));
				}
				else {
					total+=result.getInt(i)*result.getInt(i-1);
					output += String.format(" $%s\n", result.getInt(i)*result.getInt(i-1));
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
