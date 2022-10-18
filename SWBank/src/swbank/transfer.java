package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class transfer extends JFrame {

	private JPanel contentPane;
	private JTextField accno;
	private JTextField reciver;
	private JTextField sendbal;
	private JPasswordField pass;
	int accnum;
	int recvrnum;
	int sendmoney;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transfer frame = new transfer();
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
	public transfer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(transfer.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Bank Transfer - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(transfer.class.getResource("/swbank/Images/transparentlogo.png")));
		lblNewLabel.setBounds(10, 11, 403, 119);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Amount: ");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(47, 249, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(47, 286, 117, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Account number: ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(47, 166, 117, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Reciver ID: ");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(20, 206, 144, 14);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Money Transfer");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		lblNewLabel_1_2_1.setBounds(10, 116, 403, 27);
		contentPane.add(lblNewLabel_1_2_1);
		
		accno = new JTextField();
		accno.setBounds(164, 164, 166, 20);
		contentPane.add(accno);
		accno.setColumns(10);
		
		reciver = new JTextField();
		reciver.setColumns(10);
		reciver.setBounds(164, 204, 166, 20);
		contentPane.add(reciver);
		
		sendbal = new JTextField();
		sendbal.setColumns(10);
		sendbal.setBounds(164, 247, 166, 20);
		contentPane.add(sendbal);
		
		pass = new JPasswordField();
		pass.setBounds(164, 284, 166, 20);
		contentPane.add(pass);
		
		JButton btnNewButton = new JButton("Transfer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					trans();
				}
			catch(Exception exy) {JOptionPane.showMessageDialog(null, "Error while contacting to database!");;}
			}
		});
		btnNewButton.setBounds(268, 319, 117, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			dashboard obj = new dashboard();
			obj.setVisible(true);
			dispose();
			}
		});
		btnNewButton_1.setBounds(23, 321, 89, 23);
		contentPane.add(btnNewButton_1);
	}
   public	void trans() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swbank","root", "");	
			Statement st = conn.createStatement();
			Statement st1 = conn.createStatement();
			int x=0;
			int update1=0;
			int update2 = 0;
			int logic=0;
			int logic1=0;
			int bal = 0;
				accnum=Integer.parseInt(accno.getText());
				recvrnum=Integer.parseInt(reciver.getText());
				sendmoney=Integer.parseInt(sendbal.getText());
				String pw = pass.getText();
				if(sendmoney>0) {
					String sql="select * from account";
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) {
						int acc=rs.getInt("accountnumber");
						bal = rs.getInt("accountbalance");
						String passw= rs.getString("password");
						if(accnum==acc&&pw.equals(passw)) {
							update1=bal-sendmoney;
							logic=123;
							ResultSet rs1 = st1.executeQuery(sql);
							while (rs1.next()) {
								acc = rs1.getInt("accountnumber");
								bal = rs1.getInt("accountbalance");
								if(recvrnum==acc) {
									update2=bal+sendmoney;
									logic1=123;
									x=0;
									break;
									}
								else {
									x=1;
									}		
											}
								break;}
						else {
							x=2;     
							}
									}
			
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter correct amount!");
			}
			if(x==1) {JOptionPane.showMessageDialog(null, "Invalid Reciver's account number!");}
			if(x==2) {JOptionPane.showMessageDialog(null, "Invalid Account number or Password !");}
			
			if(logic==123&&logic1==123) {
				if(update1>=0) {
				String sql1="update account set accountbalance='"+update1+"' where accountnumber='"+accnum+"'";
				PreparedStatement ptstmt = conn.prepareStatement(sql1);
				ptstmt.executeUpdate();
				String sql2="update account set accountbalance='"+update2+"' where accountnumber='"+recvrnum+"'";
				PreparedStatement ptstmt1 = conn.prepareStatement(sql2);
				ptstmt1.executeUpdate();
				JOptionPane.showMessageDialog(null, "Money Transferred Succesfully!");	
			}
			else {JOptionPane.showMessageDialog( null, "Insufficient Balance!");}
		}} catch (Exception e2) {
		JOptionPane.showMessageDialog(null, e2);
		}		
	}
	
}