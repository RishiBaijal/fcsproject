import java.io.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AdvancedEncryptionStandard {


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
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			desKey = keygen.generateKey();
			String param = "AES/" + mode + "/" + "/" + padding;
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
		//	byte decryptedText[] = cipher.doFinal(encryptedText);

		//	System.out.println("Decrypted text: " + new String(decryptedText));

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


			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	
	
	
	
	//private static byte ivbytes[];
//	public static String generateSent() {
//		SecureRandom random = new SecureRandom();
//		byte bytes[] = new byte[20];
//		random.nextBytes(bytes);
//		String s = new String(bytes);
//		return s;
//
//	}

//	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
//		// TODO Auto-generated method stub
//		BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
//		String s, password, sent, mode="CBC", padding="PKCS5Padding", temp1;
//		int passIter=65536, keySize=128;// For a key size more than 128, I get a really shitty error
//		
//		System.out.println("Enter the text to be encrypted: ");
//		s=obj.readLine();
//		System.out.println("Enter the password. The specs will be based on this.");
//		password=obj.readLine();
//		System.out.println("Enter the mode of encryption: (Default is CBC)");
//		temp1=obj.readLine();
//		if (!temp1.equalsIgnoreCase(mode))
//		{
//			mode=temp1;
//		}
//		System.out.println("Enter the padding type: (Default is PKCS5Padding)");
//		temp1=obj.readLine();
//		if (!temp1.equalsIgnoreCase(padding))
//		{
//			padding=temp1;
//		}
//		sent=generateSent();
//		byte bytes[];
//		byte sentBytes[]=sent.getBytes();
//		// Derive the key based on the password entered by the user
//		SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		PBEKeySpec spec=new PBEKeySpec(password.toCharArray(), sentBytes, passIter, keySize);
//		SecretKey secretkey=factory.generateSecret(spec);
//		String params="AES" + "/" + mode + "/" + padding;
//		SecretKeySpec secret = new SecretKeySpec(secretkey.getEncoded(), "AES" );
//		
//		
//		
//		//Key derivation ends here
//		//Now we need to encrypt the damned message
//		
//		Cipher cipher=Cipher.getInstance(params);
//		cipher.init(Cipher.ENCRYPT_MODE, secret);
//		AlgorithmParameters parameters=cipher.getParameters();
//		ivbytes=parameters.getParameterSpec(IvParameterSpec.class).getIV();
//		byte encryptedText[]=cipher.doFinal(s.getBytes("UTF-8"));
//		Base64 base=new Base64();
//		System.out.println("The encrypted text is as follows: " + base.encodeAsString(encryptedText));
//		
//		sentBytes=sent.getBytes("UTF-8");
//		cipher=Cipher.getInstance(params);
//		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivbytes));
//		byte decryptedText[]=cipher.doFinal(encryptedText);
//		System.out.println("The decrypted text is as follows: "+ base.encodeAsString(decryptedText));
//		//encryptAndDecrypt(s, password, sent, mode, padding, passIter, keySize);
//	}
//	public static void encryptAndDecrypt(String s, String password, String sent, String mode, String padding, int passIter, int keySize) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
//	{
//		byte bytes[];
//		byte sentBytes[]=sent.getBytes();
//		// Derive the key based on the password entered by the user
//		SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		PBEKeySpec spec=new PBEKeySpec(password.toCharArray(), sentBytes, passIter, keySize);
//		SecretKey secretkey=factory.generateSecret(spec);
//		String params="AES" + "/" + mode + "/" + padding;
//		SecretKeySpec secret = new SecretKeySpec(secretkey.getEncoded(), "AES" );
//		
//		
//		
//		//Key derivation ends here
//		//Now we need to encrypt the damned message
//		
//		Cipher cipher=Cipher.getInstance(params);
//		cipher.init(Cipher.ENCRYPT_MODE, secret);
//		AlgorithmParameters parameters=cipher.getParameters();
//		bytes=parameters.getParameterSpec(IvParameterSpec.class).getIV();
//		byte encryptedText[]=cipher.doFinal(s.getBytes("UTF-8"));
//		Base64 base=new Base64();
//		System.out.println("The encrypted text is as follows: " + base.encodeAsString(encryptedText));
//		
//		
//
//	}
