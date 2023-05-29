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

public class GUI_Consumer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Consumer frame = new GUI_Consumer();
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
	public GUI_Consumer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbList = new JLabel("店家清單List");
		lbList.setBounds(43, 14, 81, 20);
		lbList.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lbList.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbList);
		
		
		JButton btnInfo = new JButton("資訊Info");
		btnInfo.setBounds(100, 300, 100, 30);
		btnInfo.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		contentPane.add(btnInfo);
		
		
		JButton btnReviews = new JButton("評價Reviews");
		btnReviews.setBounds(210, 300, 130, 30);
		btnReviews.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		contentPane.add(btnReviews);
		
		
		JButton btnOrder = new JButton("預訂Order");
		btnOrder.setBounds(350, 300, 115, 30);
		btnOrder.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
				btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnOrder);
		
	}
}
