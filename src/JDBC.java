import java.sql.*;
import javax.swing.JTable;
import javax.swing.JOptionPane;

public class JDBC {
	Connection c = null;
	Statement s;
	boolean useJDBC;
	FileHandle f =new FileHandle();
	JDBC(){
		
		
		String user =  f.getusername();                                             // "root"
		String password = f.getpassword();                                                         // "21101997"
		String url = f.geturl();   //"jdbc:mysql://127.0.0.1:3306/Moneytracker_db"
                                                  // "jdbc:mysql://Host_name:Host_Port/Database_Schema_Name"+"?useSSL=false"      
		
		if(user != null || password != null || url != null) {
			
		url = url+"?useSSL=false";
		
		try {
			
			//load the driver
			//Class.forName("com.mysql.jdbc.Driver");
			
			//create a connection
			c = DriverManager.getConnection(url, user, password);
			s = c.createStatement();
			useJDBC = true;
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Please Check your username, password, url or rest them in Options");
			useJDBC = false;
		}
		
	}
		else {
			useJDBC = false;
		}
		
		f.addJDBCStatus(useJDBC);
		
		
	}
	
}
