import java.math.BigDecimal;
import java.math.BigInteger;

public class ContinuedFraction {
	public BigInteger numerator;
	public BigInteger denominator;

	/*
	 * This initializes the numerator and the denominator
	 */
	public ContinuedFraction(BigInteger numerator, BigInteger denominator) {

		BigInteger temp = numerator.gcd(denominator);
		this.numerator = numerator.divide(temp);
		this.denominator = denominator.divide(temp);
		//
		// this.numerator=numerator;
		// this.denominator=denominator;
	}

	public ContinuedFraction(BigInteger firstParam, ContinuedFraction fraction) {
		this.numerator = firstParam.multiply(fraction.denominator);
		this.denominator = fraction.numerator;
		BigInteger temp = this.numerator.gcd(this.denominator);
		this.numerator = this.numerator.divide(temp);
		this.denominator = this.denominator.divide(temp);
	}

	public BigInteger quotient() {
		BigDecimal d1 = new BigDecimal(this.numerator);
		BigDecimal d2 = new BigDecimal(this.denominator);
		return d1.divide(d2).toBigInteger();
	}

	public ContinuedFraction remainder() {
		BigInteger quotient = this.quotient();// Finds the quotient for the
												// current fraction
		BigInteger newNumer = this.numerator.subtract(quotient
				.multiply(this.denominator));
		BigInteger newDenom = this.denominator;
		return new ContinuedFraction(newNumer, newDenom);

	}

	public String toString() {
		return numerator + "/" + denominator;
	}

}
