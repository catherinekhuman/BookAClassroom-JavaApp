import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // Importing DbUtils to convert ResultSet to TableModel

//Class to display classroom information in a GUI window
public class ClassRoomInfo extends JFrame {

	public ClassRoomInfo (){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
        // Create a JTable to display data
		JTable table = new JTable();
		
		try {
			Conn conn = new Conn ();
			
            // Execute a SQL query to fetch all records from the 'croom' table
			ResultSet rs = conn.s.executeQuery("select * from croom");
			
            // Use DbUtils to populate the JTable with the ResultSet data
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
        // Create a JScrollPane to add scroll functionality for the table
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, 800, 500);
		add(jsp);
		
		setSize(800, 500);
		setLocation(400, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClassRoomInfo();
	}

}
