import java.awt.Color;
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
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

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
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("菜單Menu");
		lblNewLabel.setBounds(15, 5, 130, 30);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		JTextArea taMenu = new JTextArea();
		taMenu.setBounds(10, 40, 300, 225);
		contentPane.add(taMenu);
		
		JLabel lblNewLabel_1 = new JLabel("購物車");
		lblNewLabel_1.setBounds(325, 5, 130, 30);
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		JTextArea taCart = new JTextArea();
		taCart.setBounds(320, 40, 155, 270);
		contentPane.add(taCart);
		
		JButton btnReturn = new JButton("返回Return");
		btnReturn.setBounds(15, 320, 110, 30);
		btnReturn.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
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
		btnAddToCart.setBounds(130, 320, 180, 30);
		btnAddToCart.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add to database
			}
		});
		contentPane.add(btnAddToCart);
		
		JButton btnPayment = new JButton("結帳Payment");
		btnPayment.setBounds(325, 320, 145, 30);
		btnPayment.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				C_Payment paymentFrame = new C_Payment();
				paymentFrame.setVisible(true);
			}
		});
		contentPane.add(btnPayment);
		
		JLabel lblQuantity = new JLabel("數量Quantity");
		lblQuantity.setBounds(169, 280, 97, 30);
		lblQuantity.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblQuantity);
		
		JSpinner spinner_q = new JSpinner();
		spinner_q.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner_q.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		spinner_q.setBounds(270, 280, 40, 30);
		contentPane.add(spinner_q);
		
		JLabel lblID = new JLabel("商品代號ID");
		lblID.setBounds(15, 280, 85, 30);
		lblID.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblID);
		
		JSpinner spinner_ID = new JSpinner();
		spinner_ID.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner_ID.setBounds(102, 280, 40, 30);
		spinner_ID.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(spinner_ID);
		
	}
}
