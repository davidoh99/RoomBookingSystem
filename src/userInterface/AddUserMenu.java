package userInterface;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import system.FileSystem;
import system.PasswordEncryption;
import users.Administrator;
import users.NormalUser;
import users.User;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

/**
 * The Class AddUserMenu.
 *
 * @author David Oh
 */
@SuppressWarnings("serial")
public class AddUserMenu extends JDialog {
	
	/** The text field user name. */
	private JTextField textFieldUserName;
	
	/** The password field new password. */
	private JPasswordField passwordFieldNewPassword;
	
	/** The password field confirm password. */
	private JPasswordField passwordFieldConfirmPassword;
	
	/** The button group. */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/** The radio button administrator. */
	private JRadioButton radioButtonAdministrator;
	
	/** The radio button normal user. */
	private JRadioButton radioButtonNormalUser;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		try {
			AddUserMenu dialog = new AddUserMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUserMenu() {
		setTitle("Add User");
		setResizable(false);
		setBounds(100, 100, 437, 160);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel labelUserName = new JLabel("Username:");
		GridBagConstraints gbc_labelUserName = new GridBagConstraints();
		gbc_labelUserName.anchor = GridBagConstraints.EAST;
		gbc_labelUserName.insets = new Insets(0, 0, 5, 5);
		gbc_labelUserName.gridx = 0;
		gbc_labelUserName.gridy = 0;
		panel.add(labelUserName, gbc_labelUserName);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		GridBagConstraints gbc_textFieldUserName = new GridBagConstraints();
		gbc_textFieldUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUserName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUserName.gridx = 1;
		gbc_textFieldUserName.gridy = 0;
		panel.add(textFieldUserName, gbc_textFieldUserName);
		
		radioButtonAdministrator = new JRadioButton("Administrator");
		buttonGroup.add(radioButtonAdministrator);
		GridBagConstraints gbc_radioButtonAdministrator = new GridBagConstraints();
		gbc_radioButtonAdministrator.anchor = GridBagConstraints.WEST;
		gbc_radioButtonAdministrator.insets = new Insets(0, 0, 5, 0);
		gbc_radioButtonAdministrator.gridx = 2;
		gbc_radioButtonAdministrator.gridy = 0;
		panel.add(radioButtonAdministrator, gbc_radioButtonAdministrator);
		
		JLabel labelNewPassWord = new JLabel("New Password:");
		GridBagConstraints gbc_labelNewPassWord = new GridBagConstraints();
		gbc_labelNewPassWord.anchor = GridBagConstraints.EAST;
		gbc_labelNewPassWord.insets = new Insets(0, 0, 5, 5);
		gbc_labelNewPassWord.gridx = 0;
		gbc_labelNewPassWord.gridy = 1;
		panel.add(labelNewPassWord, gbc_labelNewPassWord);
		
		passwordFieldNewPassword = new JPasswordField();
		GridBagConstraints gbc_passwordFieldNewPassword = new GridBagConstraints();
		gbc_passwordFieldNewPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldNewPassword.gridx = 1;
		gbc_passwordFieldNewPassword.gridy = 1;
		panel.add(passwordFieldNewPassword, gbc_passwordFieldNewPassword);
		
		radioButtonNormalUser = new JRadioButton("Normal User");
		buttonGroup.add(radioButtonNormalUser);
		GridBagConstraints gbc_radioButtonNormalUser = new GridBagConstraints();
		gbc_radioButtonNormalUser.anchor = GridBagConstraints.WEST;
		gbc_radioButtonNormalUser.insets = new Insets(0, 0, 5, 0);
		gbc_radioButtonNormalUser.gridx = 2;
		gbc_radioButtonNormalUser.gridy = 1;
		panel.add(radioButtonNormalUser, gbc_radioButtonNormalUser);
		
		JLabel labelConfirmPassWord = new JLabel("Confirm Password:");
		GridBagConstraints gbc_labelConfirmPassWord = new GridBagConstraints();
		gbc_labelConfirmPassWord.anchor = GridBagConstraints.EAST;
		gbc_labelConfirmPassWord.insets = new Insets(0, 0, 5, 5);
		gbc_labelConfirmPassWord.gridx = 0;
		gbc_labelConfirmPassWord.gridy = 2;
		panel.add(labelConfirmPassWord, gbc_labelConfirmPassWord);
		
		passwordFieldConfirmPassword = new JPasswordField();
		GridBagConstraints gbc_passwordFieldConfirmPassword = new GridBagConstraints();
		gbc_passwordFieldConfirmPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldConfirmPassword.gridx = 1;
		gbc_passwordFieldConfirmPassword.gridy = 2;
		panel.add(passwordFieldConfirmPassword, gbc_passwordFieldConfirmPassword);
		
		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addValidUser();
			}
		});
		GridBagConstraints gbc_buttonSubmit = new GridBagConstraints();
		gbc_buttonSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_buttonSubmit.gridx = 1;
		gbc_buttonSubmit.gridy = 3;
		panel.add(buttonSubmit, gbc_buttonSubmit);
	}

	/**
	 * Adds the valid user.
	 */
	private void addValidUser() {
		FileSystem.readUserObjects();
		
		//Gets the username from the lblUserName label
		String txtUserName = textFieldUserName.getText();
		
		byte[] salt;
		try {
			//Salt for the password is generated - doesn't matter if it's thrown away
			salt = PasswordEncryption.generateSalt();
			
			//User object is found through the FileSystem class using the username string
			User user = FileSystem.users.get(txtUserName);
			
			//If the user is not found (i.e. null) add a user
			if(!radioButtonNormalUser.isSelected() && !radioButtonAdministrator.isSelected()) {
				JOptionPane.showMessageDialog(null, "Please choose a user type.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//If the passwords in both textboxes are the same
			if (Arrays.equals(passwordFieldNewPassword.getPassword(), passwordFieldConfirmPassword.getPassword())){
				//If the password is bigger or equal to 6
				if(passwordFieldNewPassword.getPassword().length >= 6) {
					for(int i = 0; i < passwordFieldNewPassword.getPassword().length; i++) {
						//Check if each of the password's characters are letters or digits - if they are not, show an error dialog and return out of the method
						if(Character.isLetterOrDigit(passwordFieldNewPassword.getPassword()[i]) == false) {
							JOptionPane.showMessageDialog(null, "The password contains characters that is not letters or digits.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "The password could not be set as it is under 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}				
				if (user == null) {
					//Generate the password
					byte[] encryptedPassword = PasswordEncryption.getEncryptedPassword(passwordFieldNewPassword.getPassword(), salt);
					
					if(radioButtonNormalUser.isSelected()) {
						user = new NormalUser(txtUserName, salt, encryptedPassword);
						FileSystem.storeUser(user);
						FileSystem.writeUserObjects();
					}
					
					if(radioButtonAdministrator.isSelected()) {
						new Administrator(txtUserName, salt, encryptedPassword);
					}
					
					JOptionPane.showConfirmDialog(null, "The user's has been successfully added.", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "This username already exists. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "The user could not be added as the passwords were not the same.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			e1.printStackTrace();
		}
	}
}