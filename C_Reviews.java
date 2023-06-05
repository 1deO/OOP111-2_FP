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
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;

public class C_Reviews extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_Reviews frame = new C_Reviews();
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
	public C_Reviews() {
		setTitle("評價回饋");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("所有評價");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 5, 130, 30);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 40, 300, 270);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("請輸入您的評價：");
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(325, 5, 130, 30);
		contentPane.add(lblNewLabel_1);
		
		JTextArea taInput = new JTextArea();
		taInput.setBounds(320, 40, 155, 270);
		taInput.setLineWrap(true);
		contentPane.add(taInput);
		
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
		
		JButton btnSend = new JButton("確認送出Send");
		btnSend.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnSend.setBounds(325, 320, 145, 30);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		contentPane.add(btnSend);
	}
}
