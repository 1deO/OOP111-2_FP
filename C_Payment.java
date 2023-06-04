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
import javax.swing.JRadioButton;

public class C_Payment extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_Payment frame = new C_Payment();
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
	public C_Payment() {
		setTitle("結帳");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("訂單明細");
		lblNewLabel.setBounds(15, 5, 130, 30);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("優惠");
		lblNewLabel_1.setBounds(325, 5, 130, 30);
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 40, 300, 250);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(320, 40, 155, 260);
		contentPane.add(textArea_1);
		
		JButton btnPayment = new JButton("確認並返回列表頁");
		btnPayment.setBounds(320, 310, 150, 40);
		btnPayment.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send order to Seller
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
		contentPane.add(btnPayment);
		
		JLabel lblNewLabel_2 = new JLabel("支付方式");
		lblNewLabel_2.setBounds(15, 293, 68, 30);
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2);
		
		JRadioButton rdbtnCash = new JRadioButton("現金Cash");
		rdbtnCash.setBounds(15, 320, 93, 20);
		rdbtnCash.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(rdbtnCash);
		
		JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
		rdbtnPaypal.setBounds(110, 320, 79, 20);
		rdbtnPaypal.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(rdbtnPaypal);
		
		JRadioButton rdbtnLINEPay = new JRadioButton("LINE pay");
		rdbtnLINEPay.setBounds(191, 320, 93, 20);
		rdbtnLINEPay.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(rdbtnLINEPay);
	}
}
