package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;

public class passchange extends JFrame {

	private JPanel contentPane;
	private JPasswordField pichla;
	private JPasswordField naya;
	private JTextField user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					passchange frame = new passchange();
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
	public passchange() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(passchange.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Password change - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(passchange.class.getResource("/swbank/Images/transparentlogo.png")));
		lblNewLabel.setBounds(10, 0, 414, 134);
		contentPane.add(lblNewLabel);
		
		pichla = new JPasswordField();
		pichla.setBounds(163, 217, 158, 20);
		contentPane.add(pichla);
		
		naya = new JPasswordField();
		naya.setBounds(163, 253, 158, 20);
		contentPane.add(naya);
		
		JLabel lblNewLabel_1 = new JLabel("New Password: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(44, 256, 108, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Previous Password: ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(32, 220, 121, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Change Password");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 119, 414, 46);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
					Statement st = conn.createStatement();
					String username = user.getText();
					String puranapass = pichla.getText();
					String nayapass = naya.getText();
					String accnu;
					String pw,usr;
					int x=0;
					
					String sql = "Select * from registernow"; 
					  ResultSet rs = st.executeQuery(sql);
					  while(rs.next()) { 
					  usr = rs.getString("username");
					  pw = rs.getString("password");
					  accnu = rs.getString("accno");
					  if(username.equals(usr) && puranapass.equals(pw)) {
						  
						  String sql1="update login set password='"+nayapass+"' where password='"+puranapass+"' and username = '"+usr+"'";
						  PreparedStatement ptstmt = conn.prepareStatement(sql1);
						  ptstmt.executeUpdate();
						  JOptionPane.showMessageDialog(null, "Password Changed Successfully!");
						  
						  String sql2="update registernow set password='"+nayapass+"' where password='"+puranapass+"' and username = '"+usr+"'";
						  String sql3="update account set password='"+nayapass+"' where password='"+puranapass+"' and accountnumber = '"+accnu+"'";
						  PreparedStatement ptstmt1 = conn.prepareStatement(sql2);
						  PreparedStatement ptstmt2 = conn.prepareStatement(sql3);
						  ptstmt1.executeUpdate();
						  ptstmt2.executeUpdate();
						  conn.close();
						  x=0;
						  break;
						  
					  }
					  else
					  {x=1;}
					  }
					  if(x==1){JOptionPane.showMessageDialog(null, "Wrong username / password!");}
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error While contacting to database!");
				}
			}
		});
		btnNewButton.setBounds(250, 298, 151, 37);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			dashboard obj = new dashboard();
			obj.setVisible(true);
			dispose();
			}
		});
		btnBack.setBounds(32, 305, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("Username: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 187, 121, 14);
		contentPane.add(label);
		
		user = new JTextField();
		user.setBounds(163, 184, 158, 20);
		contentPane.add(user);
		user.setColumns(10);
	}

}
