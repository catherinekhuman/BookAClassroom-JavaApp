import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFacultyInfo extends JFrame implements ActionListener{
	
	JTextField tfname, tfphone, tfempid, tfemail, tfdepartment;
	JRadioButton rbmale, rbfemale;

	public AddFacultyInfo() {
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading = new JLabel("ADD FACULTY DETAILS");
		heading.setBounds(300, 20, 500, 35);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
		heading.setForeground(Color.BLUE);
		add(heading);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(60, 80, 150, 25);
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblname);
		
		tfname = new JTextField();
		tfname.setBounds(220, 80, 150, 25);
		add(tfname);
		
		JLabel lblempid = new JLabel("Employee ID");
		lblempid.setBounds(60, 130, 150, 25);
		lblempid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblempid);
		
		tfempid = new JTextField();
		tfempid.setBounds(220, 130, 150, 25);
		add(tfempid);
		
		JLabel lbldepartment = new JLabel("Department");
		lbldepartment.setBounds(60, 180, 150, 25);
		lbldepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbldepartment);
		
		tfdepartment = new JTextField();
		tfdepartment.setBounds(220, 180, 150, 25);
		add(tfdepartment);
		
		JLabel lblgender = new JLabel("Gender");
		lblgender.setBounds(60, 230, 150, 25);
		lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblgender);
		
		ButtonGroup gendergroup = new ButtonGroup();
		
		rbmale = new JRadioButton("Male");
		rbmale.setBounds(220, 230, 70, 25);
		rbmale.setBackground(Color.WHITE);
		add(rbmale);
		
		rbfemale = new JRadioButton("Female");
		rbfemale.setBounds(300, 230, 70, 25);
		rbfemale.setBackground(Color.WHITE);
		add(rbfemale);
		
		gendergroup.add(rbmale);
		gendergroup.add(rbfemale);
		
		JLabel lblphone = new JLabel("Phone Number");
		lblphone.setBounds(60, 280, 150, 25);
		lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblphone);
		
		tfphone = new JTextField();
		tfphone.setBounds(220, 280, 150, 25);
		add(tfphone);
		
		
		JLabel lblemail = new JLabel("Email ID");
		lblemail.setBounds(60, 330, 150, 25);
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(220, 330, 150, 25);
		add(tfemail);
		
		JButton save = new JButton("SAVE");
		save.setBackground(Color.BLACK);
	    save.setForeground(Color.WHITE);
		save.setBounds(220, 380, 150, 30);
		save.addActionListener(this);
		add(save);
		
		ImageIcon image = new ImageIcon(getClass().getResource("/Images/pfp.jpg"));
		JLabel lblimage = new JLabel(image);
        lblimage.setBounds(450, 80, 280, 400);
        add(lblimage);
		
		setSize(900, 600);
		setLocation(300, 150);
		setVisible(true);
	}
	
	 public void actionPerformed(ActionEvent ae) {
	        String name = tfname.getText();
	        String empid = tfempid.getText();
	        String phone = tfphone.getText();
	        String email = tfemail.getText();
	        String department = tfdepartment.getText();
	        String gender = null;
	        if (rbmale.isSelected()) {
	            gender = "Male";
	        } else {
	            gender = "Female";
	        }
	        
	        try {
	            Conn conn = new Conn();
	            
	            String query = "insert into faculty values('"+name+"', '"+empid+"', '"+phone+"', '"+email+"', '"+department+"', '"+gender+"')";
	        
	            conn.s.executeUpdate(query);
	            
	            JOptionPane.showMessageDialog(null, "Faculty Details Added Successfully");
	        
	            setVisible(false);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	public static void main(String[] args) {
		new AddFacultyInfo();
			
		}
	}

