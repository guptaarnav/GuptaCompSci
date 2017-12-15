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
		
		while (dimension%2==0) {
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
	
	public void populateSquare() {
		int currentRow = 0;
		int currentCol = this.square.length/2;
		
		for (int target = 1; target<=this.square.length*this.square.length; target++) {
			this.insertNumber(target, currentRow, currentCol);
			
			
			
			if(target%this.square.length==0) {
				currentRow++;
			}
			else {
				currentRow--;
				currentCol++;
			}
			if (currentRow<0) {
				currentRow = this.square.length-1;
			}
			if (currentCol>(this.square.length-1)) {
				currentCol = 0;
			}
		}
		
		
	}
	
	private void insertNumber(int target, int row, int col) {
		this.square[row][col] = target;
	}
	
	public static void main(String[] args) throws IOException{
		MagicSquare square1 = new MagicSquare(MagicSquare.inputDimension());
		square1.populateSquare();
		
		System.out.println(square1);
	}
	
	public int checkRows() {
		int[] rowSums = new int[this.square.length];
		for (int row = 0; row < this.square.length; row++) {
			for (int col = 0; col < this.square.length; col++) {
				rowSums[row] += this.square[row][col];
			}
		}
		for (int whichSum = 1; whichSum<rowSums.length; whichSum++) {
			if (rowSums[0]!=rowSums[whichSum]) {
				return -1;
			}
		}
		return rowSums[0];
	}
	
	public int checkCols() {
		int[] colSums = new int[this.square.length];
		for (int col = 0; col < this.square.length; col++) {
			for (int row = 0; row < this.square.length; row++) {
				colSums[col] += this.square[row][col];
			}
		}
		for (int whichSum = 1; whichSum<colSums.length; whichSum++) {
			if (colSums[0]!=colSums[whichSum]) {
				return -1;
			}
		}
		return colSums[0];
	}
}
