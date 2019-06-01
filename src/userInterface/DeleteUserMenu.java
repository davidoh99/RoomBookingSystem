/**
 * User Interface Package
 */
package userInterface;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import system.FileSystem;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The Class DeleteUserMenu.
 *
 * @author David Oh
 */
@SuppressWarnings("serial")
public class DeleteUserMenu extends JDialog {
	
	/** The text field user name. */
	private JTextField textFieldUserName;

	/**
	 * Launch the application.
	 */
	public static void launch() {
		try {
			DeleteUserMenu dialog = new DeleteUserMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteUserMenu() {
		setTitle("Delete User");
		setBounds(100, 100, 329, 115);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblUserName = new JLabel("Username:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		panel.add(lblUserName, gbc_lblUserName);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		GridBagConstraints gbc_textFieldUserName = new GridBagConstraints();
		gbc_textFieldUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUserName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUserName.gridx = 1;
		gbc_textFieldUserName.gridy = 0;
		panel.add(textFieldUserName, gbc_textFieldUserName);
		
		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUser();
			}
		});
		GridBagConstraints gbc_buttonSubmit = new GridBagConstraints();
		gbc_buttonSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_buttonSubmit.gridx = 1;
		gbc_buttonSubmit.gridy = 1;
		panel.add(buttonSubmit, gbc_buttonSubmit);
	}

	/**
	 * Delete user.
	 */
	private void deleteUser() {
		FileSystem.readUserObjects();
		
		//Gets the username from the lblUserName label 
		String txtUserName = textFieldUserName.getText();
		
		//User object is found through the FileSystem class using the username string - if this not null, remove the user found
		if(FileSystem.users.get(txtUserName) != null) {
			FileSystem.users.remove(txtUserName);
			FileSystem.writeUserObjects();
			JOptionPane.showConfirmDialog(null, "The user was successfully removed.", "Success", JOptionPane.PLAIN_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "The user does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
