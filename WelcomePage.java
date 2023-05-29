import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnBuyer = new JButton("買家Buyer");
		btnBuyer.setBounds(78, 169, 120, 40);
		btnBuyer.setForeground(new Color(0, 0, 0));
		btnBuyer.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Consumer cc = new GUI_Consumer();
				cc.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBuyer);
		
		JButton btnSeller = new JButton("賣家Seller");
		btnSeller.setBounds(266, 169, 120, 40);
		btnSeller.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(btnSeller);
		
		JLabel lbWelcome = new JLabel("Welcome");
		lbWelcome.setBounds(177, 70, 85, 53);
		lbWelcome.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbWelcome);
	}
}
