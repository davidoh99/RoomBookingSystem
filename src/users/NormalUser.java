package users;

/**
 * @author David
 *
 */
@SuppressWarnings("serial")
public class NormalUser extends User{
	public NormalUser(String userName, byte[] salt, byte[] encryptedPassword) {
		super(userName, salt, encryptedPassword);
	}
}