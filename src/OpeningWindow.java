import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DebugGraphics;
import java.awt.Color;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import java.awt.Font;

public class OpeningWindow extends JFrame {

	private JPanel contentPane;
	action ac = new action();
	FileHandle f = new FileHandle();


	/**
	 / Create the frame.
	 */
	public OpeningWindow() {
		GUI();
	}
	
	public void GUI() {
		setResizable(false);
		setVisible(true);
		
		setTitle("Money Tracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 10, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton options = new JButton("Database Options");
		options.setFont(new Font("Arial", Font.BOLD, 14));
		options.setBackground(Color.WHITE);
		options.setBounds(239, 302, 161, 44);
		options.addActionListener(ac);
		contentPane.add(options);
		
		JButton Money_Tracker = new JButton("Money Tracker");
		Money_Tracker.setFont(new Font("Arial", Font.BOLD, 14));
		Money_Tracker.setBackground(Color.WHITE);
		Money_Tracker.setBounds(239, 101, 161, 44);
		Money_Tracker.addActionListener(ac);
		contentPane.add(Money_Tracker);
		
		JButton Exit = new JButton("Exit");
		Exit.setFont(new Font("Arial", Font.BOLD, 14));
		Exit.setBackground(Color.WHITE);
		Exit.setBounds(239, 544, 161, 44);
		Exit.addActionListener(ac);
		contentPane.add(Exit);
		
	}

    class action implements ActionListener {
    	

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		
		switch(a) {
		
		case("Money Tracker"):   mainFrame(); break;
		case("Database Options"): new Options(); break;
		case("Exit"):  System.exit(0); break;
		}
		
	}

}
    
    public void mainFrame() {
	  MainFrame frame = new MainFrame(); 
	  frame.setVisible(true); 
	  this.setVisible(false);
    }
   

}
