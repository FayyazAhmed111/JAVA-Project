package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class registernow extends JFrame {

	private JPanel contentPane;
	private JTextField inibal;
	private JTextField idnum;
	private JTextField accnum;
	private JTextField idtype;
	private JTextField email;
	private JTextField phone;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registernow frame = new registernow();
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
	public registernow() {
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(registernow.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Register Now - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 656);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel registerlabel = new JLabel("Registration Form");
		registerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerlabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		registerlabel.setBounds(143, 11, 201, 114);
		contentPane.add(registerlabel);
		
		JLabel lblNewLabel = new JLabel("ID Type:");
		lblNewLabel.setBounds(20, 136, 69, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblIdNumber = new JLabel("ID Number: \r\n");
		lblIdNumber.setBounds(20, 217, 69, 21);
		contentPane.add(lblIdNumber);
		
		JLabel lblAccountNumber = new JLabel("Account Number: ");
		lblAccountNumber.setBounds(20, 268, 115, 21);
		contentPane.add(lblAccountNumber);
		
		JLabel lblEmailId = new JLabel("Email ID: ");
		lblEmailId.setBounds(20, 320, 103, 21);
		contentPane.add(lblEmailId);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setBounds(20, 370, 103, 21);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblDateOfBirth = new JLabel("Initial Balance: ");
		lblDateOfBirth.setBounds(20, 416, 103, 21);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(20, 474, 89, 21);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(20, 528, 89, 21);
		contentPane.add(lblPassword);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedValue = comboBox.getSelectedItem().toString();
				idtype.setText(selectedValue);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"National Identity Card (NIC)", "Passport Number"}));
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(143, 136, 201, 22);
		contentPane.add(comboBox);
		
		inibal = new JTextField();
		inibal.setEditable(false);
		inibal.setText("0");
		inibal.setBounds(143, 416, 201, 20);
		contentPane.add(inibal);
		inibal.setColumns(10);
		
		idnum = new JTextField();
		idnum.setColumns(10);
		idnum.setBounds(143, 217, 201, 20);
		contentPane.add(idnum);
		
		accnum = new JTextField();
		accnum.setColumns(10);
		accnum.setBounds(143, 268, 201, 20);
		contentPane.add(accnum);
		
		idtype = new JTextField();
		idtype.setText("National Identity Card (NIC)");
		idtype.setColumns(10);
		idtype.setBounds(143, 169, 201, 20);
		idtype.setEditable(false);
		contentPane.add(idtype);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(143, 320, 201, 20);
		contentPane.add(email);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(143, 370, 201, 20);
		contentPane.add(phone);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(143, 474, 201, 20);
		contentPane.add(user);
		
		pass = new JPasswordField();
		pass.setBounds(143, 528, 201, 20);
		contentPane.add(pass);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(registernow.class.getResource("/swbank/Images/logopic.png")));
		lblNewLabel_1.setBounds(10, 0, 142, 125);
		contentPane.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFocusable(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userloginpage obj = new userloginpage();
				obj.setVisible(true);
				dispose();
			}
		}); 
		btnBack.setBounds(20, 583, 89, 23);
		contentPane.add(btnBack);
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setFocusable(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(phone.getText().isEmpty()||accnum.getText().isEmpty()||email.getText().isEmpty()||idnum.getText().isEmpty()||user.getText().isEmpty()||pass.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter all values correctly!");
					}
					else {
					if(email.getText() .contains("@")&&email.getText().contains(".")) {
					
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
						String sql = "insert into registernow values(?,?,?,?,?,?,?,?)";
						String sql1 = "insert into login values(?,?)";
						String sql2 = "insert into account values(?,?,?)";
						PreparedStatement ptstmt= conn.prepareStatement(sql);
						PreparedStatement ptstmt1= conn.prepareStatement(sql1);
						PreparedStatement ptstmt2= conn.prepareStatement(sql2);
						ptstmt.setString(1,idtype.getText());
						ptstmt.setString(2,idnum.getText());
						ptstmt.setString(3,accnum.getText());
						ptstmt.setString(4,email.getText());
						ptstmt.setString(5,phone.getText());
						ptstmt.setString(6,inibal.getText());
						ptstmt.setString(7,user.getText());
						ptstmt.setString(8,pass.getText()); 
						ptstmt.executeUpdate();
						ptstmt1.setString(1,user.getText());
						ptstmt1.setString(2, pass.getText());
						ptstmt1.executeUpdate();
						
		
						ptstmt2.setString(1,accnum.getText());
						ptstmt2.setString(2,inibal.getText());
						ptstmt2.setString(3,pass.getText());
						ptstmt2.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Registered succesfully!");
						
						conn.close();					
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter correct email!");
					}
					}
				} catch (Exception e2) { 
					
					JOptionPane.showMessageDialog(null, "Error While Connecting To Database!");
				}
			}
		});
		btnNewButton.setBounds(280, 583, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}

	private Object Component (JTextField idtype2) {
		return null;
	}
}
