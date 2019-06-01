package users;

import java.io.Serializable;

import system.FileSystem;

/**
 * The Class User.
 *
 * @author David
 */
@SuppressWarnings("serial")
public abstract class User implements Serializable{
	/** The user's username*. */
	private String userName;
	
	/**The user's salt associated with the password.**/
	private byte[] salt;
	
	/**The user's encrypted password.**/
	private byte[] encryptedPassword;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param salt the salt
	 * @param encryptedPassword the encrypted password
	 */
	User(String userName, byte[] salt, byte[] encryptedPassword) {
		this.userName = userName;
		this.salt = salt;
		this.encryptedPassword = encryptedPassword;
		
		FileSystem.storeUser(this);
	}
	
	User(byte[] salt, byte[] encryptedPassword) {
		this.salt = salt;
		this.encryptedPassword = encryptedPassword;
		
		FileSystem.storeUser(this);
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the salt.
	 *
	 * @return the salt
	 */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * Sets the salt.
	 *
	 * @param salt the new salt
	 */
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	/**
	 * Gets the encrypted password.
	 *
	 * @return the encrypted password
	 */
	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * Sets the encrypted password.
	 *
	 * @param encryptedPassword the new encrypted password
	 */
	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
}