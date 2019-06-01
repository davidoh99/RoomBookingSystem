package rooms;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import system.Booking;
import system.FileSystem;
import users.User;

/**
 * The Enum Room.
 *
 * @author David
 */
public enum Room implements Serializable {

	/** The conference room. */
	// Many of these values are placeholders as I haven't checked all the rooms'
	// real facilities
	CONFERENCE_ROOM(0, 13, 2, true, false, false, true, null, null),

	EIGHT(8, 14, 1, true, false, false, true, null, null),

	ELEVEN(11, 12, 1, true, false, false, true, null, null),

	THIRTEEN(13, 14, 2, true, false, false, true, null, null),

	FOURTEEN(14, 13, 5, true, true, true, true, null, null),

	SEVENTEEN(17, 12, 7, true, false, true, true, null, null),

	TWENTY(20, 14, 4, true, false, false, true, null, null),

	THREE_HUNDRED_AND_ONE(301, 15, 3, true, false, false, true, null, null),

	THREE_HUNDRED_AND_SIX(306, 16, 5, true, false, false, true, null, null),

	THREE_HUNDRED_AND_EIGHT(308, 12, 4, true, false, false, true, null, null);

	/** The room number. */
	private final int roomNumber;

	/** The number of work stations the room has. */
	private final int numberOfWorkStations;

	/** The break out seats availability. */
	private final int breakOutSeats;

	/** The smart board availability. */
	private final boolean smartBoard;

	/** The white board availability. */
	private final boolean whiteBoard;

	/** The scanner availability. */
	private final boolean scanner;

	/** The printer availability. */
	private final boolean printer;

	/** The booked dates. */
	private Map<LocalDate, Booking> bookedDates;

	/** The booked times. */
	private ArrayList<LocalTime[]> bookedTimes;

	/** A list of all the rooms */
	private static ArrayList<Room> allRooms = new ArrayList<>();

	/**
	 * Instantiates a new room.
	 *
	 * @param roomNumber
	 *            the room number
	 * @param numberOfWorkStations
	 *            the number of work stations
	 * @param breakOutSeats
	 *            the break out seats available
	 * @param smartBoard
	 *            the smart board availability
	 * @param whiteBoard
	 *            the white board availability
	 * @param scanner
	 *            the scanner availability
	 * @param printer
	 *            the printer availability
	 * @param bookedDates
	 *            the booked dates
	 * @param bookedTimes
	 *            the booked times
	 */
	Room(int roomNumber, int numberOfWorkStations, int breakOutSeats, boolean smartBoard, boolean whiteBoard,
			boolean scanner, boolean printer, Map<Integer, Booking> bookedDates, Map<Integer, Booking> bookedTimes) {
		this.roomNumber = roomNumber;
		this.numberOfWorkStations = numberOfWorkStations;
		this.breakOutSeats = breakOutSeats;
		this.smartBoard = smartBoard;
		this.whiteBoard = whiteBoard;
		this.scanner = scanner;
		this.printer = printer;
		this.setBookedDates(new HashMap<LocalDate, Booking>());
		this.setBookedTimes(new ArrayList<LocalTime[]>());
	}

	/**
	 * Gets the room number.
	 *
	 * @return the room number
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Gets the number of work stations.
	 *
	 * @return the number of work stations
	 */
	public int getNumberOfWorkStations() {
		return numberOfWorkStations;
	}

	/**
	 * Gets the break out seats.
	 *
	 * @return the break out seats
	 */
	public int getBreakOutSeats() {
		return breakOutSeats;
	}

	/**
	 * Gets the smart board availability.
	 *
	 * @return the smart board
	 */
	public boolean getSmartBoard() {
		return smartBoard;
	}

	/**
	 * Gets the white board availability.
	 *
	 * @return the white board
	 */
	public boolean getWhiteBoard() {
		return whiteBoard;
	}

	/**
	 * Gets the scanner availability.
	 *
	 * @return the scanner
	 */
	public boolean getScanner() {
		return scanner;
	}

	/**
	 * Gets the printer availability.
	 *
	 * @return the printer
	 */
	public boolean getPrinter() {
		return printer;
	}

	/**
	 * Gets the booked dates.
	 *
	 * @return the booked dates
	 */
	public Map<LocalDate, Booking> getBookedDates() {
		return bookedDates;
	}

	/**
	 * Sets the booked dates.
	 *
	 * @param bookedDates
	 *            the booked dates
	 */
	public void setBookedDates(Map<LocalDate, Booking> bookedDates) {
		this.bookedDates = bookedDates;
	}

	/**
	 * Gets the booked times.
	 *
	 * @return the booked times
	 */
	public ArrayList<LocalTime[]> getBookedTimes() {
		return bookedTimes;
	}

	/**
	 * Sets the booked times.
	 *
	 * @param arrayList
	 *            the new booked times
	 */
	public void setBookedTimes(ArrayList<LocalTime[]> arrayList) {
		this.bookedTimes = arrayList;
	}

	/**
	 * Gets the max amount of rooms.
	 *
	 * @return the max rooms
	 */
	public static int getMaxRooms() {
		return getAllRooms().size();
	}

	/**
	 * Gets the all rooms. If the allRooms ArrayList is empty, add all the rooms to
	 * it.
	 *
	 * @return the all rooms
	 */
	public static ArrayList<Room> getAllRooms() {
		if (allRooms.isEmpty()) {
			for (Room r : Room.values()) {
				allRooms.add(r);
			}
		}
		return allRooms;
	}

	/**
	 * Choose best rooms.
	 *
	 * @param userRequestedWorkStations
	 *            the user requested work stations
	 * @param smartBoard
	 *            the smart board
	 * @param printer
	 *            the printer
	 * @param scanner
	 *            the scanner
	 * @param whiteBoard
	 *            the white board
	 * @param userRequestedBreakOutSeats
	 *            the user requested break out seats
	 * @return the room[]
	 */
	private static Room[] chooseBestRooms(int userRequestedWorkStations, boolean smartBoard, boolean printer,
			boolean scanner, boolean whiteBoard, int userRequestedBreakOutSeats) {
		getAllRooms();

		// The difference between a room's number of workstations and the user's desired
		// number of workstations
		int[] workStationDifference = new int[10];

		// Create an array of the unbooked rooms arraylist for easy sorting
		Room[] allRoomsArray = new Room[Room.allRooms.size()];
		allRoomsArray = Room.allRooms.toArray(allRoomsArray);

		// find the smallest difference in the user's requested workstations vs what the
		// rooms actually have
		for (int i = 0; i < allRoomsArray.length; i++) {
			int difference = Room.allRooms.get(i).getNumberOfWorkStations() - userRequestedWorkStations;

			workStationDifference[i] = difference;
		}

		// sort in order of the smallest difference in the workstations figured out in
		// the for loop above
		allRoomsArray = bubbleSortArrays(workStationDifference, allRoomsArray);
		
		//delete all the workstations that don't have enough rooms for the user
		for(int i = 0; i < workStationDifference.length; i++) {
			if(workStationDifference[i] < 0) {				
				for(int j = i; j < workStationDifference.length - 1; j++) {
					workStationDifference[j] = workStationDifference[j + 1];
					allRoomsArray[j] = allRoomsArray[j + 1];
				}
				
				int[] tempWSD = workStationDifference;
				Room[] tempARA = allRoomsArray;
				
				workStationDifference = new int[tempWSD.length - 1];
				allRoomsArray = new Room[tempARA.length - 1];
				
				for(int k = 0; k < workStationDifference.length; k++) {
					workStationDifference[k] = tempWSD[k];
					allRoomsArray[k] = tempARA[k];
				}
				i = -1;
			}
		}

		// workstation differences are now converted into a scoring system where the
		// lowest number wins
		int[] scores = workStationDifference;

		// for each room, score them on if they have the correct utilities required
		for (int i = 0; i < scores.length; i++) {
			if (allRoomsArray[i].smartBoard == smartBoard) {
				scores[i] -= 1;
			}
			if (allRoomsArray[i].printer == printer) {
				scores[i] -= 1;
			}
			if (allRoomsArray[i].scanner == scanner) {
				scores[i] -= 1;
			}
			if (allRoomsArray[i].whiteBoard == whiteBoard) {
				scores[i] -= 1;
			}
		}

		// sort the rooms in order of scores, from lowest to highest
		allRoomsArray = bubbleSortArrays(scores, allRoomsArray);
		return allRoomsArray;
	}

	/**
	 * Bubble sort arrays. Sorts two arrays at the same time because the scores
	 * array need to be in the same order as the rooms array.
	 *
	 * @param scores
	 *            the scores
	 * @param rooms
	 *            the rooms
	 * @return the room[]
	 */
	public static Room[] bubbleSortArrays(int[] scores, Room[] rooms) {
		// sorry for using a bubble sort but this was the only way I could think of for
		// sorting two arrays at once
		int n = scores.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (scores[j] > scores[j + 1]) {
					int temp = scores[j];
					scores[j] = scores[j + 1];
					scores[j + 1] = temp;

					Room temp2 = rooms[j];
					rooms[j] = rooms[j + 1];
					rooms[j + 1] = temp2;
				}
			}
		}

		return rooms;
	}

	/**
	 * Creates the booking using time.
	 *
	 * @param room
	 *            the room
	 * @param user
	 *            the user
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @return Booking, else null
	 */
	private static Booking createBookingUsingTime(Room room, User user, LocalDate startDate, LocalDate endDate,
			LocalTime startTime, LocalTime endTime) {
		LocalDate[] dateList = { startDate, endDate };

		for (int i = 0; i < dateList.length; i++) {
			Booking booking = room.getBookedDates().get(dateList[i]);
			if (booking != null) {// we don't store null values for unreserved dates for the room
				for (LocalTime[] time : room.bookedTimes) {
					if (time[0] == null && time[1] == null) {
						break;// break because there are no times so booking is free
					}
					if (time[0].isAfter(endTime) || time[1].isBefore(startTime) || time[0].equals(endTime)
							|| time[1].equals(startTime)) {
						break;// break because there are no overlaps in time
					} else {
						return null;// room is reserved on this date and time by another user so this room is
									// unavailable
					}
				}
			}
		}

		Booking newBooking = new Booking(room, startDate, endDate, startTime, endTime, user);

		for (LocalDate date : dateList) {
			room.getBookedDates().put(date, newBooking);// put bookings into a room, using the date as a key
		}

		room.bookedTimes.add(new LocalTime[] { startTime, endTime }); // add start and end time

		return newBooking;
	}

	/**
	 * Book room.
	 *
	 * @param user
	 *            the user
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @param userRequestedWorkStations
	 *            the user requested work stations
	 * @param smartBoard
	 *            the smart board
	 * @param printer
	 *            the printer
	 * @param scanner
	 *            the scanner
	 * @param whiteBoard
	 *            the white board
	 * @param userRequestedBreakOutSeats
	 *            the user requested break out seats
	 */
	public static void bookRoom(User user, LocalDate startDate, LocalDate endDate, LocalTime startTime,
			LocalTime endTime, int userRequestedWorkStations, boolean smartBoard, boolean printer, boolean scanner,
			boolean whiteBoard, int userRequestedBreakOutSeats) {
		if (userRequestedWorkStations > Room.THREE_HUNDRED_AND_SIX.numberOfWorkStations
				|| userRequestedWorkStations <= 0) {// min/max workstations
			JOptionPane.showMessageDialog(null, "There is no room with that capacity of workstations.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Room[] bestRoomsInOrder = chooseBestRooms(userRequestedWorkStations, smartBoard, printer, scanner, whiteBoard,
				userRequestedBreakOutSeats);

		for (Room room : bestRoomsInOrder) { // iterate through each room
			Booking booking = createBookingUsingTime(room, user, startDate, endDate, startTime, endTime);
			if (booking != null) {
				JOptionPane.showMessageDialog(null,
						"Room number " + booking.getRoomBooked().getRoomNumber() + " has been booked.",
						"Booking Success", JOptionPane.PLAIN_MESSAGE);

				FileSystem.storeRoom(room);// every time a room has been successfully booked, store room into the file
				FileSystem.storeBooking(booking);// same here
				FileSystem.writeRoomObjects();
				FileSystem.writeBookingObjects();

				return;
			}
		}
		
		JOptionPane.showMessageDialog(null, "There are no rooms available with that capacity of workstations for that time.", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}