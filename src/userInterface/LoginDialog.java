package userInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import system.FileSystem;
import system.PasswordEncryption;
import users.Administrator;
import users.NormalUser;
import users.User;
import userInterface.BookRoom;

import javax.swing.UIManager;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * The Class LoginDialog.
 *
 * @author 553621
 */
@SuppressWarnings("serial")
public class LoginDialog extends JDialog {
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The user name text field. */
	private JTextField userNameTextField;
	
	/** The password field. */
	private JPasswordField passwordField;
	
	/** The dialog. */
	private static LoginDialog dialog = new LoginDialog();
	
	private BookRoom br = BookRoom.window;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private LoginDialog() {
		setBounds(100, 100, 284, 125);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUsername = new JLabel("Username:");
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.EAST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 0;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			userNameTextField = new JTextField();
			GridBagConstraints gbc_userNameTextField = new GridBagConstraints();
			gbc_userNameTextField.insets = new Insets(0, 0, 5, 0);
			gbc_userNameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_userNameTextField.gridx = 1;
			gbc_userNameTextField.gridy = 0;
			contentPanel.add(userNameTextField, gbc_userNameTextField);
			userNameTextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 1;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 1;
			gbc_passwordField.gridy = 1;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("Login");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loginValidUser();
					}
				});
				loginButton.setActionCommand("OK");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false); 
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Checks if the String passed through parameter userName is a letter or digit.
	 * Also checks if name is not null and is not blank ("").
	 * Returns true if userName is valid, else returns false
	 *
	 * @param userName the user name
	 * @return true, if successful
	 */
	private boolean userNameIsValid(String userName) {
		if (!(userName == null || "".equals(userName))) {
            for (int i = 0; i < userName.length(); i++) {
                if (!Character.isLetterOrDigit((userName.charAt(i)))) {
                	JOptionPane.showMessageDialog(null, "The username must consist of only letters or numbers.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            return true;
        }
		JOptionPane.showMessageDialog(null, "The username field must not be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        return false;
	}
	
	/**
	 * If the username is valid, enable the main UserInterface.
	 */
	private void loginValidUser() {
		FileSystem.readUserObjects();
		
		/** Gets the username from the lblUserName label */
		String txtUserName = userNameTextField.getText();
		
		if(!userNameIsValid(txtUserName)) {
			return;
		}
		
		/** User object is found through the FileSystem class using the username string */
		User user = FileSystem.users.get(txtUserName);
		
		if(user == null) {
			JOptionPane.showMessageDialog(null, "The user does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				if(PasswordEncryption.authenticate(passwordField.getPassword(), user.getEncryptedPassword(), user.getSalt())) {
					if(user instanceof NormalUser) {
						BookRoom.setCurrentUser(user);
						br.enableBookingsViaLogIn();
						dialog.setVisible(false);
					}
					
					if(user instanceof Administrator) {
						AdminMenu.launch();
						BookRoom.setCurrentUser(user);
						br.enableBookingsViaLogIn();
						dialog.setVisible(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "The password was incorrect for the user.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
	}
}
