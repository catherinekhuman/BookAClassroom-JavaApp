import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser; // Importing JDateChooser for date input
import java.util.*; // Importing utilities for random number generation

public class BookClassroom extends JFrame implements ActionListener {
	
	JTextField tfempid;
    JLabel tfname, tfdepartment, labelcode;
    JButton bookclassroom, fetchButton, croom;
    Choice cr_timing;
    JDateChooser dcdate;
    
    public BookClassroom() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Book a Classroom");
        heading.setBounds(200, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblempid = new JLabel("Employee ID");
        lblempid.setBounds(60, 80, 150, 25);
        lblempid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblempid);
        
        tfempid = new JTextField();
        tfempid.setBounds(220, 80, 150, 25);
        add(tfempid);
        
        // Button to fetch faculty details based on employee ID
        fetchButton = new JButton("Fetch Faculty");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        JLabel lbldepartment = new JLabel("Department");
        lbldepartment.setBounds(60, 180, 150, 25);
        lbldepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldepartment);
        
        tfdepartment = new JLabel();
        tfdepartment.setBounds(220, 180, 150, 25);
        add(tfdepartment);
        
        JLabel lblcr_timing = new JLabel("Time Slot");
        lblcr_timing.setBounds(60, 230, 150, 25);
        lblcr_timing.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcr_timing);
        
        cr_timing = new Choice(); // Initialize dropdown for time slot selection
        cr_timing.setBounds(220, 230, 150, 25);       
        add(cr_timing);
        
        // Add the time slot dropdown with values from the database
        try {
            Conn c = new Conn();
            String query = "select * from croom";
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()) {
            	cr_timing.add(rs.getString("cr_timing")); // Add available timings to the dropdown
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Button to check the availability of classrooms for the selected time slot
        croom = new JButton("Check Availability");
        croom.setBackground(Color.BLACK);
        croom.setForeground(Color.WHITE);
        croom.setBounds(380, 280, 120, 25);
        croom.addActionListener(this);
        add(croom);
        
        // Label and field to display the available classroom code
        JLabel lblcode = new JLabel("Classroom");
        lblcode.setBounds(60, 330, 150, 25);
        lblcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcode);
        
        labelcode = new JLabel();
        labelcode.setBounds(220, 330, 150, 25);
        add(labelcode);
        
        // Label and date picker to select the booking date
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 430, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(220, 430, 150, 25);
        add(dcdate);
        
        
        bookclassroom = new JButton("Book Classroom");
        bookclassroom.setBackground(Color.BLACK);
        bookclassroom.setForeground(Color.WHITE);
        bookclassroom.setBounds(220, 480, 150, 25);
        bookclassroom.addActionListener(this);
        add(bookclassroom);
        
        setSize(600, 600);
        setLocation(200, 50);
        setVisible(true);
    }
    
    // Action performed when a button is clicked
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String empid = tfempid.getText();
            
            try {
                Conn conn = new Conn();

                String query = "select * from faculty where empid = '"+empid+"'";

                ResultSet rs = conn.s.executeQuery(query);
                
                if (rs.next()) {
                    tfname.setText(rs.getString("name")); 
                    tfdepartment.setText(rs.getString("department")); 
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct Employment ID");                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == croom) {
            String timing = cr_timing.getSelectedItem();            
            try {
                Conn conn = new Conn();

                String query = "select * from croom where cr_timing = '"+timing+"'";

                ResultSet rs = conn.s.executeQuery(query);
                
                if (rs.next()) {
                    labelcode.setText(rs.getString("cr_code")); 
                } else {
                    JOptionPane.showMessageDialog(null, "No Available Classrooms");                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Random random = new Random(); // Generate a random number for Unique ID
            
            String empid = tfempid.getText();
            String name = tfname.getText(); 
            String department = tfdepartment.getText();
            String crcode = labelcode.getText();
            String timing = cr_timing.getSelectedItem(); 
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            
            try {
                Conn conn = new Conn();

                String query = "insert into booking values('UniqueID-"+random.nextInt(1000)+"', '"+empid+"', '"+name+"', '"+department+"', '"+timing+"', '"+crcode+"', '"+ddate+"')";

                conn.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Classroom Booked Successfully");

                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


	public static void main(String[] args) {
		new BookClassroom();

	}

}
