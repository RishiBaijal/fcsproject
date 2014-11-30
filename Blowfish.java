import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import java.io.*;
public class Blowfish {

	// Cipher cipher;
	// SecretKey desKey;
	//
	// DataEncryptStandard() throws NoSuchAlgorithmException,
	// NoSuchPaddingException {
	// cipher = null;
	// desKey = null;
	// }

	// public static SecretKey generateKey() throws NoSuchAlgorithmException {
	// // Create a DES key
	// KeyGenerator keygen = KeyGenerator.getInstance("DES");
	// SecretKey desKey = keygen.generateKey();
	// return desKey;
	// }
	//
	// public static Cipher getCipher() throws NoSuchAlgorithmException,
	// NoSuchPaddingException {
	// cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
	// return cipher;
	// }

	// public static byte[] encrypt(String s) throws NoSuchAlgorithmException,
	// InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
	// NoSuchPaddingException {
	// byte text[] = s.getBytes();
	// desKey=generateKey();
	// cipher=getCipher();
	// System.out.println(desKey);
	// cipher.init(Cipher.ENCRYPT_MODE, desKey);
	// byte encryptedText[] = cipher.doFinal(text);
	// return encryptedText;
	// }
	//
	// public static byte[] decrypt(String s) throws NoSuchAlgorithmException,
	// InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	// byte text[] = s.getBytes();
	// //SecretKey desKey=generateKey();
	// cipher.init(Cipher.DECRYPT_MODE, desKey);
	// byte encryptedText[] = cipher.doFinal(text);
	// return encryptedText;
	// }
	public static void encryptAndDecrypt(String s, String mode, String padding)
			throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchPaddingException {
		Cipher cipher;
		SecretKey desKey;
		KeyGenerator keygen = KeyGenerator.getInstance("Blowfish");
		desKey = keygen.generateKey();
		String param = "Blowfish/" + mode + "/" + "/" + padding;
		cipher = Cipher.getInstance(param);// "DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		byte text[] = s.getBytes();
		// System.out.println("Text [Byte Format] : " + text);
		// System.out.println("Text : " + new String(text));
		// desKey=generateKey();
		// cipher=getCipher();
		//System.out.println(desKey);

		byte encryptedText[] = cipher.doFinal(text);
		// return encryptedText;
		System.out.println("Encrypted text: " + encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, desKey);
		byte decryptedText[] = cipher.doFinal(encryptedText);

		System.out.println("Decrypted text: " + new String(decryptedText));

	}

	public static void main(String args[]) throws Exception {
		try {

			// Create the cipher. Here, we have hard-coded the DES mode. We can
			// allow user input later
			String mode = "ECB", padding = "PKCS5Padding";
			//Cipher cipher;
			BufferedReader obj = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Enter the string that you want to encrypt: ");
			String s = obj.readLine();
			System.out.println("Enter the mode: (Default mode is ECB)");
			String temp1 = obj.readLine();
			if (!temp1.equalsIgnoreCase(mode)) {
				mode = temp1;
			}
			System.out
					.println("Enter the padding type: (Default is PKCS5Padding)");
			temp1 = obj.readLine();
			if (!temp1.equalsIgnoreCase(padding)) {
				padding = temp1;
			}
			encryptAndDecrypt(s, mode, padding);
			// KeyGenerator keygen = KeyGenerator.getInstance("DES");
			// SecretKey desKey = keygen.generateKey();
			// cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// cipher.init(Cipher.ENCRYPT_MODE, desKey);
			// byte text[] = s.getBytes();
			// System.out.println("Text [Byte Format] : " + text);
			// System.out.println("Text : " + new String(text));
			// // desKey=generateKey();
			// // cipher=getCipher();
			// byte encryptedText[] = cipher.doFinal(text);
			// // return encryptedText;
			// System.out.println("Encrypted text: " + encryptedText);
			// cipher.init(Cipher.DECRYPT_MODE, desKey);
			// byte decryptedText[] = cipher.doFinal(encryptedText);
			//
			// System.out.println("Decrypted text: " + new
			// String(decryptedText));
			// encryptAndDecrypt(s);
			// byte encryptedText[]=encrypt(s);
			// System.out.println("The encrypted text is as follows: "
			// + encryptedText);
			// byte decryptedText[]=decrypt(s);
			// System.out.println("The encrypted text is as follows: "
			// + decryptedText);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
