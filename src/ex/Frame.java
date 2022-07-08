package ex;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

public class Frame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton1 = new JButton("ADMIN");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
				View_List viewlist= new View_List();
				viewlist.setVisible(true);
			}
		});
		btnNewButton1.setBackground(UIManager.getColor("Button.focus"));
		btnNewButton1.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnNewButton1.setBounds(122, 59, 188, 48);
		contentPane.add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("CUSTOMER");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
				LoginWindow loginwindow= new LoginWindow();
				loginwindow.setVisible(true);
			}
		});
		btnNewButton2.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnNewButton2.setBackground(UIManager.getColor("Button.focus"));
		btnNewButton2.setBounds(122, 149, 188, 48);
		contentPane.add(btnNewButton2);
	}

}
