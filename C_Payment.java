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
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

public class C_Payment extends JFrame {

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
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(25, 55, 640, 310);
		String query1 = "SELECT * FROM `ShoppingCart` WHERE 1";
		try {
			ResultSet result1 = stat.executeQuery(query1);
			textArea.setText(showResultSet_3(result1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(textArea);
		
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
				for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
					
			    	String paymentWay = "";
			    	
				    if (rdbtnCash.isSelected()) {
				    	paymentWay = "Cash";
				    }else if (rdbtnPaypal.isSelected()) {
				    	paymentWay = "Paypal";
				    }else if (rdbtnLINEPay.isSelected()) {
				    	paymentWay = "LINE pay";
				    }
				}
				
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
		contentPane.add(btnPayment);
		
	}
	
	public static String showResultSet_3(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		while(result.next()){
			for (int i = 1; i <= columnCount; i++) {
				if(i%4==1) {
					output += String.format("ID: %s", result.getString(i));
				}
				else if(i%4==2) {
					output += String.format(" %-5s", result.getString(i));
				}
				else if(i%4==3) {
					output += String.format(" *%-5s", result.getString(i));
				}
				else {
					output += String.format(" $%s\n", result.getString(i));
				}
			}
		}
		return output;
	}
	
}
