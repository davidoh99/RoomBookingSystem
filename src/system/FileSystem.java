package system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rooms.Room;
import users.User;

/**
 * The Class FileSystem.
 *
 * @author David Oh
 */
@SuppressWarnings("serial")
public abstract class FileSystem implements Serializable {

	/**
	 * The Constant FILE_NAME which stores the users HashMap as serialised objects
	 * in an external file.
	 */
	private static final String USERS_FILE = "DB.jdat";

	/**
	 * The Constant ROOMS_FILE which stores the rooms ArrayList as serialised
	 * objects in an external file.
	 */
	private static final String ROOMS_FILE = "DBR.jdat";

	/**
	 * The Constant BOOKINGS_FILE which stores the rooms ArrayList as serialised
	 * objects in an external file.
	 */
	private static final String BOOKINGS_FILE = "DBB.jdat";

	/** The users stored in a HashMap. */
	public static Map<String, User> users = new HashMap<>();

	/** The rooms stored in an ArrayList. */
	public static ArrayList<Room> rooms = new ArrayList<>();

	/** The bookings stored in an ArrayList. */
	public static ArrayList<Booking> bookings = new ArrayList<>();

	/**
	 * Store user objects into the users HashMap.
	 *
	 * @param user
	 *            the user
	 */
	public static void storeUser(User user) {
		users.put(user.getUserName(), user);
	}

	/**
	 * Store room objects into the rooms ArrayList.
	 *
	 * @param room
	 *            the room
	 */
	public static void storeRoom(Room room) {
		rooms.add(room);
	}

	/**
	 * Store booking objects into the bookings ArrayList.
	 *
	 * @param booking
	 *            the booking
	 */
	public static void storeBooking(Booking booking) {
		bookings.add(booking);
	}

	/**
	 * Write user objects to the specified file.
	 * 
	 */
	public static void writeUserObjects() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(USERS_FILE)))) {
			oos.writeObject(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write room objects to the specified file.
	 */
	public static void writeRoomObjects() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(ROOMS_FILE)))) {
			oos.writeObject(rooms);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write booking objects to the specified file.
	 */
	public static void writeBookingObjects() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(BOOKINGS_FILE)))) {
			oos.writeObject(bookings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read user objects.
	 */
	@SuppressWarnings("unchecked")
	public static void readUserObjects() {
		File f = new File(USERS_FILE);

		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				users = (HashMap<String, User>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read room objects.
	 */
	@SuppressWarnings("unchecked")
	public static void readRoomObjects() {
		File f = new File(ROOMS_FILE);

		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				rooms = (ArrayList<Room>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read booking objects.
	 */
	@SuppressWarnings("unchecked")
	public static void readBookingObjects() {
		File f = new File(BOOKINGS_FILE);

		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				bookings = (ArrayList<Booking>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
