import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame implements ActionListener{
	
	public Home() {
		setLayout(null);
		
        // Load an image as the background
		ImageIcon i1 = new ImageIcon(getClass().getResource("/Images/classroom.jpg")); // Load the image from the resource folder
		Image img = i1.getImage(); // Convert the icon to an image object
		
        // Scale the image to match the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen dimensions
        Image scaledImg = img.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH); // Scale the image
		
        // Create a scaled icon and set it as a JLabel
        ImageIcon scaledIcon = new ImageIcon(scaledImg); // Convert the scaled image back to an icon
		JLabel image = new JLabel(scaledIcon); // Add the icon to a JLabel
		image.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()); // Set bounds to cover the screen
		add(image); // Add the JLabel to the frame
		
        // Add a heading label on the background image
		JLabel heading = new JLabel("BOOK A CLASSROOM"); // Set the text
		heading.setBounds(500, 120, 400, 40);
		heading.setForeground(Color.WHITE);
		heading.setFont(new Font("Tahoma", Font.BOLD, 36));
		image.add(heading); // Add the label to the background image
		
        // Create a menu bar
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar); // Attach the menu bar to the frame
		
        // Create a "Details" menu
		JMenu details = new JMenu("Details"); // Create the menu
		menubar.add(details); // Add it to the menu bar
		
        // Add "Add Faculty" menu item under "Details" menu
		JMenuItem addFaculty = new JMenuItem("Add Faculty"); // Create the menu item
		addFaculty.addActionListener(this); // Add an action listener
		details.add(addFaculty); // Add the menu item to the "Details" menu
		
		// Add "Classroom Info" menu item under "Details" menu
		JMenuItem clasroomInfo = new JMenuItem("Classroom Info");
		clasroomInfo.addActionListener(this); 
		details.add(clasroomInfo);
		
        // Add "Book a Classroom" menu item under "Details" menu
		JMenuItem bookClassroom = new JMenuItem("Book a Classroom");
		bookClassroom.addActionListener(this);
		details.add(bookClassroom);
		
        // Create a "Your Bookings" menu
		JMenu yourBookings = new JMenu("Your Bookings");
		menubar.add(yourBookings);
		
        // Add "Booking Info" menu item under "Your Bookings" menu
		JMenuItem bookingInfo = new JMenuItem("Booking Info");
		bookingInfo.addActionListener(this);
		yourBookings.add(bookingInfo);
		
        // Set the frame to full-screen mode
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame to occupy the whole screen
		setVisible(true);
	}

    // Method to handle menu item actions
	public void actionPerformed(ActionEvent ae) { // Get the name of the menu item that triggered the event
		String text = ae.getActionCommand();
		
        // Open the appropriate screen based on the menu item selected
		if (text.equals("Add Faculty")) {
			new AddFacultyInfo();
		} else if (text.equals("Booking Info")) {
			new BookingInfo();
		} else if (text.equals("Book a Classroom")) {
			new BookClassroom();
		} else if (text.equals("Classroom Info")) {
			new ClassRoomInfo();
		}
	}
	
	public static void main(String[] args) {
		new Home();
	
	}
}