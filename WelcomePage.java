import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class WelcomePage extends JFrame {

	private JPanel contentPane;
	Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "111306079"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "111306079"; // change to your own user name
		String password = "d7w00"; // change to your own password

		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");

			WelcomePage frame = new WelcomePage(conn);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public WelcomePage(Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JButton btnBuyer = new JButton("買家Buyer");
		btnBuyer.setBackground(new Color(224, 255, 255));
		btnBuyer.setBounds(65, 250, 150, 50);
		btnBuyer.setForeground(new Color(0, 0, 0));
		btnBuyer.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnBuyer.addActionListener(new ActionListener() {
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
		contentPane.setLayout(null);
		contentPane.add(btnBuyer);

		JButton btnSeller = new JButton("賣家Seller");
		btnSeller.setBackground(new Color(224, 255, 255));
		btnSeller.setBounds(285, 250, 150, 50);
		btnSeller.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnSeller.addActionListener(new ActionListener() {
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
		contentPane.add(btnSeller);

		JLabel lbWelcome = new JLabel("Welcome");
		lbWelcome.setBounds(184, 116, 136, 53);
		lbWelcome.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbWelcome);
	}
}