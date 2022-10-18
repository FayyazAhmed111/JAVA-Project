package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class forgetpass extends JFrame {

	private JPanel contentPane;
	private JTextField phtext;
	private JTextField emailtext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgetpass frame = new forgetpass();
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
	public forgetpass() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(forgetpass.class.getResource("/swbank/Images/logopic.png")));
		setTitle("ForgetPassword - SWBank");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(forgetpass.class.getResource("/swbank/Images/transparentlogo.png")));
		lblNewLabel.setBounds(10, 11, 414, 135);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phone Number: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(69, 179, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(90, 221, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		phtext = new JTextField();
		phtext.setBounds(186, 176, 144, 20);
		contentPane.add(phtext);
		phtext.setColumns(10);
		
		emailtext = new JTextField();
		emailtext.setBounds(186, 218, 144, 20);
		contentPane.add(emailtext);
		emailtext.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("   Forgot Password");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(20, 122, 384, 33);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Get Password");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
					Statement st = conn.createStatement();
					
					String email = emailtext.getText();
					String phno = phtext.getText();
					String em,ph,pin;
					int x=0;
					
					String sql = "Select * from registernow";
					ResultSet rs = st.executeQuery(sql);
					  while(rs.next()) { 
					  em = rs.getString("email"); 
					  ph = rs.getString("phone");
					  pin = rs.getString("password");
					  if(email.equals(em) && phno.equals(ph)) { 
						  
						  JOptionPane.showMessageDialog(null, "Your Password is ' "+pin+" '");
						  conn.close();
						  x=0;
						  break;
					  }
					  else {
						  x=1;}
					  }
					  if (x==1) {
						  JOptionPane.showMessageDialog(null, "Email / Phone number doest not exist in our database!");
					  }
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Database connection Error!");
					
				}
				
				
				
			}
		});
		btnNewButton.setBounds(265, 268, 139, 33);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userloginpage obj = new userloginpage();
				obj.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(20, 268, 118, 33);
		contentPane.add(btnBack);
	}
}
