import java.util.concurrent.*;

public class ParMatSum {
	private int matrix[][]=
		{
				{ 1, 2, 3, 4, 5} ,
				{ 2, 2, 2, 2, 2 } ,
				{ 3, 3, 3, 3, 3 } ,
				{ 4, 4, 4, 4, 3 } ,
				{ 5, 5, 5, 5, 5 } } ;
	public int results[];
	final int rows=matrix.length;    // number of rows
	final int cols=matrix[0].length; // number of columns
	void printMat() {
		for(int i=0; i<rows; i++){
			System.out.print("[");
			for(int j=0; j<cols; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("]");
		}
	}
	private void exec() {
		printMat();
		results=new int[rows];
		CyclicBarrier bar=new CyclicBarrier(rows, new ResultUser(results));
		for(int i=0; i<rows; i++) {
			new RowSummer(i, matrix[i], results, bar).start();
		}
	}
	public static void main(String args[]) {
		new ParMatSum().exec();
	}
}