package swbank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Font;

public class dashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard frame = new dashboard();
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
	public dashboard() {
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(dashboard.class.getResource("/swbank/Images/logopic.png")));
		setTitle("Dashboard - SWBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userloginpage obj = new userloginpage();
				obj.setVisible(true);
				dispose();
			}
		});
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(dashboard.class.getResource("/swbank/Images/transparentlogo.png")));
		logo.setBounds(10, 11, 466, 133);
		contentPane.add(logo);
		
		JButton accbal = new JButton("Account Balance");
		accbal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accbal obj=new accbal();
				obj.setVisible(true);
				dispose();
				
			}
		});
		accbal.setFocusable(false);
		accbal.setBounds(47, 207, 155, 53);
		contentPane.add(accbal);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deposit obj = new deposit();
				obj.setVisible(true);
				dispose();
			}
		});
		btnDeposit.setFocusable(false);
		btnDeposit.setBounds(47, 304, 155, 53);
		contentPane.add(btnDeposit); 
		
		
		JButton btnGetLoan = new JButton("Money Transfer");
		btnGetLoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			transfer obj = new transfer();
			obj.setVisible(true);
			dispose();
			}
		});
		
		btnGetLoan.setFocusable(false);
		btnGetLoan.setBounds(276, 207, 155, 53);
		contentPane.add(btnGetLoan);
		
		JButton btnwithdraw = new JButton("Withdraw");
		btnwithdraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				withdraw obj = new withdraw();
				obj.setVisible(true);
				dispose();
			}
		});
		btnwithdraw.setFocusable(false);
		btnwithdraw.setBounds(276, 304, 155, 53);
		contentPane.add(btnwithdraw);
		
		JLabel lblNewLabel = new JLabel("User Dashboard\r\n");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 125, 384, 71);
		contentPane.add(lblNewLabel);
		
		JButton changepass = new JButton("Change Password");
		changepass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			passchange obj = new passchange();
			obj.setVisible(true);
			dispose();
			}
		});
		changepass.setFocusable(false);
		changepass.setBounds(47, 391, 155, 53);
		contentPane.add(changepass);
		
		JButton logout = new JButton("Logout");
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userloginpage obj = new userloginpage();
				obj.setVisible(true);
				dispose();
			}
		});
		logout.setFocusable(false);
		logout.setBounds(276, 391, 155, 53);
		contentPane.add(logout);
	}
}
