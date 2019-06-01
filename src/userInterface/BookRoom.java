package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import rooms.Room;
import system.FileSystem;
import users.User;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class BookRoom.
 *
 * @author David Oh
 */
public class BookRoom {

	/** The current user. */
	public static User currentUser = null;

	/** The frame. */
	public JFrame frame;

	/** The button group smart board. */
	private final ButtonGroup buttonGroupSmartBoard = new ButtonGroup();

	/** The button group white board. */
	private final ButtonGroup buttonGroupWhiteBoard = new ButtonGroup();

	/** The button group scanner. */
	private final ButtonGroup buttonGroupScanner = new ButtonGroup();

	/** The button group printer. */
	private final ButtonGroup buttonGroupPrinter = new ButtonGroup();

	/** The time picker end. */
	private TimePicker timePickerEnd;

	/** The time picker start. */
	private TimePicker timePickerStart;

	/** The date picker start. */
	private DatePicker datePickerStart;

	/** The spinner work stations. */
	private JSpinner spinnerWorkStations;

	/** The rdbtn yes smart board. */
	private JRadioButton rdbtnYesSmartBoard;

	/** The rdbtn no smart board. */
	private JRadioButton rdbtnNoSmartBoard;

	/** The rdbtn yes white board. */
	private JRadioButton rdbtnYesWhiteBoard;

	/** The rdbtn no white board. */
	private JRadioButton rdbtnNoWhiteBoard;

	/** The rdbtn yes scanner. */
	private JRadioButton rdbtnYesScanner;

	/** The rdbtn no scanner. */
	private JRadioButton rdbtnNoScanner;

	/** The rdbtn yes printer. */
	private JRadioButton rdbtnYesPrinter;

	/** The rdbtn no printer. */
	private JRadioButton rdbtnNoPrinter;

	/** The btn book. */
	private JButton btnBook;

	/** The btn clear. */
	private JButton btnClear;

	/** The mntm login. */
	private JMenuItem mntmLogin;

	/** The mntm log out. */
	private JMenuItem mntmLogOut;

	/** The date picker end. */
	private DatePicker datePickerEnd;

	/** The lbl current user. */
	private JLabel lblCurrentUser;

	/** The spinner break out seats. */
	private JSpinner spinnerBreakOutSeats;
	
	/**
	 * Gets the current user.
	 *
	 * @return the current user
	 */
	public static User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Sets the current user.
	 *
	 * @param currentUser the new current user
	 */
	public static void setCurrentUser(User currentUser) {
		BookRoom.currentUser = currentUser;
	}

	/** The window. */
	public static BookRoom window;
	
	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments (should there be any)
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					window = new BookRoom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSystem.readUserObjects();
					System.out.println("read objects");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private BookRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 440, 358);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 12, 5, 12));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panel.setLayout(gbl_panel);

		JLabel lblNoOfWorkstations = new JLabel("Number of Workstations:");
		GridBagConstraints gbc_lblNoOfWorkstations = new GridBagConstraints();
		gbc_lblNoOfWorkstations.anchor = GridBagConstraints.EAST;
		gbc_lblNoOfWorkstations.fill = GridBagConstraints.VERTICAL;
		gbc_lblNoOfWorkstations.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfWorkstations.gridx = 0;
		gbc_lblNoOfWorkstations.gridy = 0;
		panel.add(lblNoOfWorkstations, gbc_lblNoOfWorkstations);

		spinnerWorkStations = new JSpinner();
		spinnerWorkStations.setEnabled(false);
		GridBagConstraints gbc_spinnerWorkStations = new GridBagConstraints();
		gbc_spinnerWorkStations.fill = GridBagConstraints.BOTH;
		gbc_spinnerWorkStations.gridwidth = 3;
		gbc_spinnerWorkStations.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerWorkStations.gridx = 1;
		gbc_spinnerWorkStations.gridy = 0;
		panel.add(spinnerWorkStations, gbc_spinnerWorkStations);

		JLabel lblNoOfBreakoutSeats = new JLabel("Number of Breakout Seats:");
		GridBagConstraints gbc_lblNoOfBreakoutSeats = new GridBagConstraints();
		gbc_lblNoOfBreakoutSeats.anchor = GridBagConstraints.EAST;
		gbc_lblNoOfBreakoutSeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfBreakoutSeats.gridx = 0;
		gbc_lblNoOfBreakoutSeats.gridy = 1;
		panel.add(lblNoOfBreakoutSeats, gbc_lblNoOfBreakoutSeats);

		spinnerBreakOutSeats = new JSpinner();
		spinnerBreakOutSeats.setEnabled(false);
		GridBagConstraints gbc_spinnerBreakOutSeats = new GridBagConstraints();
		gbc_spinnerBreakOutSeats.gridwidth = 3;
		gbc_spinnerBreakOutSeats.fill = GridBagConstraints.BOTH;
		gbc_spinnerBreakOutSeats.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerBreakOutSeats.gridx = 1;
		gbc_spinnerBreakOutSeats.gridy = 1;
		panel.add(spinnerBreakOutSeats, gbc_spinnerBreakOutSeats);

		JLabel label = new JLabel("-");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 6;
		panel.add(label, gbc_label);

		JLabel lblSmartboard = new JLabel("Smartboard:");
		GridBagConstraints gbc_lblSmartboard = new GridBagConstraints();
		gbc_lblSmartboard.anchor = GridBagConstraints.EAST;
		gbc_lblSmartboard.fill = GridBagConstraints.VERTICAL;
		gbc_lblSmartboard.insets = new Insets(0, 0, 5, 5);
		gbc_lblSmartboard.gridx = 0;
		gbc_lblSmartboard.gridy = 2;
		panel.add(lblSmartboard, gbc_lblSmartboard);

		rdbtnYesSmartBoard = new JRadioButton("Yes");
		rdbtnYesSmartBoard.setEnabled(false);
		buttonGroupSmartBoard.add(rdbtnYesSmartBoard);
		rdbtnYesSmartBoard.setSelected(true);
		GridBagConstraints gbc_rdbtnYesSmartBoard = new GridBagConstraints();
		gbc_rdbtnYesSmartBoard.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnYesSmartBoard.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYesSmartBoard.gridx = 1;
		gbc_rdbtnYesSmartBoard.gridy = 2;
		panel.add(rdbtnYesSmartBoard, gbc_rdbtnYesSmartBoard);

		rdbtnNoSmartBoard = new JRadioButton("No");
		rdbtnNoSmartBoard.setEnabled(false);
		buttonGroupSmartBoard.add(rdbtnNoSmartBoard);
		GridBagConstraints gbc_rdbtnNoSmartBoard = new GridBagConstraints();
		gbc_rdbtnNoSmartBoard.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNoSmartBoard.gridx = 3;
		gbc_rdbtnNoSmartBoard.gridy = 2;
		panel.add(rdbtnNoSmartBoard, gbc_rdbtnNoSmartBoard);

		JLabel lblWhiteboard = new JLabel("Whiteboard:");
		GridBagConstraints gbc_lblWhiteboard = new GridBagConstraints();
		gbc_lblWhiteboard.anchor = GridBagConstraints.EAST;
		gbc_lblWhiteboard.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhiteboard.gridx = 0;
		gbc_lblWhiteboard.gridy = 3;
		panel.add(lblWhiteboard, gbc_lblWhiteboard);

		rdbtnYesWhiteBoard = new JRadioButton("Yes");
		rdbtnYesWhiteBoard.setEnabled(false);
		buttonGroupWhiteBoard.add(rdbtnYesWhiteBoard);
		GridBagConstraints gbc_rdbtnYesWhiteBoard = new GridBagConstraints();
		gbc_rdbtnYesWhiteBoard.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYesWhiteBoard.gridx = 1;
		gbc_rdbtnYesWhiteBoard.gridy = 3;
		panel.add(rdbtnYesWhiteBoard, gbc_rdbtnYesWhiteBoard);

		rdbtnNoWhiteBoard = new JRadioButton("No");
		rdbtnNoWhiteBoard.setEnabled(false);
		buttonGroupWhiteBoard.add(rdbtnNoWhiteBoard);
		rdbtnNoWhiteBoard.setSelected(true);
		GridBagConstraints gbc_rdbtnNoWhiteBoard = new GridBagConstraints();
		gbc_rdbtnNoWhiteBoard.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNoWhiteBoard.gridx = 3;
		gbc_rdbtnNoWhiteBoard.gridy = 3;
		panel.add(rdbtnNoWhiteBoard, gbc_rdbtnNoWhiteBoard);

		JLabel lblScanner = new JLabel("Scanner:");
		GridBagConstraints gbc_lblScanner = new GridBagConstraints();
		gbc_lblScanner.anchor = GridBagConstraints.EAST;
		gbc_lblScanner.fill = GridBagConstraints.VERTICAL;
		gbc_lblScanner.insets = new Insets(0, 0, 5, 5);
		gbc_lblScanner.gridx = 0;
		gbc_lblScanner.gridy = 4;
		panel.add(lblScanner, gbc_lblScanner);

		rdbtnYesScanner = new JRadioButton("Yes");
		rdbtnYesScanner.setEnabled(false);
		buttonGroupScanner.add(rdbtnYesScanner);
		GridBagConstraints gbc_rdbtnYesScanner = new GridBagConstraints();
		gbc_rdbtnYesScanner.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYesScanner.gridx = 1;
		gbc_rdbtnYesScanner.gridy = 4;
		panel.add(rdbtnYesScanner, gbc_rdbtnYesScanner);

		rdbtnNoScanner = new JRadioButton("No");
		rdbtnNoScanner.setEnabled(false);
		buttonGroupScanner.add(rdbtnNoScanner);
		rdbtnNoScanner.setSelected(true);
		GridBagConstraints gbc_rdbtnNoScanner = new GridBagConstraints();
		gbc_rdbtnNoScanner.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNoScanner.gridx = 3;
		gbc_rdbtnNoScanner.gridy = 4;
		panel.add(rdbtnNoScanner, gbc_rdbtnNoScanner);

		JLabel lblPrinter = new JLabel("Printer:");
		GridBagConstraints gbc_lblPrinter = new GridBagConstraints();
		gbc_lblPrinter.anchor = GridBagConstraints.EAST;
		gbc_lblPrinter.fill = GridBagConstraints.VERTICAL;
		gbc_lblPrinter.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrinter.gridx = 0;
		gbc_lblPrinter.gridy = 5;
		panel.add(lblPrinter, gbc_lblPrinter);

		rdbtnYesPrinter = new JRadioButton("Yes");
		rdbtnYesPrinter.setEnabled(false);
		buttonGroupPrinter.add(rdbtnYesPrinter);
		rdbtnYesPrinter.setSelected(true);
		GridBagConstraints gbc_rdbtnYesPrinter = new GridBagConstraints();
		gbc_rdbtnYesPrinter.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYesPrinter.gridx = 1;
		gbc_rdbtnYesPrinter.gridy = 5;
		panel.add(rdbtnYesPrinter, gbc_rdbtnYesPrinter);

		rdbtnNoPrinter = new JRadioButton("No");
		rdbtnNoPrinter.setEnabled(false);
		buttonGroupPrinter.add(rdbtnNoPrinter);
		GridBagConstraints gbc_rdbtnNoPrinter = new GridBagConstraints();
		gbc_rdbtnNoPrinter.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNoPrinter.gridx = 3;
		gbc_rdbtnNoPrinter.gridy = 5;
		panel.add(rdbtnNoPrinter, gbc_rdbtnNoPrinter);

		JLabel lblNewLabel = new JLabel("Booking Date:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		datePickerStart = new DatePicker();
		datePickerStart.getComponentToggleCalendarButton().setEnabled(false);
		datePickerStart.getComponentDateTextField().setEnabled(false);
		datePickerStart.getComponentDateTextField().setEditable(false);
		datePickerStart.setDateToToday();
		GridBagConstraints gbc_datePickerStart = new GridBagConstraints();
		gbc_datePickerStart.insets = new Insets(0, 0, 5, 5);
		gbc_datePickerStart.fill = GridBagConstraints.BOTH;
		gbc_datePickerStart.gridx = 1;
		gbc_datePickerStart.gridy = 6;
		panel.add(datePickerStart, gbc_datePickerStart);

		datePickerEnd = new DatePicker();
		datePickerEnd.getComponentDateTextField().setEnabled(false);
		datePickerEnd.getComponentDateTextField().setEditable(false);
		datePickerEnd.getComponentToggleCalendarButton().setEnabled(false);
		datePickerEnd.setDateToToday();
		GridBagConstraints gbc_datePickerEnd = new GridBagConstraints();
		gbc_datePickerEnd.insets = new Insets(0, 0, 5, 0);
		gbc_datePickerEnd.fill = GridBagConstraints.BOTH;
		gbc_datePickerEnd.gridx = 3;
		gbc_datePickerEnd.gridy = 6;
		panel.add(datePickerEnd, gbc_datePickerEnd);

		JLabel lblBookingTime = new JLabel("Booking Time:");
		GridBagConstraints gbc_lblBookingTime = new GridBagConstraints();
		gbc_lblBookingTime.anchor = GridBagConstraints.EAST;
		gbc_lblBookingTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblBookingTime.gridx = 0;
		gbc_lblBookingTime.gridy = 7;
		panel.add(lblBookingTime, gbc_lblBookingTime);

		timePickerStart = new TimePicker();
		timePickerStart.getComponentTimeTextField().setEnabled(false);
		timePickerStart.getComponentToggleTimeMenuButton().setEnabled(false);
		timePickerStart.getComponentTimeTextField().setEditable(false);
		timePickerStart.getComponentTimeTextField().addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { //automatically set the end time to 30 minutes past the first
				timePickerStart.setTextFieldToValidStateIfNeeded();
				timePickerEnd.setTime(timePickerStart.getTime().plusMinutes(30));
			}
		});
		timePickerStart.setTimeToNow();
		GridBagConstraints gbc_timePickerStart = new GridBagConstraints();
		gbc_timePickerStart.insets = new Insets(0, 0, 0, 5);
		gbc_timePickerStart.fill = GridBagConstraints.BOTH;
		gbc_timePickerStart.gridx = 1;
		gbc_timePickerStart.gridy = 7;
		panel.add(timePickerStart, gbc_timePickerStart);

		JLabel labelDash = new JLabel("-");
		GridBagConstraints gbc_labelDash = new GridBagConstraints();
		gbc_labelDash.insets = new Insets(0, 0, 0, 5);
		gbc_labelDash.gridx = 2;
		gbc_labelDash.gridy = 7;
		panel.add(labelDash, gbc_labelDash);

		timePickerEnd = new TimePicker();
		timePickerEnd.getComponentTimeTextField().setEnabled(false);
		timePickerEnd.getComponentToggleTimeMenuButton().setEnabled(false);
		timePickerEnd.getComponentTimeTextField().setEditable(false);
		timePickerEnd.setTime(timePickerStart.getTime().plusMinutes(30));
		timePickerEnd.getComponentTimeTextField().addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { 
				timePickerStart.setTextFieldToValidStateIfNeeded();
			}
		});
		GridBagConstraints gbc_timePickerEnd = new GridBagConstraints();
		gbc_timePickerEnd.fill = GridBagConstraints.BOTH;
		gbc_timePickerEnd.gridx = 3;
		gbc_timePickerEnd.gridy = 7;
		panel.add(timePickerEnd, gbc_timePickerEnd);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 3, 0));
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(50);
		panel_1.add(horizontalStrut);

		btnBook = new JButton("Book");
		btnBook.setEnabled(false);
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookRoom();
			}
		});
		panel_1.add(btnBook);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetInputs();
			}
		});
		btnClear.setEnabled(false);
		panel_1.add(btnClear);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDialog.launch();
			}
		});
		mntmLogin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnFile.add(mntmLogin);

		JMenuItem mntmAddUser = new JMenuItem("Add User");
		mntmAddUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmAddUser.setEnabled(false);
		mnFile.add(mntmAddUser);

		JMenuItem mntmDeleteUser = new JMenuItem("Delete User");
		mntmDeleteUser.setEnabled(false);
		mntmDeleteUser
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnFile.add(mntmDeleteUser);

		JMenuItem mntmResetUserPassword = new JMenuItem("Reset User Password");
		mntmResetUserPassword.setEnabled(false);
		mntmResetUserPassword
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnFile.add(mntmResetUserPassword);

		mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableBookingsViaLogOut();
			}
		});
		mntmLogOut.setEnabled(false);
		mntmLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnFile.add(mntmLogOut);

		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);

		lblCurrentUser = new JLabel("No Current User");
		menuBar.add(lblCurrentUser);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue_1);

		JMenu menu = new JMenu("Help");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Help");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "Help.txt"); //open Help.txt (already has content in it and has been set to read only)
				try {
					pb.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menu.add(menuItem);
	}

	/**
	 * Book room.
	 */
	public void bookRoom() {
		boolean smartBoard;
		boolean printer;
		boolean scanner;
		boolean whiteBoard;

		if (rdbtnYesSmartBoard.isSelected()) {
			smartBoard = true;
		} else {
			smartBoard = false;
		}

		if (rdbtnYesPrinter.isSelected()) {
			printer = true;
		} else {
			printer = false;
		}

		if (rdbtnYesScanner.isSelected()) {
			scanner = true;
		} else {
			scanner = false;
		}

		if (rdbtnYesWhiteBoard.isSelected()) {
			whiteBoard = true;
		} else {
			whiteBoard = false;
		}

		if ((int) spinnerWorkStations.getValue() < 0) { //if spinner is smaller than 0
			JOptionPane.showMessageDialog(null, "Please input a valid number of work stations required.",
					"Booking Failure", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if ((int) spinnerBreakOutSeats.getValue() < 0) { //if spinner is smaller than 0
			JOptionPane.showMessageDialog(null, "Please input a valid number of breakout seats required.",
					"Booking Failure", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(datePickerStart.getDate().isAfter(datePickerEnd.getDate())) {
			JOptionPane.showMessageDialog(null, "Please choose dates that follow the normal convention of time.",
					"Booking Failure", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Room.bookRoom(currentUser, datePickerStart.getDate(), datePickerEnd.getDate(), timePickerStart.getTime(),
				timePickerEnd.getTime(), (Integer) spinnerWorkStations.getValue(), smartBoard, printer, scanner,
				whiteBoard, (int) spinnerBreakOutSeats.getValue());
	}
	
	/**
	 * Enable bookings via log in.
	 */
	public void enableBookingsViaLogIn() {
		//procedurally enable all the Swing components
		spinnerWorkStations.setEnabled(true);
		spinnerBreakOutSeats.setEnabled(true);
		rdbtnYesSmartBoard.setEnabled(true);
		rdbtnNoSmartBoard.setEnabled(true);
		rdbtnYesWhiteBoard.setEnabled(true);
		rdbtnNoWhiteBoard.setEnabled(true);
		rdbtnYesScanner.setEnabled(true);
		rdbtnNoScanner.setEnabled(true);
		rdbtnYesPrinter.setEnabled(true);
		rdbtnNoPrinter.setEnabled(true);
		datePickerStart.getComponentToggleCalendarButton().setEnabled(false);
		datePickerStart.getComponentDateTextField().setEnabled(true);
		datePickerStart.getComponentDateTextField().setEditable(true);
		datePickerEnd.getComponentDateTextField().setEnabled(true);
		datePickerEnd.getComponentDateTextField().setEditable(true);
		datePickerStart.getComponentToggleCalendarButton().setEnabled(true);
		datePickerEnd.getComponentToggleCalendarButton().setEnabled(true);
		timePickerStart.getComponentTimeTextField().setEnabled(true);
		timePickerStart.getComponentToggleTimeMenuButton().setEnabled(true);
		timePickerStart.getComponentTimeTextField().setEditable(true);
		timePickerEnd.getComponentTimeTextField().setEnabled(true);
		timePickerEnd.getComponentToggleTimeMenuButton().setEnabled(true);
		timePickerEnd.getComponentTimeTextField().setEditable(true);
		btnBook.setEnabled(true);
		btnClear.setEnabled(true);
		mntmLogin.setEnabled(false);
		mntmLogOut.setEnabled(true);

		lblCurrentUser.setText(currentUser.getUserName());

		resetInputs();
	}
	
	/**
	 * Disable bookings via log out.
	 */
	public void disableBookingsViaLogOut() {
		//procedurally disable all the Swing components
		spinnerWorkStations.setEnabled(false);
		spinnerBreakOutSeats.setEnabled(false);
		rdbtnYesSmartBoard.setEnabled(false);
		rdbtnNoSmartBoard.setEnabled(false);
		rdbtnYesWhiteBoard.setEnabled(false);
		rdbtnNoWhiteBoard.setEnabled(false);
		rdbtnYesScanner.setEnabled(false);
		rdbtnNoScanner.setEnabled(false);
		rdbtnYesPrinter.setEnabled(false);
		rdbtnNoPrinter.setEnabled(false);
		datePickerStart.getComponentToggleCalendarButton().setEnabled(false);
		datePickerStart.getComponentDateTextField().setEnabled(false);
		datePickerStart.getComponentDateTextField().setEditable(false);
		datePickerEnd.getComponentToggleCalendarButton().setEnabled(false);
		datePickerEnd.getComponentDateTextField().setEnabled(false);
		datePickerEnd.getComponentDateTextField().setEditable(false);
		timePickerStart.getComponentTimeTextField().setEnabled(false);
		timePickerStart.getComponentToggleTimeMenuButton().setEnabled(false);
		timePickerStart.getComponentTimeTextField().setEditable(false);
		timePickerEnd.getComponentTimeTextField().setEnabled(false);
		timePickerEnd.getComponentToggleTimeMenuButton().setEnabled(false);
		timePickerEnd.getComponentTimeTextField().setEditable(false);
		btnBook.setEnabled(false);
		btnClear.setEnabled(false);
		mntmLogin.setEnabled(true);
		mntmLogOut.setEnabled(false);

		lblCurrentUser.setText("No Current User");
		
		currentUser = null;

		resetInputs();
	}
	
	/**
	 * Reset inputs.
	 */
	private void resetInputs() {
		//procedurally reset inputs to default values
		spinnerWorkStations.setValue(0);
		spinnerBreakOutSeats.setValue(0);
		rdbtnYesSmartBoard.setSelected(true);
		rdbtnNoWhiteBoard.setSelected(true);
		rdbtnNoScanner.setSelected(true);
		rdbtnYesPrinter.setSelected(true);
		datePickerStart.setDateToToday();
		datePickerEnd.setDateToToday();
		timePickerStart.setTimeToNow();
		timePickerEnd.setTime(timePickerStart.getTime().plusMinutes(30));
	}
}
