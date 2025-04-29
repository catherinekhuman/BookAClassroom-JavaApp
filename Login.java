import javax.swing.*; // For GUI components
import java.awt.*; // For layout and color
import java.awt.event.*; // For handling events
import java.sql.*; // For database connectivity

//Main class for the Login functionality, extending JFrame and implementing ActionListener for event handling
public class Login extends JFrame implements ActionListener{
	JButton submit, reset, close; // Buttons for user actions: submit, reset, and close
	JTextField tfusername; // Text field to input the username(=admin)
	JPasswordField tfpassword;  // Password field to input the password (concealed characters)(=admin)
	
	// Constructor to initialize the login GUI
	public Login() {
		// Set background color for the frame
		getContentPane().setBackground(Color.WHITE);
		setLayout(null); // Use no layout manager for manual positioning of components
		
		// Label for "Username" field
		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(20, 25, 100, 20); // Set position and size
		add(lblusername); // Add label to the frame
		
		 // Text field for username input
		tfusername = new JTextField();
		tfusername.setBounds(130, 25, 200, 20);
		add(tfusername);
		
		// Label for "Password" field
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(20, 65, 100, 20);
		add(lblpassword);
		
		// Password field for password input
		tfpassword = new JPasswordField();
		tfpassword.setBounds(130, 65, 200, 20);
		add(tfpassword);
		
		// Reset button to clear input fields
		reset = new JButton("Reset");
		reset.setBounds(40, 120, 120, 20);
		reset.addActionListener(this); // Add action listener to handle button click
		add(reset); // Add reset button to the frame
		
		// Submit button to validate credentials
		submit = new JButton("Submit");
		submit.setBounds(230, 120, 120, 20);
		submit.addActionListener(this);
		add(submit);
		
		// Close button to exit the login window
		close = new JButton("Close");
		close.setBounds(130, 160, 120, 20);
		close.addActionListener(this);
		add(close);
		
		 // Set frame properties
		setSize(400, 250); // Set size of the frame
		setLocation(450, 250); // Set position on the screen
		setVisible(true); // Make the frame visible
	}

    // Method to handle button click events
	public void actionPerformed(ActionEvent ae) {
        // Action for the "Submit" button
		if (ae.getSource() == submit) {
			String username = tfusername.getText(); // Get the username input
			String password = tfpassword.getText(); // Get the password input
			
			try {
				// Establish database connection
				Conn c = new Conn();
				
                // Query to check for matching credentials in the "login" table
				String query = "select * from login where username = '" + username + "' and password = '" + password + "'";
				
                // Execute the query
				ResultSet rs = c.s.executeQuery(query);
				
                // If credentials match, proceed to the Home screen
				if (rs.next()) {
					new Home(); // Open the Home screen
					setVisible(false); // Close the login window
				} else {
                    // If credentials are invalid, show an error message
					JOptionPane.showMessageDialog(null, "Invalid Username or Password");
					setVisible(false);
				}
			} catch (Exception e) {
				e.printStackTrace(); // Print any exception to the console
			}
			
	    // Action for the "Close" button	
		} else if (ae.getSource() == close) {
			setVisible(false);
	    // Action for the "Reset" button
		} else if (ae.getSource() == reset) {
			tfusername.setText(""); // Clear the username field
			tfpassword.setText(""); // Clear the password field
		}
	}
	
    // Main method to run the application
	public static void main(String[] args) {
		new Login(); // display the login window
		
	}

}
