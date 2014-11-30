//Based on http://en.wikipedia.org/wiki/RSA_%28cryptosystem%29
import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class RSA {

	private final static SecureRandom random = new SecureRandom();

	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger modulus;

	private final static BigInteger one = new BigInteger("1");

	RSA(int bits) {
		BigInteger p = new BigInteger("239");
		BigInteger q = new BigInteger("379");
		modulus = p.multiply(q);
		BigInteger phi = modulus.subtract(p).subtract(q)
				.add(BigInteger.valueOf(1));
		publicKey = new BigInteger("17993");
		privateKey = publicKey.modInverse(phi);

	}

	// public static BigInteger e, d, n, phi, message = null;
	// private int bits;

	public BigInteger getPublicKey() {
		return publicKey;
	}

	public BigInteger getModulus() {
		return modulus;
	}

	public BigInteger encrypt(BigInteger message) {
		return message.modPow(publicKey, modulus);
	}

	public BigInteger decrypt(BigInteger message) {
		return message.modPow(privateKey, modulus);
	}

	public String toString() {
		return "Public: " + publicKey + " Private: " + privateKey
				+ " moddded with " + modulus;

	}

	public static void main(String args[]) {
		RSA rsa = new RSA(5);
		System.out.println("Key: " + rsa);

		String s = "Te";
		byte bytes[] = s.getBytes();
		BigInteger message = new BigInteger(bytes);
		BigInteger encrypt = rsa.encrypt(message);
		BigInteger decrypt = rsa.decrypt(encrypt);
		System.out.println("---WITHOUT IMPLEMENTING ATTACK---");

		System.out.println("Message: " + s);
		System.out.println("The encrypted message is : "
				+ new String(encrypt.toByteArray()));
		System.out.println("The decrypted message is : "
				+ new String(decrypt.toByteArray()));

		System.out.println("---WITH THE ATTACK---");
		Wiener wiener = new Wiener();
		BigInteger privateKey = wiener.attack(rsa.getPublicKey(),
				rsa.getModulus());
		if (privateKey.equals(BigInteger.ONE.negate())) {
			System.out.println("Unable to recover private key");
		} else {
			System.out.println("Private key is " + privateKey.toString());
		}
	}
	/*
	 * The portion that is commented out is the correct version of RSA. What I
	 * just wrote is a vulnerable version
	 */
	// RSA(int bits)
	// {
	// e=new BigInteger("65537");
	// this.bits=bits;
	// Random random=new Random();
	// BigInteger p=new BigInteger(bits, 48, random);
	// BigInteger q=new BigInteger(bits, 48,random);
	// n=p.multiply(q);
	// phi=n.subtract(p).subtract(q).add(BigInteger.valueOf(1));
	// }
	//
	// public BigInteger generatePrime()
	// {
	// BigInteger prime=null;
	// boolean found=false;
	// Random random=new Random();
	// while (found==false)
	// {
	// BigInteger rand;
	// rand = new BigInteger(bits, 48, random);
	// if (rand.compareTo(n) < 0)
	// {
	// prime=rand;
	// found=true;
	// }
	// }
	// return prime;
	// }
	//
	// public BigInteger encrypt(BigInteger current)
	// {
	// return current.modPow(e, n);
	// }
	//
	// // public BigInteger decrypt(BigInteger cipher)
	// // {
	// // BigInteger currentD=decryptionExponent(this.e, n);
	// // return cipher.modPow(currentD, n);
	// // }
	//
	// public BigInteger decrypt(BigInteger cipher, BigInteger d, BigInteger
	// mod)
	// {
	// BigInteger message = cipher.modPow(d,mod);
	// return message;
	// }
	//
	//
	// public BigInteger decryptionExponent(BigInteger e, BigInteger mod)// this
	// can be replaced by n
	// {
	// BigInteger numerator=BigInteger.valueOf(1);
	// BigInteger decr=null;
	// int multiple=0;
	// boolean found=false;
	// while (found==false)
	// {
	// numerator=BigInteger.valueOf(1).add(mod.multiply(BigInteger.valueOf(multiple)));
	// if (numerator.mod(e).equals(BigInteger.valueOf(0)))
	// {
	// decr=numerator.divide(e);
	// found=true;
	// }
	// else
	// {
	// multiple++;
	// }
	// }
	// d=decr;
	// return decr;
	// }
	// public static void main(String args[])
	// {
	// RSA obj=new RSA(48);
	// BigInteger encryptTest=BigInteger.valueOf(65537);
	// System.out.println("Executing Tests.");
	// BigInteger a=obj.encrypt(encryptTest);
	// System.out.println(a);
	// //System.out.println("Decrypted value is :" +obj.decrypt(a));
	// }
	//
	//

}
