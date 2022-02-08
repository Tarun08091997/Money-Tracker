import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Options extends JFrame {

	private JPanel contentPane;
	public JRadioButton no_rbtn_O;
	public JRadioButton yes_rbtn_O;
	public boolean no_btn_state;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblUrl;
	private JTextField user_txtfld_O;
	private JTextField url_txtfld_O;
	private JPasswordField passwordField;
	
	
	public static String username = null;                            // to be used in other programs
	public static String password = null;
	public static String url = null;
	
	
	JButton ok_btn_O;
	JButton btnCancel;
	
	
	action ac = new action();
	FileHandle f = new FileHandle();
	private JButton reset_btn_O;


	/**
	 * Create the frame.
	 */
	public Options() {
		System.out.println("lol");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 50, 794, 344);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 139));
		panel.setBounds(0, 0, 778, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Do you want to use SQL (Database)   ");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel.setBackground(new Color(240, 248, 255));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setBounds(34, 11, 403, 35);
		panel.add(lblNewLabel);
		
		yes_rbtn_O = new JRadioButton("YES");
		yes_rbtn_O.setFont(new Font("Arial Black", Font.BOLD, 14));
		yes_rbtn_O.setHorizontalTextPosition(SwingConstants.RIGHT);
		yes_rbtn_O.setHorizontalAlignment(SwingConstants.LEFT);
		yes_rbtn_O.setAlignmentX(Component.CENTER_ALIGNMENT);
		yes_rbtn_O.setBounds(456, 19, 66, 23);
		
		yes_rbtn_O.addActionListener(ac);
		panel.add(yes_rbtn_O);
		
		no_rbtn_O = new JRadioButton("NO");
		no_rbtn_O.setSelected(true);
		no_rbtn_O.setHorizontalTextPosition(SwingConstants.RIGHT);
		no_rbtn_O.setHorizontalAlignment(SwingConstants.LEFT);
		no_rbtn_O.setFont(new Font("Arial Black", Font.BOLD, 14));
		no_rbtn_O.setAlignmentX(0.5f);
		no_rbtn_O.setBounds(569, 19, 66, 23);
		
		no_rbtn_O.addActionListener(ac);
		panel.add(no_rbtn_O);
		
		lblUser = new JLabel("User Name");
		lblUser.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblUser.setBackground(new Color(240, 248, 255));
		lblUser.setAlignmentX(0.5f);
		lblUser.setBounds(102, 74, 125, 28);
		panel.add(lblUser);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblPassword.setBackground(new Color(240, 248, 255));
		lblPassword.setAlignmentX(0.5f);
		lblPassword.setBounds(102, 125, 125, 28);
		panel.add(lblPassword);
		
		lblUrl = new JLabel("URL");
		lblUrl.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrl.setForeground(Color.WHITE);
		lblUrl.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblUrl.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblUrl.setBackground(new Color(240, 248, 255));
		lblUrl.setAlignmentX(0.5f);
		lblUrl.setBounds(102, 183, 125, 28);
		panel.add(lblUrl);
		
		user_txtfld_O = new JTextField();
		user_txtfld_O.setEditable(false);
		user_txtfld_O.setText(null);
		user_txtfld_O.setHorizontalAlignment(SwingConstants.LEFT);
		user_txtfld_O.setFont(new Font("Arial Black", Font.BOLD, 16));
		user_txtfld_O.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		user_txtfld_O.setBounds(343, 73, 205, 35);
		panel.add(user_txtfld_O);
		user_txtfld_O.setColumns(10);
		
		url_txtfld_O = new JTextField();
		url_txtfld_O.setText(null);
		url_txtfld_O.setHorizontalAlignment(SwingConstants.LEFT);
		url_txtfld_O.setFont(new Font("Arial Black", Font.BOLD, 16));
		url_txtfld_O.setEditable(false);
		url_txtfld_O.setColumns(10);
		url_txtfld_O.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		url_txtfld_O.setBounds(343, 176, 205, 35);
		panel.add(url_txtfld_O);
		
		passwordField = new JPasswordField();
    	passwordField.setText(null);
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setEditable(false);
		passwordField.setFont(new Font("Arial Black", Font.BOLD, 16));
		passwordField.setEchoChar('*');
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(343, 125, 205, 35);
		panel.add(passwordField);
		
		ok_btn_O = new JButton("OK");
		ok_btn_O.setBackground(new Color(255, 255, 0));
		ok_btn_O.setFont(new Font("Arial Black", Font.BOLD, 14));
		ok_btn_O.setBounds(485, 245, 89, 34);
		ok_btn_O.setEnabled(false);
		ok_btn_O.addActionListener(ac);
		panel.add(ok_btn_O);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.setBounds(626, 245, 89, 34);
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(ac);
		panel.add(btnCancel);
		
		reset_btn_O = new JButton("Reset");
		reset_btn_O.setFont(new Font("Arial Black", Font.BOLD, 14));
		reset_btn_O.setBackground(new Color(0, 255, 0));
		reset_btn_O.setBounds(173, 245, 125, 34);
		reset_btn_O.addActionListener(ac);
		panel.add(reset_btn_O);
		}
	   
	    public class action implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String a = e.getActionCommand();
				switch(a) {
				case("YES"):  yes_btn_action(); return;
				case("NO"):  no_btn_action(); return;
				case("OK"):  get_value(); return;
				case("Cancel"): no_rbtn_O.setSelected(true); no_btn_action(); return;
				case("Reset"): reset(); return; 
				}
				
			}
	    	
	    }
	    
	    public void reset() {
	    	int a=JOptionPane.showConfirmDialog(this,"Do you want to reset data of username,password and url","Warning", JOptionPane.OK_CANCEL_OPTION);
	    	if(a==JOptionPane.OK_OPTION) {
	  	      f.addpassword(null);
    	      f.addusername(null);
    	      f.addurl(null);
	    	}
	    }
	    
	    
	    
	    
	    public void get_value() {
	    	
	    	username = user_txtfld_O.getText();
	    	password = passwordField.getText();
	    	url = url_txtfld_O.getText();
	    	if(username !=null || password!= null || url != null) {
	    	      f.addpassword(password);
	    	      f.addusername(username);
	    	    f.addurl(url);
	    	}else {
	    		JOptionPane.showMessageDialog(this, "Please fill the detail properly");
	    	}
	    	
	    }
	    
	    
	    
	    
	    public void no_btn_action() 
	    { 
	    	if(true) {
	    	user_txtfld_O.setText(null);
	    	url_txtfld_O.setText(null);
	    	passwordField.setText(null);
	 		yes_rbtn_O.setSelected(false);
			user_txtfld_O.setEditable(false);
			url_txtfld_O.setEditable(false);
			passwordField.setEditable(false);
			ok_btn_O.setEnabled(false);
			btnCancel.setEnabled(false);
	    }
	    }
	    
	    public void yes_btn_action() {
	    	
	    	if(true) {
	    		
	    		no_rbtn_O.setSelected(false);
				user_txtfld_O.setEditable(true);
				url_txtfld_O.setEditable(true);
				passwordField.setEditable(true);
				ok_btn_O.setEnabled(true);
				btnCancel.setEnabled(true);
	    		
	    		
	    	}
	    	
	    	
	    }
				
			
	    
	
}
