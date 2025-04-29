import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

//Class to display booking information based on a unique ID
public class BookingInfo extends JFrame implements ActionListener{
    JTable table;  // JTable to display booking data
    JTextField uniqueid; // Text field to input the Unique ID
    JButton show; // Button to trigger fetching and displaying the booking details
    
    public BookingInfo() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Label to prompt the user for entering Unique ID
        JLabel lbluniqueid = new JLabel("Unique ID");
        lbluniqueid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbluniqueid.setBounds(50, 50, 100, 25);
        add(lbluniqueid);
        
        // Text field for entering the Unique ID
        uniqueid = new JTextField();
        uniqueid.setBounds(160, 50, 120, 25);
        add(uniqueid);
        
        // Button to trigger fetching booking details based on Unique ID
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);
        
        // Create an empty JTable to display results
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.WHITE);
        add(jsp);
        
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from booking where UniqueID = '"+uniqueid.getText()+"'");
            
            // Check if the ResultSet is empty (i.e., no records found)
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            // If records are found, update the JTable model to display the result set
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BookingInfo();
    }
}