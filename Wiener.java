import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Wiener {
	List<BigInteger> d = new ArrayList<BigInteger>();
	List<ContinuedFraction> a = new ArrayList<ContinuedFraction>();
	List<BigInteger> p = new ArrayList<BigInteger>();
	List<BigInteger> q = new ArrayList<BigInteger>();
	ContinuedFraction kdg = new ContinuedFraction(BigInteger.ZERO,
			BigInteger.ONE);

	private BigInteger e;
	private BigInteger n;

	// Find the root of the integer
	public static BigInteger root(BigInteger number) {
		BigInteger b1 = BigInteger.valueOf(0L);
		BigInteger b2 = b1.setBit(2 * number.bitLength());
		do {
			BigInteger b3 = b2.add(b1);
			if (b3.compareTo(number) != 1) {
				number = number.subtract(b3);
				b1 = b3.add(b2);
			}
			b1 = b1.shiftRight(1);
			b2 = b2.shiftRight(1);
		} while (b2.bitCount() != 0);
		return b1;
	}

	public BigInteger attack(BigInteger e, BigInteger n) {
		this.e = e;
		this.n = n;
		int i = 0;
		BigInteger temp1;
		do {
			temp1 = step(i);
			i++;
		} while (temp1 == null);
		return temp1;
	}

	public BigInteger step(int iteration) {
		if (iteration == 0) {
			ContinuedFraction init = new ContinuedFraction(e, n);
			d.add(init.quotient());
			a.add(init.remainder());
			p.add(d.get(0));
			q.add(BigInteger.ONE);

		} else if (iteration == 1) {
			ContinuedFraction temp2 = new ContinuedFraction(
					a.get(0).denominator, a.get(0).numerator);
			d.add(temp2.quotient());
			a.add(temp2.remainder());
			p.add((d.get(0).multiply(d.get(1))).add(BigInteger.ONE));
			q.add(d.get(1));
		}

		else {
			if (a.get(iteration - 1).numerator.equals(BigInteger.ZERO)) {
				return BigInteger.ONE.negate();// Finite continued fraction

				// If it throws an index out of bounds exception once more, I
				// will kill someone
			}
			ContinuedFraction temp3 = new ContinuedFraction(a.get(iteration - 1).denominator,
					a.get(iteration - 1).numerator);
			d.add(temp3.quotient());
			a.add(temp3.remainder());
			p.add((d.get(iteration).multiply(p.get(iteration - 1)).add(p
					.get(iteration - 2))));
			q.add((d.get(iteration).multiply(q.get(iteration - 1)).add(q
					.get(iteration - 2))));

		}

		if (iteration % 2 == 0) {
			if (iteration == 0) {
				kdg = new ContinuedFraction(d.get(0).add(BigInteger.ONE),
						BigInteger.ONE);
			} else {
				// Ok, this is seriously fucked up. Who thought of this?

				kdg = new ContinuedFraction(d.get(iteration)
						.add(BigInteger.ONE).multiply(p.get(iteration - 1))
						.add(p.get(iteration - 2)),
						(d.get(iteration).add(BigInteger.ONE)).multiply(
								q.get(iteration - 1)).add(q.get(iteration - 2)));
			}

		}

		else {
			kdg = new ContinuedFraction(p.get(iteration), q.get(iteration));
		}

		System.out.println("KDG is: " + kdg);

		BigInteger phi = e.multiply(kdg.denominator).subtract(BigInteger.ONE)
				.divide(kdg.numerator);
		BigInteger b = n.subtract(phi).add(BigInteger.ONE);
		BigInteger c = n;
		BigInteger discriminant = b.multiply(b).subtract(
				new BigInteger("4").multiply(c));
		BigInteger x1 = b.negate().add(root(discriminant))
				.divide(new BigInteger("2")).abs();
		BigInteger x2 = b.negate().subtract(root(discriminant))
				.divide(new BigInteger("2")).abs();

		if (x1.multiply(x2).equals(n)) {
			return e.modInverse(phi);
		}

		BigInteger edg = this.e.multiply(kdg.denominator);
		BigInteger bi = (new ContinuedFraction(this.e, kdg)).quotient();
		BigInteger g = edg.mod(kdg.numerator);
		BigDecimal pd = (new BigDecimal(this.n.subtract(bi))).add(
				BigDecimal.ONE).divide(new BigDecimal("2"));
		if (!pd.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
			return null;
		}
		BigInteger pMqD2s = pd.toBigInteger().pow(2).subtract(n);
		BigInteger pMqD2 = root(pMqD2s);
		if (!pMqD2.pow(2).equals(pMqD2s))
			return null;
		BigInteger privateKey = edg.divide(e.multiply(g));
		return privateKey;

	}
}
