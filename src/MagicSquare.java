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
	
	public static int inputDimension() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter odd-numbered dimension for Magic Square: ");
		int dimension = new Integer(input.readLine());
		
		while (dimension%2!=0) {
			System.out.print("Enter correct odd-numbered dimension for Magic Square: ");
			dimension = new Integer(input.readLine());
		}
		
		return dimension;
	}
	
	public String toString() {
		String output = "";
		for (int row=0; row<this.square.length; row++) {
			for (int col=0; col<this.square[row].length; col++) {
				output+=this.square[row][col];
				output+=" ";
			}
			output+="\n";
		}
		return output;
		
	}
	
	public static void main(String[] args) throws IOException{
		MagicSquare square1 = new MagicSquare(MagicSquare.inputDimension());
		
	}

}
