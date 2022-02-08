/* This Class is for Catogry add/Remove Frame
   
   ttl_pnl_cf -> title Panel Category Frame
   ttl_lbl   ->  title Label
   txtfld_cf  ->  Catagory text field to add new catagory
   ok_btn_cf  ->  ok btn to Exit by saving data
   cncl_btn_cf  ->  Cancel button to Exit without saving
   del_btn_cf   -> delete button to delete a catagory
   table_cf   - > tabel to store data of category
   
*/
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import java.awt.ComponentOrientation;
import java.awt.Component;

public class NewCategoryFrame extends JFrame {

	
	JDBC db = new JDBC();


	private JPanel contentPane;
	 JTextField txtfld_cf;
	 JTable table_cf;
	 JButton add_btn_cf,del_btn_cf;
	 
	 Action ac = new Action();
	 FileHandle f = new FileHandle();
	 
	

	/**
	 * Launch the application.
	 */
	
	 

	/**
	 * Create the frame.
	 */
	public NewCategoryFrame() {
		GUI();
		if(f.getJDBCStatus()) {
		getEntries(db.s);
		}
		
	}
	
	

	public void GUI(){
		setTitle("Add Category");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TARUN\\eclipse-workspace\\Money_Tracker\\Files\\Money_Track logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel ttl_pnl_cf = new JPanel();
		ttl_pnl_cf.setBounds(0, 0, 450, 67);
		ttl_pnl_cf.setBackground(new Color(0, 0, 255));
		contentPane.add(ttl_pnl_cf);
		ttl_pnl_cf.setLayout(null);
		
		JLabel ttl_lbl = new JLabel("Add Your Category");
		ttl_lbl.setForeground(new Color(255, 255, 255));
		ttl_lbl.setFont(new Font("Arial Black", Font.BOLD, 20));
		ttl_lbl.setHorizontalTextPosition(SwingConstants.CENTER);
		ttl_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		ttl_lbl.setBounds(87, 11, 245, 45);
		ttl_pnl_cf.add(ttl_lbl);
		
		txtfld_cf = new JTextField();
		txtfld_cf.setBounds(31, 95, 378, 50);
		txtfld_cf.setFont(new Font("Arial Black", Font.BOLD, 16));
		contentPane.add(txtfld_cf);
		txtfld_cf.setColumns(10);
		txtfld_cf.addActionListener(ac);
		
		add_btn_cf = new JButton("ADD");
		add_btn_cf.setBounds(57, 169, 104, 38);
		add_btn_cf.setBackground(new Color(0, 128, 0));
		add_btn_cf.setFont(new Font("Arial Black", Font.BOLD, 14));
		add_btn_cf.addActionListener(ac);
		contentPane.add(add_btn_cf);
		
		
		del_btn_cf = new JButton("DELETE");
		del_btn_cf.setBounds(245, 169, 104, 38);
		del_btn_cf.setFont(new Font("Arial Black", Font.BOLD, 14));
		del_btn_cf.setBackground(Color.RED);
		del_btn_cf.addActionListener(ac);
		contentPane.add(del_btn_cf);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 256, 445, 162);
		scrollPane.setFont(new Font("Arial Black", Font.BOLD, 14));
		contentPane.add(scrollPane);
		
		table_cf = new JTable();
		table_cf.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table_cf.setGridColor(Color.BLACK);
		table_cf.setFillsViewportHeight(true);
		table_cf.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table_cf.setSelectionBackground(new Color(0, 120, 215));
		table_cf.setBackground(Color.WHITE);
		table_cf.setFont(new Font("Arial", Font.PLAIN, 14));
		table_cf.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr.", "Category"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_cf.getColumnModel().getColumn(0).setResizable(false);
		table_cf.getColumnModel().getColumn(0).setPreferredWidth(35);
		table_cf.getColumnModel().getColumn(0).setMaxWidth(35);
		table_cf.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table_cf);
		
		
	}

	
	//Func to add category in Data tables
	
public void addCategory(String txt,Statement s) {
	String category =txt;
	try {
	
	s.executeUpdate("insert into category_info values('"+ category + "')");
	JOptionPane.showMessageDialog(this, "Category Added Successfully");
	}
	catch(SQLIntegrityConstraintViolationException ex) {
		JOptionPane.showMessageDialog(this,"Category Already Exist");
	}
	
	catch(NullPointerException e) {
	}catch(Exception ex) {
		JOptionPane.showMessageDialog(this, ex);
	}
	}




/*
 * Func to get entries from the db_table to the app_table
 */


public void getEntries(Statement s) {
	int sr = 0;
	try {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table_cf.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table_cf.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		ResultSet rs = s.executeQuery("select * from category_info");
		javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table_cf.getModel();
		int rc=dtm.getRowCount();
		while(rc-- != 0) {                //rc is Row Count
			dtm.removeRow(0);
		}
		while(rs.next()) {
			String category=rs.getString("Category");
			if(category != null) {
			
			Object o[] = {++sr,category};
			dtm.addRow(o);
		}}
		
	}
	catch(Exception ex){
		JOptionPane.showMessageDialog(this, ex);	}
}








class Action implements ActionListener{
	Action(){
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	   
	   String a = e.getActionCommand();
	   int ri = table_cf.getSelectedRow();
	   String db_table_name = "category_info";
	   String b = txtfld_cf.getText();
	   boolean useJDBC = f.getJDBCStatus();
	  
	   /*
	    * For database
	    */
	   if(useJDBC) {
	   switch(a) {
	   
	   case("DELETE"):  deleteRow(ri,1,db.s,table_cf,db_table_name); getEntries(db.s);  break;
	   case("ADD"):  addCategory(b,db.s);getEntries(db.s);txtfld_cf.setText(""); break;
	   default:  addCategory(a,db.s);getEntries(db.s);txtfld_cf.setText(""); break;
	   
	   }}
	   
	   /*
	    * for Excel file
	    */
	   
	   switch(a) {
	   case("DELETE"): deleteCategory(ri);   getEntries_Excel(); break;
	   case("ADD"): f.addCategory(b); getEntries_Excel(); txtfld_cf.setText("");  break;
	   default:  f.addCategory(b); getEntries_Excel(); txtfld_cf.setText(""); break;
	   
	   
	   }
		

	}
}

/*To delete a row from the table on database
 * 
 * ri means row selected in the table in computer
 * "column" is column which act as key to delete row in database table
	* "table" is table in the application
	*  db_table_name is table in the database
	*/

public void getEntries_Excel() {
	int sr = 0;
	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table_cf.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table_cf.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table_cf.getModel();
		int rc=dtm.getRowCount();
		while(rc-- != 0) {                //rc is Row Count
			dtm.removeRow(0);
		}
		
		
		int rows = f.getcat_rows();
		for(int i=0;i<=rows;i++) {
			String category=f.getCategory(i);
			if(category != null) {
			Object o[] = {++sr,category};
			dtm.addRow(o);
         }}
	
}


public void deleteCategory(int ri) {
	
	String selectedValue=(String)table_cf.getValueAt(ri,1);
	f.removeCategory(selectedValue);
}

public void deleteRow(int ri,int column,Statement s,JTable app_table,String db_table_name){
	
	int a =JOptionPane.showConfirmDialog(this, "Do you want to delete this Data", "Deletion Confermation", JOptionPane.YES_NO_OPTION);
	if(a == JOptionPane.OK_OPTION) {
	
	String selectedValue=(String)app_table.getValueAt(ri, column);
	try {
		s.executeUpdate("delete from "+db_table_name+" where category = '"+selectedValue+"'");
		JOptionPane.showMessageDialog(this, ""+selectedValue+" is deleted" );
	}
	catch(NullPointerException ex) {
		
	}catch(Exception e) {
		JOptionPane.showMessageDialog(this, e);
	}
}}

}


