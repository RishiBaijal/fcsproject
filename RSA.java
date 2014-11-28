//Based on http://en.wikipedia.org/wiki/RSA_%28cryptosystem%29
import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class RSA {
    public static BigInteger e, d, n, phi, message=null; //This is a shitty assumption to make. Think of a way around this.
    private int bits;
    RSA(int bits)
    {
        e=new BigInteger("65537");
        this.bits=bits;
        Random random=new Random();
        BigInteger p=new BigInteger(bits, 48, random);
        BigInteger q=new BigInteger(bits, 48,random);
        n=p.multiply(q);
        phi=n.subtract(p).subtract(q).add(BigInteger.valueOf(1));
    }
    
    public BigInteger generatePrime()
    {
        BigInteger prime=null;
        boolean found=false;
        Random random=new Random();
        while (found==false)
        {
            BigInteger rand;
            rand = new BigInteger(bits, 48, random);
            if (rand.compareTo(n) < 0)
            {
                prime=rand;
                found=true;
            }
        }
        return prime;
    }
    
    public BigInteger encrypt(BigInteger current)
    {
        return current.modPow(e, n);
    }
    
//    public BigInteger decrypt(BigInteger cipher)
//    {
//        BigInteger currentD=decryptionExponent(this.e, n);
//        return cipher.modPow(currentD, n);
//    }
    
    public BigInteger decrypt(BigInteger cipher, BigInteger d, BigInteger mod) 
    {
        BigInteger message = cipher.modPow(d,mod);
        return message;
    }

    
    public BigInteger decryptionExponent(BigInteger e, BigInteger mod)// this can be replaced by n
    {
        BigInteger numerator=BigInteger.valueOf(1);
        BigInteger decr=null;
        int multiple=0;
        boolean found=false;
        while (found==false)
        {
            numerator=BigInteger.valueOf(1).add(mod.multiply(BigInteger.valueOf(multiple)));
            if (numerator.mod(e).equals(BigInteger.valueOf(0)))
            {
                decr=numerator.divide(e);
                found=true;
            }
            else
            {
                multiple++;
            }
        }
        d=decr;
        return decr;
    }
    public static void main(String args[])
    {
        RSA obj=new RSA(48);
        BigInteger encryptTest=BigInteger.valueOf(65537);
        System.out.println("Executing Tests.");
        BigInteger a=obj.encrypt(encryptTest);
        System.out.println(a);
        //System.out.println("Decrypted value is :" +obj.decrypt(a));
    }
    
    
    
}
