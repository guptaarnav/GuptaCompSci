import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author arnav
 *
 */
public class MagicSquare {
	private int[][] square;
	
	public MagicSquare(int dimension) {
		square = new int[dimension][dimension];
	}
	
	public int inputDimension() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter odd-numbered dimension for Magic Square: ");
		int dimension = new Integer(input.readLine());
		
		while (dimension%2!=0) {
			System.out.print("Enter correct odd-numbered dimension for Magic Square: ");
			dimension = new Integer(input.readLine());
		}
		
		return dimension;
	}
	
	
	public static void main(String[] args) {
		
	}

}
