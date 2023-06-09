import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
		contentPane.add(taMenu);
		
		JLabel lblNewLabel_1 = new JLabel("購物車 Cart");
		lblNewLabel_1.setBounds(460, 10, 168, 40);
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		JTextArea taCart = new JTextArea();
		taCart.setFont(new Font("Monospaced", Font.PLAIN, 16));
		taCart.setBounds(460, 55, 205, 330);
		contentPane.add(taCart);
		
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
		
		JButton btnAddToCart = new JButton("加入購物車 AddToCart");
		btnAddToCart.setBounds(190, 400, 245, 40);
		btnAddToCart.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add to database
			}
		});
		contentPane.add(btnAddToCart);
		
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
		
	}
}
