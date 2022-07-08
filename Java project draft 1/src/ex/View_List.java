package ex;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import java.util.Arrays;
import javax.swing.JButton;

public class View_List extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_List frame = new View_List();
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
	public View_List() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INSERT ADMIN PIN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel.setBounds(150, 99, 206, 43);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				char[] password = passwordField.getPassword();
				char[] correctPass = new char[] {'1', '2', '3'};
				 
				if (Arrays.equals(password, correctPass)) {
				    
					try {

						Runtime.getRuntime().exec(new String[] {"rundll32", "url.dll,FileProtocolHandler",
								"C:\\Users\\Maimuna\\eclipse-workspace\\Java project draft 1\\AccountList.txt"});
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null,"Error");
					}
					
				} else {
					JOptionPane.showMessageDialog(null,"Invalid Admin Pin");
				}
			
			}
		});
		passwordField.setBounds(150, 155, 188, 22);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame frame= new Frame();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(202, 211, 97, 25);
		contentPane.add(btnNewButton);
	}

}
