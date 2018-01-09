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
	
	/**
	 * constructs the a 2d array square
	 * @param dimension
	 */
	public MagicSquare(int dimension) {
		square = new int[dimension][dimension];
	}
	
	/**
	 * error traps input of MagicSquare dimension
	 * @return validated odd dimension between 3 and 20
	 * @throws IOException
	 */
	public static int inputDimension() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter odd-numbered dimension for Magic Square: ");
		int dimension = new Integer(input.readLine());
		
		while (dimension>=20 || dimension<3 || dimension%2==0) {
			System.out.print("Enter correct odd-numbered dimension for Magic Square between 3 and 19 inclusive: ");
			dimension = new Integer(input.readLine());
		}
		
		return dimension;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
	
	
	/**
	 * populates the MagicSquare such that each column and row and diagonal 
	 * sum up to the same number.
	 */
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
	
	/**
	 * inserts target number into square 2d array through array assignment
	 * @param target number to insert
	 * @param row index
	 * @param col index
	 */
	private void insertNumber(int target, int row, int col) {
		this.square[row][col] = target;
	}
	
	
	public static void main(String[] args) throws IOException{
		MagicSquare square1 = new MagicSquare(MagicSquare.inputDimension());
		square1.populateSquare();
		
		System.out.println(square1);
	}
}

/*
 * 
 * 
Enter odd-numbered dimension for Magic Square: 5
17 24 1 8 15 
23 5 7 14 16 
4 6 13 20 22 
10 12 19 21 3 
11 18 25 2 9 

 Enter odd-numbered dimension for Magic Square: 19
192 213 234 255 276 297 318 339 360 1 22 43 64 85 106 127 148 169 190 
212 233 254 275 296 317 338 359 19 21 42 63 84 105 126 147 168 189 191 
232 253 274 295 316 337 358 18 20 41 62 83 104 125 146 167 188 209 211 
252 273 294 315 336 357 17 38 40 61 82 103 124 145 166 187 208 210 231 
272 293 314 335 356 16 37 39 60 81 102 123 144 165 186 207 228 230 251 
292 313 334 355 15 36 57 59 80 101 122 143 164 185 206 227 229 250 271 
312 333 354 14 35 56 58 79 100 121 142 163 184 205 226 247 249 270 291 
332 353 13 34 55 76 78 99 120 141 162 183 204 225 246 248 269 290 311 
352 12 33 54 75 77 98 119 140 161 182 203 224 245 266 268 289 310 331 
11 32 53 74 95 97 118 139 160 181 202 223 244 265 267 288 309 330 351 
31 52 73 94 96 117 138 159 180 201 222 243 264 285 287 308 329 350 10 
51 72 93 114 116 137 158 179 200 221 242 263 284 286 307 328 349 9 30 
71 92 113 115 136 157 178 199 220 241 262 283 304 306 327 348 8 29 50 
91 112 133 135 156 177 198 219 240 261 282 303 305 326 347 7 28 49 70 
111 132 134 155 176 197 218 239 260 281 302 323 325 346 6 27 48 69 90 
131 152 154 175 196 217 238 259 280 301 322 324 345 5 26 47 68 89 110 
151 153 174 195 216 237 258 279 300 321 342 344 4 25 46 67 88 109 130 
171 173 194 215 236 257 278 299 320 341 343 3 24 45 66 87 108 129 150 
172 193 214 235 256 277 298 319 340 361 2 23 44 65 86 107 128 149 170 

*/
