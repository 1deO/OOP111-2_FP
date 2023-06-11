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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;

public class C_Reviews extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
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
				
				C_Reviews frame = new C_Reviews(conn);
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
	public C_Reviews(Connection conn) throws SQLException {
		setTitle("評價回饋");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		stat = conn.createStatement();
		
		JLabel lblNewLabel = new JLabel("所有評價 All reviews");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 10, 219, 40);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(25, 55, 310, 330);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("評分：（滿分5分）");
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(360, 10, 231, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("請輸入您的留言：");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(360, 50, 231, 40);
		contentPane.add(lblNewLabel_2);
		
		JTextArea taInput = new JTextArea();
		taInput.setFont(new Font("Monospaced", Font.PLAIN, 16));
		taInput.setBounds(355, 100, 310, 285);
		taInput.setLineWrap(true);//字填滿框就換行
		contentPane.add(taInput);
		
		JButton btnReturn = new JButton("返回 Return");
		btnReturn.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnReturn.setBounds(30, 400, 150, 40);
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
		
		JButton btnSend = new JButton("確認送出Send");
		btnSend.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnSend.setBounds(484, 400, 176, 40);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		contentPane.add(btnSend);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(5, 1, 5, 1));
		spinner.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		spinner.setBounds(573, 11, 50, 40);
		contentPane.add(spinner);
	}
}
