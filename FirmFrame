import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.EventQueue;

public class FirmFrame extends JFrame{
	private JFrame frame;
	private JLabel firmLabel, itemLabel, infoLabel;
	private JTextArea itemArea, infoArea;
	private JButton confirmButton, addButton, delButton, modButton;
	private JComboBox firmCombo;
	private JPanel panel_1, panel_2;
	
	
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
					System.out.println("DB Connectd");
					
					FirmFrame frame = new FirmFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FirmFrame() throws SQLException{
		this.setTitle("店家介面");
		setBounds(100, 100, 731, 467);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 10, 717, 34);
		panel_1.setLayout(null);
		
		firmLabel = new JLabel("選擇你的店家");
		firmLabel.setBounds(143, 0, 157, 34);
		firmLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(firmLabel);
		
		firmCombo = new JComboBox();
		firmCombo.setFont(new Font("新細明體", Font.PLAIN, 20));
		firmCombo.setModel(new DefaultComboBoxModel(new String[] {"極鮮滷味", "允浩自助餐", "血盅紅"}));
		firmCombo.setBounds(298, 0, 126, 34);
		panel_1.add(firmCombo);
		
		confirmButton = new JButton("確認");
		confirmButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 180, 209), Color.CYAN));
		confirmButton.setFont(new Font("新細明體", Font.PLAIN, 20));
		confirmButton.setBounds(474, 0, 102, 34);
		
		panel_1.add(confirmButton);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
		panel_2.setBounds(0, 46, 717, 384);
		panel_2.setLayout(null);
		
		itemLabel = new JLabel("販售品項");
		itemLabel.setForeground(Color.RED);
		itemLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		itemLabel.setBounds(10, 10, 136, 38);
		panel_2.add(itemLabel);
		
		infoLabel = new JLabel("營業資訊");
		infoLabel.setForeground(Color.RED);
		infoLabel.setFont(new Font("新細明體", Font.BOLD, 24));
		infoLabel.setBounds(382, 10, 136, 38);
		panel_2.add(infoLabel);
		
		itemArea = new JTextArea();
		itemArea.setBounds(10, 48, 325, 225);
		panel_2.add(itemArea);
		
		infoArea = new JTextArea();
		infoArea.setBounds(382, 48, 325, 225);
		panel_2.add(infoArea);
		
		addButton = new JButton("新增");
		addButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 180, 209), Color.CYAN));
		addButton.setFont(new Font("新細明體", Font.PLAIN, 24));
		addButton.setBounds(23, 294, 92, 45);
		panel_2.add(addButton);
		
		delButton = new JButton("刪除");
		delButton.setFont(new Font("新細明體", Font.PLAIN, 24));
		delButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 180, 209), Color.CYAN));
		delButton.setBounds(161, 294, 92, 45);
		panel_2.add(delButton);
		
		modButton = new JButton("修改");
		modButton.setFont(new Font("新細明體", Font.PLAIN, 24));
		modButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 180, 209), Color.CYAN));
		modButton.setBounds(298, 294, 92, 45);
		panel_2.add(modButton);
		this.add(panel_1);
		this.add(panel_2);
	}

}
