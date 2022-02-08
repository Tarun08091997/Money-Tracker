/*
 *  money_txtfld_E -> monew txtfield in Expense tab
 *   add_btn_E  -> Add button in Expense
 *   cat_cbox_E  -> combo box for category
 *   
 *   db is object of JDBC used for database entries having connection c and statement s
 *   
 *   
 */

import java.awt.BorderLayout;
import java.lang.NumberFormatException;
import java.util.Date;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.OptionPaneUI;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Window.Type;
import java.awt.Frame;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame implements WindowListener{
	
	// Variables of Expense Tab
	public JTextField money_txtfld_E;    // The money space
	public JButton add_btn_E, addcat_btn_E,del_btn_E;
    public	JComboBox cat_cbox_E;
    JDateChooser date_E;
    JLabel Total_E;
	JTable table_E;
	
	//Variables of View Spanding
	
	public JDateChooser from_date_VS,to_date_VS;
	public JComboBox cat_cbox_VS;
	public JButton search_btn_VS;
	public JLabel total_VS;
	public JTable table_VS;
	
	
	JTable view_table;
	
	
	JDBC db = new JDBC();
	Action ac = new Action();

	NewCategoryFrame ncf= new NewCategoryFrame();
	
	FileHandle f = new FileHandle();
	
	java.time.LocalDate dc = java.time.LocalDate.now().minusDays(30);  // date from 20 days past today
	
	
	
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		ncf.addWindowListener(this);
		GUI();
		displayCategory(cat_cbox_E);
		displayCategory(cat_cbox_VS);
		cat_cbox_VS.setSelectedItem(null);
		cat_cbox_VS.addItem(null);
		getEntries_Excel();
		viewAllSpendings();
		
		
		
	}
	
	// Functions for Expense
	
	public void GUI() {
		{
			
			//main Frame
			setName("MainFrame");
			setVisible(true);
			setAlwaysOnTop(true);
			setForeground(new Color(0, 0, 0));
			setFont(new Font("Arial Black", Font.BOLD, 12));
			setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TARUN\\eclipse-workspace\\Money_Tracker\\Files\\Money_Track logo.png"));
			setTitle("Money Tracker");
			getContentPane().setFont(new Font("Arial Black", Font.BOLD, 11));
			getContentPane().setLayout(null);
			setVisible(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			addWindowListener(this);
			setBackground(new Color(173, 216, 230));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setBounds(0, 0, 1383, 744);
			setBounds(0, 0, 1383, 300);
			// Tabbed Pane
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFont(new Font("Arial Black", Font.PLAIN, 14));
			tabbedPane.setBounds(0, 0, 1367, 705);
			getContentPane().add(tabbedPane);
			
			
			// Panels used for Tabs
			
			JPanel Add_Expense = new JPanel();  
			tabbedPane.addTab("Add Expenses", null, Add_Expense, "Add new expenses");
			Add_Expense.setLayout(null);
			
			
			// Panels Used
			
			JPanel titel_Panel_Expenses = new JPanel();
			titel_Panel_Expenses.setForeground(new Color(0, 0, 0));
			titel_Panel_Expenses.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			titel_Panel_Expenses.setBackground(new Color(0, 0, 255));
			titel_Panel_Expenses.setBounds(0, 0, 1362, 149);
			Add_Expense.add(titel_Panel_Expenses);
			titel_Panel_Expenses.setLayout(null);
			
			
			
			
			// Labels Used
			
			JLabel title_Label_Expenses = new JLabel("Add Expenses");
			title_Label_Expenses.setHorizontalAlignment(SwingConstants.CENTER);
			title_Label_Expenses.setFont(new Font("Arial Black", Font.BOLD, 22));
			title_Label_Expenses.setForeground(new Color(255, 255, 255));
			title_Label_Expenses.setBackground(new Color(0, 0, 255));
			title_Label_Expenses.setBounds(0, 11, 1362, 41);
			titel_Panel_Expenses.add(title_Label_Expenses);
			
			
			
			JLabel date_Label_Expenses = new JLabel("Date :");
			date_Label_Expenses.setForeground(new Color(255, 255, 255));
			date_Label_Expenses.setHorizontalAlignment(SwingConstants.CENTER);
			date_Label_Expenses.setFont(new Font("Arial Black", Font.BOLD, 16));
			date_Label_Expenses.setBackground(new Color(0, 0, 255));
			date_Label_Expenses.setBounds(0, 90, 83, 29);
			titel_Panel_Expenses.add(date_Label_Expenses);
			
			JLabel money_Label_Expenses = new JLabel("Money:");
			money_Label_Expenses.setHorizontalAlignment(SwingConstants.CENTER);
			money_Label_Expenses.setForeground(Color.WHITE);
			money_Label_Expenses.setFont(new Font("Arial Black", Font.BOLD, 16));
			money_Label_Expenses.setBackground(Color.BLUE);
			money_Label_Expenses.setBounds(223, 85, 83, 29);
			titel_Panel_Expenses.add(money_Label_Expenses);
			
			JLabel category_Label_Expenses = new JLabel("Category:");
			category_Label_Expenses.setHorizontalAlignment(SwingConstants.CENTER);
			category_Label_Expenses.setForeground(Color.WHITE);
			category_Label_Expenses.setFont(new Font("Arial Black", Font.BOLD, 16));
			category_Label_Expenses.setBackground(Color.BLUE);
			category_Label_Expenses.setBounds(478, 90, 104, 29);
			titel_Panel_Expenses.add(category_Label_Expenses);
			
			JLabel TotalSpanding_Label_Expenses = new JLabel("Total Spanding:");
			TotalSpanding_Label_Expenses.setHorizontalAlignment(SwingConstants.CENTER);
			TotalSpanding_Label_Expenses.setFont(new Font("Arial Black", Font.BOLD, 16));
			TotalSpanding_Label_Expenses.setBounds(156, 641, 267, 29);
			Add_Expense.add(TotalSpanding_Label_Expenses);
			
			Total_E = new JLabel("");
			Total_E.setBackground(new Color(255, 255, 255));
			Total_E.setHorizontalAlignment(SwingConstants.CENTER);
			Total_E.setFont(new Font("Arial Black", Font.BOLD, 16));
			Total_E.setBounds(787, 641, 267, 29);
			Add_Expense.add(Total_E);
			
			JLabel MiddleLabel_Expenses = new JLabel("Last 20 days Expenses Expenses");
			MiddleLabel_Expenses.setHorizontalAlignment(SwingConstants.CENTER);
			MiddleLabel_Expenses.setForeground(new Color(0, 0, 0));
			MiddleLabel_Expenses.setFont(new Font("Arial Black", Font.BOLD, 16));
			MiddleLabel_Expenses.setBackground(new Color(255, 255, 255));
			MiddleLabel_Expenses.setBounds(10, 160, 367, 29);
			Add_Expense.add(MiddleLabel_Expenses);
			
			
			
			//Buttons
			
			add_btn_E = new JButton("ADD");
			add_btn_E.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add_btn_E.addActionListener(ac);
			
			add_btn_E.setForeground(new Color(0, 0, 0));
			add_btn_E.setBackground(new Color(255, 255, 0));
			add_btn_E.setFont(new Font("Arial Black", Font.BOLD, 16));
			add_btn_E.setBounds(1138, 85, 94, 49);
			titel_Panel_Expenses.add(add_btn_E);
			
			
			addcat_btn_E = new JButton("Add New Category");
			addcat_btn_E.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addcat_btn_E.setForeground(new Color(255, 255, 255));
			addcat_btn_E.setBackground(new Color(0, 128, 0));
			addcat_btn_E.setFont(new Font("Arial", Font.BOLD, 16));
			addcat_btn_E.setBounds(838, 90, 206, 29);
			addcat_btn_E.addActionListener(ac);
			titel_Panel_Expenses.add(addcat_btn_E);
			
			
			
			del_btn_E = new JButton("Delete");
			del_btn_E.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			del_btn_E.setForeground(new Color(0, 0, 0));
			del_btn_E.setFont(new Font("Arial", Font.BOLD, 16));
			del_btn_E.setBackground(new Color(255, 0, 0));
			del_btn_E.setBounds(1159, 160, 125, 29);
			del_btn_E.addActionListener(ac);
			Add_Expense.add(del_btn_E);
			
			
			// TextField
			
			money_txtfld_E = new JTextField();
			money_txtfld_E.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
			money_txtfld_E.setBounds(316, 90, 122, 29);
			titel_Panel_Expenses.add(money_txtfld_E);
			money_txtfld_E.setColumns(10);
			
			
			//ComboBox
			
			cat_cbox_E = new JComboBox();
			cat_cbox_E.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cat_cbox_E.setBounds(607, 90, 169, 29);
			cat_cbox_E.addActionListener(ac);
			titel_Panel_Expenses.add(cat_cbox_E);
			
			
			
			
			
			
			//DateChooser
			
			date_E = new JDateChooser();
			date_E.setAlignmentX(Component.LEFT_ALIGNMENT);
			date_E.setBounds(86, 90, 127, 29);
			date_E.setSelectableDateRange(null, new java.util.Date());
			titel_Panel_Expenses.add(date_E);
			
			
			
			// Scroll Pane
			
			JScrollPane scrollPane_Expenses = new JScrollPane();
			scrollPane_Expenses.setBounds(0, 195, 1362, 442);
			Add_Expense.add(scrollPane_Expenses);
			
			
			table_E = new JTable();
			table_E.setFillsViewportHeight(true);
			table_E.setFont(new Font("Arial", Font.PLAIN, 14));
			table_E.setAlignmentX(CENTER_ALIGNMENT);
			table_E.setSurrendersFocusOnKeystroke(true);
			table_E.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Date", "Category", "Money"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			
			
			table_E.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			table_E.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			table_E.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
			table_E.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			
			scrollPane_Expenses.setViewportView(table_E);		
			
			
			
			
			// View Spending Panel and Table
			
			JPanel ViewSpendings_Panel = new JPanel();
			ViewSpendings_Panel.setForeground(new Color(0, 0, 0));
			tabbedPane.addTab("View Spandings", null, ViewSpendings_Panel, null);
			tabbedPane.setEnabledAt(1, true);
			ViewSpendings_Panel.setLayout(null);
			
			JPanel title_Panel_ViewSpendings = new JPanel();
			title_Panel_ViewSpendings.setBackground(new Color(0, 120, 215));
			title_Panel_ViewSpendings.setBounds(0, 0, 1362, 138);
			ViewSpendings_Panel.add(title_Panel_ViewSpendings);
			title_Panel_ViewSpendings.setLayout(null);
			
			
			JLabel title_Label_ViewSpendings = new JLabel("View Spending");
			title_Label_ViewSpendings.setForeground(new Color(255, 255, 255));
			title_Label_ViewSpendings.setHorizontalAlignment(SwingConstants.CENTER);
			title_Label_ViewSpendings.setFont(new Font("Arial Black", Font.BOLD, 24));
			title_Label_ViewSpendings.setBounds(0, 11, 1362, 37);
			title_Panel_ViewSpendings.add(title_Label_ViewSpendings);
			
			
			JLabel date_Label_ViewSpendings = new JLabel("Date:");
			date_Label_ViewSpendings.setFont(new Font("Arial Black", Font.BOLD, 16));
			date_Label_ViewSpendings.setHorizontalAlignment(SwingConstants.CENTER);
			date_Label_ViewSpendings.setForeground(new Color(255, 255, 255));
			date_Label_ViewSpendings.setBounds(332, 51, 116, 29);
			title_Panel_ViewSpendings.add(date_Label_ViewSpendings);
			
			JLabel from_Label_ViewSpendings = new JLabel("From:");
			from_Label_ViewSpendings.setHorizontalAlignment(SwingConstants.CENTER);
			from_Label_ViewSpendings.setForeground(Color.WHITE);
			from_Label_ViewSpendings.setFont(new Font("Arial Black", Font.BOLD, 16));
			from_Label_ViewSpendings.setBounds(43, 91, 75, 29);
			title_Panel_ViewSpendings.add(from_Label_ViewSpendings);
			
			JLabel to_Label_ViewSpendings = new JLabel("To:");
			to_Label_ViewSpendings.setHorizontalAlignment(SwingConstants.CENTER);
			to_Label_ViewSpendings.setForeground(Color.WHITE);
			to_Label_ViewSpendings.setFont(new Font("Arial Black", Font.BOLD, 16));
			to_Label_ViewSpendings.setBounds(412, 91, 36, 29);
			title_Panel_ViewSpendings.add(to_Label_ViewSpendings);
			
			JLabel category_Label_ViewSpanding = new JLabel("Category:");
			category_Label_ViewSpanding.setHorizontalAlignment(SwingConstants.CENTER);
			category_Label_ViewSpanding.setForeground(Color.WHITE);
			category_Label_ViewSpanding.setFont(new Font("Arial Black", Font.BOLD, 16));
			category_Label_ViewSpanding.setBounds(733, 91, 116, 29);
			title_Panel_ViewSpendings.add(category_Label_ViewSpanding);
			
			
			JLabel label_ViewSpanding = new JLabel("This Month Expenses");
			label_ViewSpanding.setHorizontalAlignment(SwingConstants.CENTER);
			label_ViewSpanding.setForeground(Color.BLACK);
			label_ViewSpanding.setFont(new Font("Arial Black", Font.BOLD, 16));
			label_ViewSpanding.setBackground(Color.WHITE);
			label_ViewSpanding.setBounds(10, 149, 257, 29);
			ViewSpendings_Panel.add(label_ViewSpanding);
			
			JLabel Totalspanding_Label_ViewSpanding = new JLabel("Total Spanding:");
			Totalspanding_Label_ViewSpanding.setHorizontalAlignment(SwingConstants.CENTER);
			Totalspanding_Label_ViewSpanding.setFont(new Font("Arial Black", Font.BOLD, 16));
			Totalspanding_Label_ViewSpanding.setBounds(219, 630, 267, 29);
			ViewSpendings_Panel.add(Totalspanding_Label_ViewSpanding);
			
			total_VS = new JLabel("");
			total_VS.setHorizontalAlignment(SwingConstants.CENTER);
			total_VS.setFont(new Font("Arial Black", Font.BOLD, 16));
			total_VS.setBackground(Color.WHITE);
			total_VS.setBounds(841, 630, 267, 29);
			ViewSpendings_Panel.add(total_VS);
			
			
			search_btn_VS = new JButton("Search");
			search_btn_VS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			search_btn_VS.setForeground(Color.BLACK);
			search_btn_VS.setFont(new Font("Arial", Font.BOLD, 16));
			search_btn_VS.setBackground(new Color(255, 255, 0));
			search_btn_VS.setBounds(1213, 67, 100, 37);
			search_btn_VS.addActionListener(ac);
			title_Panel_ViewSpendings.add(search_btn_VS);
			
			
			
			cat_cbox_VS = new JComboBox();
			cat_cbox_VS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cat_cbox_VS.setFont(new Font("Arial Black", Font.BOLD, 16));
			cat_cbox_VS.setBounds(875, 91, 270, 29);
			cat_cbox_VS.addActionListener(ac);
			title_Panel_ViewSpendings.add(cat_cbox_VS);
			
			
			from_date_VS = new JDateChooser();
			from_date_VS.setBounds(141, 91, 188, 29);
			from_date_VS.setSelectableDateRange(null, new java.util.Date());
			title_Panel_ViewSpendings.add(from_date_VS);
			
			to_date_VS = new JDateChooser();
			to_date_VS.setBounds(479, 91, 188, 29);
			to_date_VS.setSelectableDateRange(null, new java.util.Date());
			title_Panel_ViewSpendings.add(to_date_VS);
			
			
			JScrollPane scrollPane_VS = new JScrollPane();
			scrollPane_VS.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scrollPane_VS.setBounds(0, 189, 1362, 440);
			ViewSpendings_Panel.add(scrollPane_VS);
			
			
			
			//Tabels
			
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			
			
			
			table_VS = new JTable();
			table_VS.setShowVerticalLines(false);
			table_VS.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"Sr No.", "Category", "Date", "Money"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			
			table_VS.setFont(new Font("Arial", Font.PLAIN, 14));
			table_VS.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			table_VS.setAlignmentX(CENTER_ALIGNMENT);
			
			table_VS.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			table_VS.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			table_VS.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
			table_VS.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			
			scrollPane_VS.setViewportView(table_VS);
			
			
			
			
			
			JScrollPane all_Spending = new JScrollPane();
			
			
			
			tabbedPane.addTab("View all Spendings", null, all_Spending, null);
			
			
			
			view_table = new JTable();
			view_table.setFont(new Font("Arial", Font.PLAIN, 14));
			view_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			view_table.setAlignmentX(CENTER_ALIGNMENT);
			view_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Date", "Category", "Money"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			
			
			view_table.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			view_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			view_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			view_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
			view_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
			all_Spending.setViewportView(view_table);
			
			
			
	}}
	
	
	
	
	public void addCatFrame() {          // Things must happen while opening AddCatagory Window
		this.setVisible(false);
		ncf.setVisible(true);
	}
	
	public void getEntries() {            // To get Entries from database to table{
		int total = 0;
		
		
		try {
			String query = "select * from spending where date >= '"+ dc + "'";
			ResultSet rs = db.s.executeQuery(query);
			
			javax.swing.table.DefaultTableModel dtm = (DefaultTableModel) table_E.getModel();  //Get the model of table
			
			// Empty the table
			
			int rc = dtm.getRowCount();
			while(rc-- !=0) {
				dtm.removeRow(0);
			}
			
			// Add data from database
			int i=0;
			while(rs.next()) {
				int id =i++;
				String category=  rs.getString(2);
				String date = rs.getString(3);
				String money = rs.getString(4);
				int m = Integer.parseInt(money);
				total = total+m;
			Object	o[] = {id,category,date,m};
			dtm.addRow(o);
				}}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
		Total_E.setText(total+" RS");
		
	}
	
	
	
	public boolean inputCheck(Date d,String money) {
		try {                                          // try and catch to get input with no null values
			
			
			Integer.parseInt(money);
			if(d==null || money=="") {
				throw new NullPointerException(); }
			
			else{
				return true;
			}
			
				}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Please give appropriate money figure");
			return false;
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Please give appropriate values");
			return false;
		}
		
		
	}
	
	public void addNewExpense() {
		Date d = date_E.getDate();
		String money="";
		money = money_txtfld_E.getText();
		money_txtfld_E.setText("");
		String cat = (String)cat_cbox_E.getSelectedItem();
		
		// Create connection and add expense to the Data base
		// if all input are correct
		
		if(inputCheck(d,money)) {
		try {
			int m = Integer.parseInt(money);
			java.sql.Date date = new java.sql.Date(d.getTime());
			String query = "insert into spending(category,date,money) values('" + cat +"','"+date+"',"+ m + ")";
		db.s.executeUpdate(query);
		
		JOptionPane.showMessageDialog(this, "Spending has been Added");
		
		getEntries();
		
		
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, ex);
		}}		
		
		
	}

	
	/*
	 * First Empty the table than add values in table from database
	 */
	
	
	
	public void deleteExpense() {
		int o =JOptionPane.showConfirmDialog(this, "Do you want to delete this Data", "Deletion Confermation", JOptionPane.YES_NO_OPTION);
		if(o == JOptionPane.OK_OPTION) {
			
		String db_table_name = "spending";
		int ri = table_E.getSelectedRow();
		String a = (String) table_E.getValueAt(ri, 1);                      //Get Sr. No. values
		java.util.Date d = (Date) table_E.getValueAt(ri, 2);
		java.sql.Date date = new java.sql.Date(d.getTime());
		int money = (int) table_E.getValueAt(ri, 3);
		try {
		String q = "delete from "+db_table_name+" where category = '" + a +"' && date = '"+ date+"' && money = "+money+"" ;
		System.out.println(q);
		db.s.executeUpdate(q);
		JOptionPane.showMessageDialog(this, "Deletion is Successfull" );
		getEntries();
		}catch(Exception ex) {
			JOptionPane.showConfirmDialog(this, ex);
		}}
		
	}

	 public void displayCategory(JComboBox cbox) {
     	cbox.removeAllItems();
     	try {
     		    int rows = f.getcat_rows();
     		    if(rows != -1) {
     		    	for(int i=0; i <= rows; i++) {
     		    		String category = f.getCategory_E(i);
     			        cbox.addItem(category);
     		
     		    }}
     	}catch(Exception ex) {
     		JOptionPane.showMessageDialog(this, ex);
     	}
     }

	
	
	@Override
	public void windowClosing(WindowEvent we) {
		try {
			JFrame f = (JFrame) we.getSource();
			String a=f.getTitle();
			if(a == "Add Category") {
			  this.setVisible(true);
			  displayCategory(cat_cbox_E);
			}
			else if(a =="Money Tracker"){
				db.c.close();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	
	
	
		@Override
		public void windowOpened(WindowEvent we) {
			// TODO Auto-generated method stub
			
		}

		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		class Action implements ActionListener{
			
			
			Action(){
				
				
			}
			
			public void actionPerformed(ActionEvent e) {				
				String a = e.getActionCommand();
		        boolean useJDBC = f.getJDBCStatus();
				
		        if(useJDBC) {
				switch(a) {
				case("Add New Category"): addCatFrame();    break;
				case("ADD"):  addNewExpense(); break;
				case("Delete"):  deleteExpense(); break;
				case("Search"):  search();  break;
				}}
		        
		        switch(a) {
		        case("Add New Category"):  addCatFrame(); break;
		        case("ADD"):   addExpenses_Excel();getEntries_Excel(); break;
		        case("Delete"): deleteExpenses_Excel();getEntries_Excel(); break;
		        case("Search"):  search_Excel();  break;
		        } 
				
				
			}}

	
		
		public void deleteExpenses_Excel() {
			int o =JOptionPane.showConfirmDialog(this, "Do you want to delete this Data", "Deletion Confermation", JOptionPane.YES_NO_OPTION);
			if(o == JOptionPane.OK_OPTION) {
				
			int ri = table_E.getSelectedRow();
			String cat = (String) table_E.getValueAt(ri, 1);                      //Get Sr. No. values
			String date =  (String) table_E.getValueAt(ri, 2);
			int m =  (int) table_E.getValueAt(ri, 3);
			
			
			try {
			f.removeExpense(cat, date, m);
			JOptionPane.showMessageDialog(this, "Deletion is Successfull" );
			}catch(Exception ex) {
			
			}}
			
		}
	
		
		
		public void getEntries_Excel() {
			int total = 0;
			int row = f.getEx_rows();
			
			if(row != -1) {
			
			try {
				javax.swing.table.DefaultTableModel dtm = (DefaultTableModel) table_E.getModel();  //Get the model of table
				
				// Empty the table
				
				int rc = dtm.getRowCount();
				while(rc-- !=0) {
					dtm.removeRow(0);
				}
				
				// Add data from database
				for(int i=0;i<=row;i++) {
					int id =i+1;
					String category=  f.getCategory_E(i);
					String date = f.getdate_E(i);
					int m = f.getmoney_E(i);
					total = total+m;
				Object	o[] = {id,category,date,m};
				dtm.addRow(o);
					}}catch(NullPointerException ex) {
			}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
			 
			}
			
			
			Total_E.setText(total+" RS");
			
		}
		
		public void addExpenses_Excel() {
			Date d = date_E.getDate();
			String money="";
			money = money_txtfld_E.getText();
			money_txtfld_E.setText("");

			String cat = (String)cat_cbox_E.getSelectedItem();
			
			// Create connection and add expense to the Data base
			// if all input are correct
			
			if(inputCheck(d,money)) {
				int m = Integer.parseInt(money);
				f.addExpenses(cat, d, m);
				
			
		}}
       
      		public void search() {
			Date from_date = from_date_VS.getDate();
			
			Date to_date = to_date_VS.getDate();
			
			
			
			String cat =(String) cat_cbox_VS.getSelectedItem();
			
              javax.swing.table.DefaultTableModel dtm = (DefaultTableModel) table_VS.getModel();  //Get the model of table
			
			// Empty the table
			
			int rc = dtm.getRowCount();
			while(rc-- !=0) {
				dtm.removeRow(0);
			}
			
			/*
			 * Condition for Searching
			 */
			
			if(from_date == null && to_date == null && cat != null) {
				int total =0;
				try {
					
					ResultSet rs = db.s.executeQuery("select * from spending where category ='"+ cat +"'");
		     		while(rs.next()) {
		     			
		     			int id =Integer.parseInt(rs.getString(1));
						String date = rs.getString(3);
						String money = rs.getString(4);
						int m = Integer.parseInt(money);
						total = total+m;
					Object	o[] = {id,cat,date,m};
					dtm.addRow(o);
					
					}
		     		}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex);
				}
				total_VS.setText(total+"");
			}
			
			else if(from_date != null && to_date != null && cat == null) {
				int total =0;
				java.sql.Date from_d = new java.sql.Date(from_date.getTime());
				java.sql.Date to_d = new java.sql.Date(to_date.getTime());
				
				try {
					
					ResultSet rs = db.s.executeQuery("select * from spending where date >= '"+ from_d +"' && date <= '"+ to_d +"'");
		     		while(rs.next()) {
		     			
		     			int id =Integer.parseInt(rs.getString(1));
						String date = rs.getString(3);
						String money = rs.getString(4);
						String category = rs.getString(2);
						int m = Integer.parseInt(money);
						total = total+m;
					Object	o[] = {id,category,date,m};
					dtm.addRow(o);
					
					}
		     		}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex);
				}
				total_VS.setText(total+"");}
			
			else if(from_date != null && to_date == null && cat == null) {
				int total =0;
				java.sql.Date from_d = new java.sql.Date(from_date.getTime());
				//java.sql.Date to_d = new java.sql.Date(to_date.getTime());
				
				try {
					
					ResultSet rs = db.s.executeQuery("select * from spending where date >= '"+ from_d +"'");
		     		while(rs.next()) {
		     			
		     			int id =Integer.parseInt(rs.getString(1));
						String date = rs.getString(3);
						String money = rs.getString(4);
						String category = rs.getString(2);
						int m = Integer.parseInt(money);
						total = total+m;
					Object	o[] = {id,category,date,m};
					dtm.addRow(o);
					
					}
		     		}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex);
				}
				total_VS.setText(total+"");}
			
			else if(from_date != null && to_date != null && cat != null) {
				int total =0;
				java.sql.Date from_d = new java.sql.Date(from_date.getTime());
				java.sql.Date to_d = new java.sql.Date(to_date.getTime());
				
				try {
					
					ResultSet rs = db.s.executeQuery("select * from spending where date >= '"+ from_d +"' && date <= '"+ to_d +"' && category = '"+cat+"'");
		     		while(rs.next()) {
		     			
		     			int id =Integer.parseInt(rs.getString(1));
						String date = rs.getString(3);
						String money = rs.getString(4);
						int m = Integer.parseInt(money);
						total = total+m;
					Object	o[] = {id,cat,date,m};
					dtm.addRow(o);
					
					}
		     		}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex);
				}
				total_VS.setText(total+"");}
			
			else if(from_date != null && to_date == null && cat != null) {
				int total =0;
				java.sql.Date from_d = new java.sql.Date(from_date.getTime());
				//java.sql.Date to_d = new java.sql.Date(to_date.getTime());
				
				try {
					
					ResultSet rs = db.s.executeQuery("select * from spending where date >= '"+ from_d +"' && category = '"+cat+"'");
		     		while(rs.next()) {
		     			
		     			int id =Integer.parseInt(rs.getString(1));
						String date = rs.getString(3);
						String money = rs.getString(4);
						int m = Integer.parseInt(money);
						total = total+m;
					Object	o[] = {id,cat,date,m};
					dtm.addRow(o);
					
					}
		     		}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex);
				}
				total_VS.setText(total+"");}
			
			else {
				JOptionPane.showMessageDialog(this,"Please fill the details properly");
			}
			
             
        
    	
    }	

      		
      		public void search_Excel() {
    			Date from_date = from_date_VS.getDate();
    			
    			Date to_date = to_date_VS.getDate();
    			int rows = f.getEx_rows();
    			
    			
    			
    			String cat =(String) cat_cbox_VS.getSelectedItem();
    			
                  javax.swing.table.DefaultTableModel dtm = (DefaultTableModel) table_VS.getModel();  //Get the model of table
    			
    			// Empty the table
    			
    			int rc = dtm.getRowCount();
    			while(rc-- !=0) {
    				dtm.removeRow(0);
    			}
    			
    			/*
    			 * Condition for Searching
    			 */
    			
    			if(from_date == null && to_date == null && cat != null) {
    				int total =0;
    				
    				try {
    					
    					if(rows!= -1) {
    		    			for(int i=0;i<=rows;i++) {
    		    				int id =i+1;
    							String category=  f.getCategory_E(i);
    							if(category == cat) {
    							String date = f.getdate_E(i);
    							int m = f.getmoney_E(i);
    						Object	o[] = {id,category,date,m};
    						dtm.addRow(o);
    							}
    					}
    		     		}}catch(Exception ex) {
    					JOptionPane.showMessageDialog(this, ex);
    				}
    				total_VS.setText(total+"");
    			}
    			
    			else if(from_date != null && to_date != null && cat == null) {
    				int total =0;
    				
    				
    				try {
    					
    					if(rows!= -1) {
    		    			for(int i=0;i<=rows;i++) {
    		    				int id =i+1;
    		    				Date d = f.getDate_E(i);
    							if(to_date.compareTo(d) > 0 && from_date.compareTo(d) < 0 ) {
    							String category=  f.getCategory_E(i);
    							String date = f.getdate_E(i);
    							int m = f.getmoney_E(i);
    						Object	o[] = {id,category,date,m};
    						dtm.addRow(o);
    							}
    					}
    		     		}
    		     		}catch(Exception ex) {
    					JOptionPane.showMessageDialog(this, ex);
    				}
    				total_VS.setText(total+"");}
    			
    			else if(from_date != null && to_date == null && cat == null) {
    				int total =0;
    				
    				try {
    					
    					if(rows!= -1) {
    		    			for(int i=0;i<=rows;i++) {
    		    				int id =i+1;
    		    				Date d = f.getDate_E(i);
    							if(from_date.compareTo(d) < 0 ) {
    							String category=  f.getCategory_E(i);
    							String date = f.getdate_E(i);
    							int m = f.getmoney_E(i);
    						Object	o[] = {id,category,date,m};
    						dtm.addRow(o);
    							}
    					}
    		     		}
    		     		}catch(Exception ex) {
    					JOptionPane.showMessageDialog(this, ex);
    				}
    				total_VS.setText(total+"");}
    			
    			else if(from_date != null && to_date != null && cat != null) {
    				int total =0;
    				
    				try {
    					
    					if(rows!= -1) {
    		    			for(int i=0;i<=rows;i++) {
    		    				int id =i+1;
    		    				Date d = f.getDate_E(i);
    		    				String category=  f.getCategory_E(i);
    							if(from_date.compareTo(d) < 0 && to_date.compareTo(d) > 0  && cat == category) {
    							String date = f.getdate_E(i);
    							int m = f.getmoney_E(i);
    						Object	o[] = {id,category,date,m};
    						dtm.addRow(o);
    							}
    					}
    		     		}
    		     		}catch(Exception ex) {
    					JOptionPane.showMessageDialog(this, ex);
    				}
    				total_VS.setText(total+"");}
    			
    			else if(from_date != null && to_date == null && cat != null) {
    				int total =0;
    				java.sql.Date from_d = new java.sql.Date(from_date.getTime());
    				//java.sql.Date to_d = new java.sql.Date(to_date.getTime());
    				
    				try {
    					
    					if(rows!= -1) {
    		    			for(int i=0;i<=rows;i++) {
    		    				int id =i+1;
    		    				Date d = f.getDate_E(i);
    		    				String category=  f.getCategory_E(i);
    							if(from_date.compareTo(d) < 0  && cat == category) {
    							String date = f.getdate_E(i);
    							int m = f.getmoney_E(i);
    						Object	o[] = {id,category,date,m};
    						dtm.addRow(o);
    							}
    					}
    		     		}
    		     		}catch(Exception ex) {
    					JOptionPane.showMessageDialog(this, ex);
    				}
    				total_VS.setText(total+"");}
    			
    			else {
    				JOptionPane.showMessageDialog(this,"Please fill the details properly");
    			}
    			
                 
            
        	
        }	

        public void viewAllSpendings() {
        	
        	if(f.getJDBCStatus()) {
        	try {
    			String query = "select * from spending where date >= '"+ dc + "'";
    			ResultSet rs = db.s.executeQuery(query);
    			
    			javax.swing.table.DefaultTableModel dtm = (DefaultTableModel) view_table.getModel();  //Get the model of table
    			
    			// Empty the table
    			
    			int rc = dtm.getRowCount();
    			while(rc-- !=0) {
    				dtm.removeRow(0);
    			}
    			
    			// Add data from database
    			while(rs.next()) {
    				int id =Integer.parseInt(rs.getString(1));
    				String category=  rs.getString(2);
    				String date = rs.getString(3);
    				String money = rs.getString(4);
    				int m = Integer.parseInt(money);
    			Object	o[] = {date,category,m};
    			dtm.addRow(o);
    				}}catch(Exception ex) {
    			JOptionPane.showMessageDialog(null, ex);
    		}}
        	
        	else {
        		
        		int rows =f.getEx_rows();
                javax.swing.table.DefaultTableModel dtm = (DefaultTableModel) view_table.getModel();  //Get the model of table
    			
    			// Empty the table
    			
    			int rc = dtm.getRowCount();
    			while(rc-- !=0) {
    				dtm.removeRow(0);
    			}
        		
    			// Add data from database
    			if(rows!= -1) {
    			for(int i=0;i<=rows;i++) {
    				int id =i+1;
					String category=  f.getCategory_E(i);
					String date = f.getdate_E(i);
					int m = f.getmoney_E(i);
				Object	o[] = {id,category,date,m};
				dtm.addRow(o);
    			
    			
    			
        	}}
        	
        }}}