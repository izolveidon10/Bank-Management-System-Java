package ex;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AccountDashboard2 extends JFrame implements ActionListener
{
	Database db = Database.getInstance();
	Account ac;
	
	JLabel welcomeText = new JLabel();
	
	JTextField accountNoT = new JTextField("",15);
	JTextField amountT = new JTextField("",10);
	

	JButton withdrawB= new JButton("Withdraw Money");
	JButton depositB= new JButton("Deposit Money");
	JButton balanceB= new JButton("Balance Check");
	JButton accountDetailB= new JButton("Account Detail");
	JButton changeSettingB= new JButton("Change Setting");
	JButton logoutB = new JButton("Log Out");
	
	
	JButton withdrawalSB = new JButton("Withdraw");
	JButton depositSB = new JButton("Deposit");
	
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel centerr = new JPanel();
	JPanel center = new JPanel();
	
	public AccountDashboard2(Account ac)
	{
		this.ac=ac;
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				db.saveData();
				System.exit(0);
			}
		});
		this.setTitle("Dashboard");
		this.setSize(700, 500);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //this.setLayout(new FlowLayout());
	    
	   
	    withdrawalSB.addActionListener(this);
	    depositSB.addActionListener(this);
	    
	    
	    this.add(left,BorderLayout.WEST);
	    this.add(right,BorderLayout.EAST);
	    this.add(top,BorderLayout.NORTH);
	    this.add(bottom,BorderLayout.SOUTH);
	    this.add(centerr,BorderLayout.CENTER);
	    
	    
	    welcomeText.setText("Welcome, " + ac.user.firstName + " " + ac.user.lastName);
	    top.add(welcomeText);
	    
	    left.setLayout(new GridLayout(9, 1,5,10));
	    withdrawB.addActionListener(this);
	    left.add(withdrawB);
	    depositB.addActionListener(this);
	    left.add(depositB);
	    balanceB.addActionListener(this);
	    left.add(balanceB);
	    right.setLayout(new GridLayout(9, 1,5,10));
	    right.add(accountDetailB);
	    right.add(changeSettingB);
	    
	    centerr.setLayout(new FlowLayout(FlowLayout.CENTER));
	    centerr.setBorder(new EmptyBorder(90, 10, 50, 10));
	    centerr.add(center);
	    //center.setBackground(new Color(4,55,4));
	    //centerr.setBackground(new Color(54,55,40));
	    
	    logoutB.addActionListener(this);
	    bottom.add(logoutB);
	    
	}

	public void actionPerformed(ActionEvent e)
	{
		
	 if(e.getActionCommand().equals("Withdraw Money"))
		{
			generateWithdrawalPanel();
		}
		else if(e.getActionCommand().equals("Deposit Money"))
		{
			generateDepositPanel();
		}
		else if(e.getActionCommand().equals("Balance Check"))
		{
			generateBalancePanel();
		}
		
		else if(e.getActionCommand().equals("Withdraw"))
		{
			withdraw();
		}
		else if(e.getActionCommand().equals("Deposit"))
		{
			deposit();
			
		}
		
		else if(e.getActionCommand().equals("Log Out"))
		{
			this.dispose();
			new LoginWindow();
		}
		
	}
	
	void generateTransferPanel()
	{
		panelClear();
		center.add(new JLabel("Enter Account: "));
		center.add(accountNoT);
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());

		this.revalidate();
	}
	
	void generateWithdrawalPanel()
	{
		panelClear();
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(withdrawalSB);
		this.revalidate();
	}
	
	void generateDepositPanel()
	{
		panelClear();
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(depositSB);
		this.revalidate();
	}
	
	void generateBalancePanel()
	{
		panelClear();
		center.add(new JLabel("Current Balance: "));
		DecimalFormat df = new DecimalFormat("0.00");
		center.add(new JLabel(""+df.format(ac.getBalance())));
		center.add(new JLabel());
		center.add(new JLabel());
		this.revalidate();
	}
	
	void generatePayBillPanel()
	{
		panelClear();
		center.add(new JLabel("Enter ID/BillNo: "));
		center.add(accountNoT);
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		this.revalidate();
	}
	
	
	void panelClear()
	{
		center.removeAll();
		center.setLayout(new GridLayout(0, 2, 5, 10));
		accountNoT.setText("");
		amountT.setText("");
	}
	
	
	void withdraw()
	{
		try
		{
			double amount=Double.parseDouble(amountT.getText());
			int t=ac.withdrawMoney(amount);
			if(t==0)
				JOptionPane.showMessageDialog(this,"Successfully Withdrawn","Success",JOptionPane.OK_OPTION);
			else if(t==Account.INSUFFICIENT_BALANCE)
				JOptionPane.showMessageDialog(this,"You don't have enough Balance","Failed",JOptionPane.ERROR_MESSAGE);
			else if(t==Account.WITHDRAWAL_LIMIT_UNDER)
				JOptionPane.showMessageDialog(this,"Minimum withdrawal amount is: "+ ac.minWithdrawal,"Failed",JOptionPane.ERROR_MESSAGE);
			else if(t==Account.WITHDRAWAL_LIMIT_OVER)
				JOptionPane.showMessageDialog(this,"Maximum Withdrawal amount is: " +ac.maxWithdrawal,"Failed",JOptionPane.ERROR_MESSAGE);
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(this,"Enter valid amount","Failed",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void deposit()
	{
		try
		{
			double amount=Double.parseDouble(amountT.getText());
			ac.depositMoney(amount);
			JOptionPane.showMessageDialog(this,"Successfully Deposited","Success",JOptionPane.OK_OPTION);
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(this,"Enter valid amount","Failed",JOptionPane.ERROR_MESSAGE);
		}
	}
}