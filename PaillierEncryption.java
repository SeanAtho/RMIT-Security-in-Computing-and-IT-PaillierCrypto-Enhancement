import java.math.BigInteger;
import java.util.Scanner;

/**
 * This class implements the Paillier Cryptosystem.
 */
public class PaillierEncryption {
    
    /**
     * This method calculates 'n' using 'p' and 'q'.
     */
    private static BigInteger calculateN(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }

    /**
     * This method calculates 'lambda' using 'p' and 'q'.
     */
    private static BigInteger calculateLambda(BigInteger p, BigInteger q) {
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger qMinusOne = q.subtract(BigInteger.ONE);
        return pMinusOne.multiply(qMinusOne.divide(pMinusOne.gcd(qMinusOne)));
    }

    /**
     * This method calculates 'mu' using 'g', 'lambda', and 'n'.
     */
    private static BigInteger calculateMu(BigInteger g, BigInteger lambda, BigInteger n) {
        BigInteger u = g.modPow(lambda, n.multiply(n));
        BigInteger lOfU = u.subtract(BigInteger.ONE).divide(n);
        return lOfU.modInverse(n);
    }

    /**
     * This method encrypts a message 'm' using 'r', 'g', and 'n'.
     */
    private static BigInteger encryptMessage(BigInteger m, BigInteger r, BigInteger g, BigInteger n) {
        BigInteger nSquared = n.multiply(n);
        BigInteger temp1 = g.modPow(m, nSquared);
        BigInteger temp2 = r.modPow(n, nSquared);
        return temp1.multiply(temp2).mod(nSquared);
    }

    /**
     * This method decrypts a message 'c' using 'lambda', 'mu', and 'n'.
     */
    private static BigInteger decryptMessage(BigInteger c, BigInteger lambda, BigInteger mu, BigInteger n) {
        BigInteger nSquared = n.multiply(n);
        BigInteger u = c.modPow(lambda, nSquared);
        BigInteger lOfU = u.subtract(BigInteger.ONE).divide(n);
        return lOfU.multiply(mu).mod(n);
    }

    /**
     * The main method where the program starts execution.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        System.out.println("Welcome to Paillier Encryption Program !!!");

        System.out.print("Enter the value of 'p' (as Integer): ");
        BigInteger p = new BigInteger(input.next());

        System.out.print("Enter the value of 'q' (as Integer): ");
        BigInteger q = new BigInteger(input.next());

        System.out.print("Enter the value of 'g' (as Integer): ");
        BigInteger g = new BigInteger(input.next());

        BigInteger n = calculateN(p, q);
        BigInteger lambda = calculateLambda(p, q);
        BigInteger mu = calculateMu(g, lambda, n);

        System.out.print("Enter the message (as Integer) to encrypt, m := ");
        BigInteger m = new BigInteger(input.next());

        System.out.print("Enter the value of 'r' (as Integer): ");
        BigInteger r = new BigInteger(input.next());

        BigInteger c = encryptMessage(m, r, g, n);
        BigInteger decryptedMessage = decryptMessage(c, lambda, mu, n);

        System.out.println("Decrypted Message, M := " + decryptedMessage);
    }
}
