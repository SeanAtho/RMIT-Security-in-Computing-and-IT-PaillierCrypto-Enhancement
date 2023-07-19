import java.math.BigInteger;
import java.util.Scanner;
public class PaillierEncryption {
	public static void main(String[] args) {
		// Declare required parameters as BigInteger objects
		BigInteger m; // Input Message
		BigInteger p; // 1st prime number
		BigInteger q; // 2nd prime number
		BigInteger n; // Key parameter "n"
		BigInteger g; // Chosen Integer
		BigInteger lambda;// Private Key parameter
		BigInteger u;
		BigInteger k;
		BigInteger meu; // Private Key parameter
		BigInteger r; // Random Integer
		BigInteger C; // Encrypted Message
		BigInteger M; // Decrypted Message
		// Take required parameter as Input
		Scanner input = new Scanner(System.in); // initialize Scanner object for taking input
		System.out.println("Welcome to Paillier Encryption Program !!!");
		System.out.println("BOB generates Public and Private Key.");
		System.out.println("Please provide the following information as Integer !!!");
		System.out.print("Enter the value of 'p' (as Integer): ");
		String p_str = input.next();
		System.out.print("Enter the value of 'q' (as Integer): ");
		String q_str = input.next();
		System.out.print("Enter the value of 'g' (as Integer): ");
		String g_str = input.next();
		// assign values to bi1, bi2
		p = new BigInteger(p_str);
		q = new BigInteger(q_str);
		g = new BigInteger(g_str);
		System.out.println();
		System.out.println("Here is the detail Solution: ");
		n = p.multiply(q);
		System.out.println("The value of 'n' is: "+n);
		System.out.println("The Public-Key (n,g) := ("+n+", "+g+") is sent to ALICE.");
		System.out.println("BOB computes Private-Key parameters (Lambda, Meu) ");
		BigInteger p_minus_1 = p.subtract(new BigInteger("1"));
		BigInteger q_minus_1 = q.subtract(new BigInteger("1"));
		lambda = p_minus_1.multiply(q_minus_1.divide(p_minus_1.gcd(q_minus_1)));
		System.out.println("The value of 'Lambda' is: "+lambda);
		u = g.modPow(lambda, n.multiply(n));
		BigInteger L_of_u = u.subtract(new BigInteger("1")).divide(n);
		k = L_of_u;
		System.out.println("The value of k is := "+k);
		meu = k.modInverse(n);
		System.out.println("The value of 'Meu' is := "+meu);
		System.out.println("BOB's Private-Key (Lambda,Meu) := ("+lambda+", "+meu+")");
		System.out.println("ALICE Encrypts the Message, m ");
		System.out.print("Enter the message (as Integer) to encrypt, m := ");
		String m_str = input.next();
		m = new BigInteger(m_str);
		System.out.print("Enter the value of 'r' (as Integer): ");
		String r_str = input.next();
		r = new BigInteger(r_str);
		BigInteger temp1 = g.modPow(m, n.multiply(n));
		BigInteger temp2 = r.modPow(n, n.multiply(n));
		C = temp1.multiply(temp2).mod(n.multiply(n));
		System.out.println("Ciphertext, C := "+C);
		System.out.println("BOB decrypts the Original Message.");
		BigInteger u1 = C.modPow(lambda, n.multiply(n));
		BigInteger L_of_u1 = u1.subtract(new BigInteger("1")).divide(n);
		M = L_of_u1.multiply(meu).mod(n);
		System.out.println("Extracted Message, M := "+ M);
	}
}
