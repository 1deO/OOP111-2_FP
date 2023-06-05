import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class C_Info extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_Info frame = new C_Info();
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
	public C_Info() {
		setTitle("檢視店家資訊");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("菜單Menu");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 5, 130, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("營業資訊");
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
		
	}

}
