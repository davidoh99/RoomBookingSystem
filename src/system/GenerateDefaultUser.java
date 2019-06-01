package system;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import users.Administrator;
import users.User;

/**
 * The Class GenerateDefaultUser. This class should be thrown away before the project is exported into a JAR file for security.
 *
 * @author David Oh
 * Class which requires the Administrator class (in users package) to run
 */
public class GenerateDefaultUser {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (FileSystem.users.get("default") == null) {
			byte[] salt = {-99, 4, -2, 34, -32, 62, -50, -57};
			String password = "hqJCThsJx7Kv7xLB";
			byte[] encryptedPassword = PasswordEncryption.getEncryptedPassword(password.toCharArray(), salt);
			new Administrator(salt, encryptedPassword);
			System.out.println("Success");
		}
		else {
			System.out.println("There is already a default administrator user.");
			return;
		}
	}
}
