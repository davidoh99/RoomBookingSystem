package system;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import rooms.Room;
import users.Administrator;
import users.NormalUser;
import users.User;

/**
 * The Class Booking.
 */
@SuppressWarnings("serial")
public class Booking implements Serializable {

	/** The room booked. */
	private Room roomBooked;

	/** The start and end dates. */
	private LocalDate startDate, endDate;

	/** The start and end time. */
	private LocalTime startTime, endTime;

	/** The user. */
	private User user;

	/**
	 * Instantiates a new booking.
	 *
	 * @param room
	 *            the room
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @param user
	 *            the user
	 */
	public Booking(Room room, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime,
			User user) {
		this.roomBooked = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
	}

	/**
	 * Gets the room booked.
	 *
	 * @return the room booked
	 */
	public Room getRoomBooked() {
		return roomBooked;
	}

	/**
	 * Sets the room booked.
	 *
	 * @param roomBooked
	 *            the new room booked
	 */
	public void setRoomBooked(Room roomBooked) {
		this.roomBooked = roomBooked;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the guest.
	 *
	 * @return the guest
	 */
	public User getGuest() {
		return user;
	}

	/**
	 * Gets the guest. OVERLOADED METHOD
	 *
	 * @param guest
	 *            the new guest
	 */
	public User getGuest(Administrator guest) {
		return guest;
	}
	
	/**
	 * Gets the guest. OVERLOADED METHOD
	 *
	 * @param guest
	 *            the new guest
	 */
	public User getGuest(NormalUser guest) {
		return guest;
	}
	
	/**
	 * Sets the guest.
	 *
	 * @param guest
	 *            the new guest
	 */
	public void setGuest(User guest) {
		this.user = guest;
	}

}
