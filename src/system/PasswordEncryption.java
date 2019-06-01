package system;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author Jerry Orr (Edited to use SHA-256 for digital signature generation by David Oh)
 * @see https://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
 */
public abstract class PasswordEncryption {
	/**
	 * Authenticate passwords.
	 *
	 * @param attemptedPassword the attempted password
	 * @param encryptedPassword the encrypted password
	 * @param salt the salt
	 * @return true, if successful
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 */
	public static boolean authenticate(char[] attemptedPassword, byte[] encryptedPassword, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Encrypt the clear-text password using the same salt that was used to
		// encrypt the original password
		byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

		// Authentication succeeds if encrypted password that the user entered
		// is equal to the stored hash
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	/**
	 * Gets/generates the encrypted password.
	 *
	 * @param password the password
	 * @param salt the salt
	 * @return the encrypted password
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 */
	public static byte[] getEncryptedPassword(char[] password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
		// specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
		String algorithm = "PBKDF2WithHmacSHA256";
		// SHA-256 generates 256 bit hashes, so that's what makes sense here
		int derivedKeyLength = 256;
		// Pick an iteration count that works for you. The NIST recommends at
		// least 1,000 iterations:
		// http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
		// iOS 4.x reportedly uses 10,000:
		// http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
		int iterations = 20000;

		KeySpec spec = new PBEKeySpec(password, salt, iterations, derivedKeyLength);

		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		return f.generateSecret(spec).getEncoded();
	}

	/**
	 * Generate salt.
	 *
	 * @return the byte[]
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[8];
		random.nextBytes(salt);

		return salt;
	}
}
