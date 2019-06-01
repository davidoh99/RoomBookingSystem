package userInterface;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;

import system.FileSystem;
import system.PasswordEncryption;
import users.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * The Class ResetPasswordMenu.
 *
 * @author David Oh
 */
@SuppressWarnings("serial")
public class ResetPasswordMenu extends JDialog {
	
	/** The password field new password. */
	private JPasswordField passwordFieldNewPassword;
	
	/** The password field confirm password. */
	private JPasswordField passwordFieldConfirmPassword;
	
	/** The text field user name. */
	private JTextField textFieldUserName;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPasswordMenu dialog = new ResetPasswordMenu();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ResetPasswordMenu() {
		setTitle("Reset Password");
		setResizable(false);
		setBounds(100, 100, 305, 163);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblUserName = new JLabel("Username:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		panel.add(lblUserName, gbc_lblUserName);

		textFieldUserName = new JTextField();
		GridBagConstraints gbc_textFieldUserName = new GridBagConstraints();
		gbc_textFieldUserName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUserName.gridx = 1;
		gbc_textFieldUserName.gridy = 0;
		panel.add(textFieldUserName, gbc_textFieldUserName);
		textFieldUserName.setColumns(10);

		JLabel lblNewPassword = new JLabel("New Password:");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.gridx = 0;
		gbc_lblNewPassword.gridy = 1;
		panel.add(lblNewPassword, gbc_lblNewPassword);

		passwordFieldNewPassword = new JPasswordField();
		GridBagConstraints gbc_passwordFieldNewPassword = new GridBagConstraints();
		gbc_passwordFieldNewPassword.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldNewPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldNewPassword.gridx = 1;
		gbc_passwordFieldNewPassword.gridy = 1;
		panel.add(passwordFieldNewPassword, gbc_passwordFieldNewPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmPassword.gridx = 0;
		gbc_lblConfirmPassword.gridy = 2;
		panel.add(lblConfirmPassword, gbc_lblConfirmPassword);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValidUserPassword();
			}
		});

		passwordFieldConfirmPassword = new JPasswordField();
		GridBagConstraints gbc_passwordFieldConfirmPassword = new GridBagConstraints();
		gbc_passwordFieldConfirmPassword.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldConfirmPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldConfirmPassword.gridx = 1;
		gbc_passwordFieldConfirmPassword.gridy = 2;
		panel.add(passwordFieldConfirmPassword, gbc_passwordFieldConfirmPassword);
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 3;
		panel.add(btnSubmit, gbc_btnSubmit);

	}

	
	/**
	 * Reset valid user's password.
	 */
	public void resetValidUserPassword() {
		FileSystem.readUserObjects();
		
		/** Gets the username from the lblUserName label */
		String txtUserName = textFieldUserName.getText();
		
		/** User object is found through the FileSystem class using the username string */
		User user = FileSystem.users.get(txtUserName);
		
		byte[] salt;
		try {
			/** Salt for the password is generated - doesn't matter if it's thrown away */
			salt = PasswordEncryption.generateSalt();
			
			/** If the user is not found (i.e. null) show an error dialog box and return out of the method */
			if (user == null) {
				JOptionPane.showMessageDialog(null, "The user does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			/** If the passwords in both textboxes are the same */
			if (Arrays.equals(passwordFieldNewPassword.getPassword(), passwordFieldConfirmPassword.getPassword())) {
				/** If the password is bigger or equal to 6 */
				if(passwordFieldNewPassword.getPassword().length >= 6) {
					for(int i = 0; i < passwordFieldNewPassword.getPassword().length; i++) {
						/** Check if each of the password's characters are letters or digits - if they are not, show an error dialog and return out of the method */
						if(Character.isLetterOrDigit(passwordFieldNewPassword.getPassword()[i]) == false) {
							JOptionPane.showMessageDialog(null, "The password contains characters that is not letters or digits.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "The password could not be reset as it is under 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				/** Generate the password */
				user.setSalt(salt);
				user.setEncryptedPassword(PasswordEncryption.getEncryptedPassword(passwordFieldNewPassword.getPassword(), salt));
				JOptionPane.showConfirmDialog(null, "The user's password has been reset", "Success", JOptionPane.PLAIN_MESSAGE);
				FileSystem.writeUserObjects();
			}
			else {
				JOptionPane.showMessageDialog(null, "The password could not be reset as the passwords were not the same.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			e1.printStackTrace();
		}
	}
}
