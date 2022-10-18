package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;

public class userloginpage extends JFrame {

	private JPanel contentPane;
	private JTextField usertxtfield;
	private JPasswordField passfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userloginpage frame = new userloginpage();
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
	public userloginpage() {
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(userloginpage.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Userlogin - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 397);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernamelabel = new JLabel("Username: ");
		usernamelabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		usernamelabel.setBounds(87, 151, 103, 38);
		contentPane.add(usernamelabel);
		
		JLabel passlabel = new JLabel("Password: ");
		passlabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		passlabel.setBounds(87, 200, 103, 38);
		contentPane.add(passlabel);
		
		usertxtfield = new JTextField();
		usertxtfield.setBounds(179, 159, 150, 27);
		contentPane.add(usertxtfield);
		usertxtfield.setColumns(10);
		
		passfield = new JPasswordField();
		passfield.setBounds(179, 206, 150, 30);
		contentPane.add(passfield);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setFocusable(false);
		loginbtn.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
					Statement st = conn.createStatement();
					
					String un=usertxtfield.getText();
					String pw=passfield.getText();
					String username;
					String password;
					int x=0;
					
					  String sql = "Select * from login"; 
					  ResultSet rs = st.executeQuery(sql);
					  while(rs.next()) { 
					  username = rs.getString("username"); 
					  password = rs.getString("password"); 
					  
					  
					  if(un.equals(username) && pw.equals(password)) { 
						  new dashboard().setVisible(true); 
						  dispose();
						  x=1;
					  } 
					 
					  
					  }
					  if(x==1) {
						  //to prevent looping username-pass incorrect outputs.
						  //created by 21SW111
					  }
					  else {
						  JOptionPane.showMessageDialog(null, "Username Password Incorrect!");
					  }
					  
					  conn.close(); 
					 
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error while connection to database!");
				}
			}
		});
		loginbtn.setBounds(179, 256, 150, 54);
		contentPane.add(loginbtn);
		
		JLabel forgetpass = new JLabel("Forgot Password?");
		forgetpass.setForeground(Color.blue);
		forgetpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				forgetpass obj = new forgetpass();
				obj.setVisible(true);
				dispose();
			}
		});
		forgetpass.setBounds(215, 239, 127, 14);
		contentPane.add(forgetpass);
		
		JLabel registernow = new JLabel("            Want to join SWBank? Register now!");
		registernow.setForeground(new Color(0, 0, 255));
		registernow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			registernow obj = new registernow();
			obj.setVisible(true);
			dispose();
			}
		});
		registernow.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		registernow.setBounds(112, 321, 284, 26);
		contentPane.add(registernow);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(userloginpage.class.getResource("/swbank/Images/transparentlogo.png")));
		lblNewLabel.setBounds(56, 11, 393, 129);
		contentPane.add(lblNewLabel);
	}
}
