import java.util.Scanner;

public class problem03 {
	/*RubiksMatrix
	https://judge.softuni.bg/Contests/Practice/DownloadResource/1051
	*/
	static int r;
	static int c;
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String[] dimensions = scn.nextLine().split(" ");
		r = Integer.parseInt(dimensions[0]);
		c = Integer.parseInt(dimensions[1]);
		int [][] cube = new int[r][c];
		cube = initializeCube(cube);
		int n = Integer.parseInt(scn.nextLine());
		
		for (int i=0; i<n; i++) {
			String[] command = scn.nextLine().split(" ");
			switch(command[1]){
			case "left": moveLeft(cube,command); break;
			case "right": moveRight(cube,command); break;
			case "up": moveUp(cube,command); break;
			case "down": moveDown(cube,command); break;
			}
		}
		int[][] original = new  int[r][c];
		original = initializeCube(original);
		for (int row =0; row<r; row++){
			for (int col = 0; col<c; col++){
				if (original[row][col] == cube[row][col]){
					System.out.println("No swap required");
				}
				else {
					int[] swapSource= findSwap(cube,original[row][col]);
					System.out.printf("Swap (%d, %d) with (%d, %d)\n", 
							row,
							col,
							swapSource[0],
							swapSource[1]);
					swap(cube,swapSource,new int[]{row,col});
				}
			}
		}
	}
	private static int[][] swap(int[][] cube, int[] source, int[] destination){
		int tmp = cube[destination[0]][destination[1]];
		cube[destination[0]][destination[1]] = cube[source[0]][source[1]];
		cube[source[0]][source[1]] = tmp;
		return cube;
	}
	private static int[] findSwap(int[][] cube, int key){
		for (int row =0; row<r; row++){
			for (int col = 0; col<c; col++){
				if (cube[row][col] == key){
					return new int[]{row,col};
				}
			}
		}
		return new int[]{-1,-1};
	}
	private static void moveRight(int[][] cube,String[] command){
		long moves = Long.parseLong(command[2]);
		moves %=c;
		int row = Integer.parseInt(command[0]);
		for (long i =0; i<moves; i++) {
			int tmp = cube[row][c-1];
			for (int col=c-1; col>0; col--){
				cube [row][col] = cube[row][col-1];
			}
			cube[row][0] = tmp;
		}
	}

	private static void moveLeft(int[][] cube,String[] command){
		long moves = Long.parseLong(command[2]);
		moves %=c;
		int row = Integer.parseInt(command[0]);
		for (long i =0; i<moves; i++) {
			int tmp = cube[row][0];
			for (int col=0; col<c-1; col++){
				cube [row][col] = cube[row][col+1];
			}
			cube[row][c-1] = tmp;
		}
	}
	
	private static void moveDown(int[][] cube,String[] command){
		long moves = Long.parseLong(command[2]);
		moves %=r;
		int col = Integer.parseInt(command[0]);
		for (long i =0; i<moves; i++) {
			int tmp = cube[r-1][col];
			for (int row=r-1; row>0; row--){
				cube [row][col] = cube[row-1][col];
			}
			cube[0][col] = tmp;
		}
	}
	
	private static void moveUp(int[][] cube,String[] command){
		long moves = Long.parseLong(command[2]);
		moves %=r;
		int col = Integer.parseInt(command[0]);
		for (long i =0; i<moves; i++) {
			int tmp = cube[0][col];
			for (int row=0; row<r-1; row++){
				cube [row][col] = cube[row+1][col];
			}
			cube[r-1][col] = tmp;
		}
	}

	private static int[][] initializeCube(int[][] cube){
		int count = 1;
		for (int row=0; row<r; row++) {
			for (int col=0; col<c; col++){
				cube[row][col] = count;
				count++;
			}
		}
		
		return cube;
	}
}
