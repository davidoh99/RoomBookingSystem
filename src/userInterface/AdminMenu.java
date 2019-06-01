package userInterface;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import system.GenerateReport;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * The Class ElevatedUserMenu.
 *
 * @author David Oh
 */
@SuppressWarnings("serial")
public class AdminMenu extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu dialog = new AdminMenu();
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
	public AdminMenu() {
		setTitle("Admin Menu");
		setBounds(100, 100, 308, 418);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(30, 50, 30, 50));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Launch the add user menu
				AddUserMenu.launch();
			}
		});
		panel.add(btnAddUser);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Launch the delete user menu
				DeleteUserMenu.launch();
			}
		});
		panel.add(btnDeleteUser);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Launch the reset password menu
				ResetPasswordMenu.launch();
			}
		});
		panel.add(btnResetPassword);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Launch the generate report menu
				try {
					GenerateReport.createReport();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnGenerateReport);

	}

}
