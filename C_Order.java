import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class C_Order extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_Order frame = new C_Order();
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
	public C_Order() {
		setTitle("線上預訂");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("菜單Menu");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 5, 130, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("購物車");
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(325, 5, 130, 30);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 40, 300, 270);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(320, 40, 155, 270);
		contentPane.add(textArea_1);
		
		JButton btnReturn = new JButton("返回Return");
		btnReturn.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnReturn.setBounds(15, 320, 110, 30);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Consumer buyerFrame;
				try {
					buyerFrame = new GUI_Consumer();
					buyerFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnReturn);
		
		JButton btnAddToCart = new JButton("加入購物車AddToCart");
		btnAddToCart.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnAddToCart.setBounds(130, 320, 180, 30);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add to database
			}
		});
		contentPane.add(btnAddToCart);
		
		JButton btnPayment = new JButton("結帳Payment");
		btnPayment.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnPayment.setBounds(325, 320, 145, 30);
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				C_Payment paymentFrame = new C_Payment();
				paymentFrame.setVisible(true);
			}
		});
		contentPane.add(btnPayment);
	}

}
