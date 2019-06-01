package users;

import users.Administrator;
import users.User;
import system.FileSystem;

/**
 * @author David
 *
 */
@SuppressWarnings("serial")
public class Administrator extends User{	
	public Administrator(byte[] salt, byte[] encryptedPassword) {
		super(salt, encryptedPassword);
		User user = new Administrator("default", salt, encryptedPassword);
		FileSystem.storeUser(user);
		FileSystem.writeUserObjects();
	}
	
	public Administrator(String userName, byte[] salt, byte[] encryptedPassword) {
		super(userName, salt, encryptedPassword);
	}
}
