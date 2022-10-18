package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class accbal extends JFrame {

	private JPanel contentPane;
	private JTextField bal;
	private JPasswordField pas;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accbal frame = new accbal();
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
	public accbal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(accbal.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Account Balance - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 365);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(accbal.class.getResource("/swbank/Images/transparentlogo.png")));
		lblNewLabel.setBounds(10, 11, 381, 124);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Available Balance");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(10, 128, 381, 48);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboard obj=new dashboard();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(30, 277, 89, 38);
		contentPane.add(btnNewButton);
		
		bal = new JTextField();
		bal.setBounds(190, 187, 176, 20);
		contentPane.add(bal);
		bal.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Account Number: ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(30, 190, 157, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Get Balance");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
					Statement st = conn.createStatement();
					String accnum = bal.getText();
					String pin = pas.getText();
					String accnu = null;
					String paisa = null;
					String pass = null;
					int x = 0;
					String sql = "Select * from account"; 
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) { 
						  accnu = rs.getString("accountnumber");
						  paisa = rs.getString("accountbalance");
						  pass = rs.getString("password"); 
						  
						  
						  if(accnum.equals(accnu) && pin.equals(pass)) {
							  
							  JOptionPane.showMessageDialog(null, "Your current balance is " +paisa+" RS.");
							  x=0;
							  break;
						  } 
						 
					  else {
						  x=1;
						  }
					  }//whileloop end
					  
					  if(x==1) {
						 JOptionPane.showMessageDialog(null, "Invalid Account number / Password!");
						 
					  }
					  
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error while connecting to database!");
				}
			}
		});
		btnNewButton_1.setBounds(251, 277, 115, 38);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Password: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(58, 231, 129, 14);
		contentPane.add(lblNewLabel_2);
		
		pas = new JPasswordField();
		pas.setBounds(190, 228, 176, 20);
		contentPane.add(pas);
		

	}
}
