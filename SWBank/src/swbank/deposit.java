package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class deposit extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JTextField amount;
	private JPasswordField pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deposit frame = new deposit();
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
	public deposit() {
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(deposit.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Deposit - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(deposit.class.getResource("/swbank/Images/transparentlogo.png")));
		lblNewLabel.setBounds(10, 11, 385, 131);
		contentPane.add(lblNewLabel);
		
		account = new JTextField();
		account.setBounds(148, 172, 164, 20);
		contentPane.add(account);
		account.setColumns(10);
		
		amount = new JTextField();
		amount.setBounds(148, 216, 164, 20);
		contentPane.add(amount);
		amount.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(29, 175, 109, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(49, 219, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Deposit ");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        try{
		        
		        	Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
					Statement st = conn.createStatement();
					
					
					String acc=account.getText();
					String amm=amount.getText();
					String pass=pw.getText();
					String accid;
					String paisa;
					String pin;
					
					int x = 0;
					int y=Integer.parseInt(amm);
					int z=0;
					
					 if(y > 0) { 
					  String sql = "Select * from account"; 
					  ResultSet rs = st.executeQuery(sql);
					  while(rs.next()) { 
					  accid = rs.getString("accountnumber"); 
					  paisa = rs.getString("accountbalance");
					  pin = rs.getString("password");
					  
					  if(acc.equals(accid) && pass.equals(pin)) { 
					  x=Integer.parseInt(amm)+Integer.parseInt(paisa);
					  JOptionPane.showMessageDialog(null, "Successfully Deposited!");
					  String sql1="update account set accountbalance='"+x+"' where accountnumber='"+accid+"'";
					  
					  PreparedStatement ptstmt = conn.prepareStatement(sql1);

					  ptstmt.executeUpdate();
					  conn.close();
					  z=0;
					  break;
					  }
					  else {
					  z=1;
					  }
}}
					
					  else {
					  JOptionPane.showMessageDialog(null, "Please Enter Correct Amount!");
					  }
					 if(z==1) {
						 JOptionPane.showMessageDialog(null, "Invalid Account number / Password!");
					 }
					 
		        }		     
		        catch(Exception ex){
		        JOptionPane.showMessageDialog(null, "Database Connection Error");
		        
		        }
		        }
				 
		        	
		        
		});
		btnNewButton.setBounds(261, 307, 109, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Money Deposit");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(148, 122, 140, 39);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard obj = new dashboard();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(31, 312, 89, 34);
		contentPane.add(btnNewButton_1);
		
		pw = new JPasswordField();
		pw.setBounds(148, 262, 164, 20);
		contentPane.add(pw);
		
		JLabel lblNewLabel_4 = new JLabel("Password: ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(59, 265, 79, 14);
		contentPane.add(lblNewLabel_4);
	}
}