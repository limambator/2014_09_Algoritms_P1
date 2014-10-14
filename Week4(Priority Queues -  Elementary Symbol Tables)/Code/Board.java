import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
	private final int N;
	private final int[][] blocks;

	public Board(int[][] blocks) // construct a board from an N-by-N array of
									// blocks
									// (where blocks[i][j] = block in row i,
									// column j)
	{
		this.blocks = copyBlocks(blocks);
		this.N = blocks.length;
		// this.manhattan = internalManhattan();
	}

	private int[][] copyBlocks(int[][] a) {
		int length = a.length;
		int[][] copy = new int[length][length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(a[i], 0, copy[i], 0, length);
		}
		return copy;
	}

	public int dimension() // board dimension N
	{
		return N;
	}

	public int hamming() // number of blocks out of place
	{
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int actualValue = blocks[i][j];
				int expectedValue = (i * N + j + 1) % (N * N);
				if (actualValue > 0 && actualValue != expectedValue)
					cnt++;
			}
		}
		return cnt;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int actualValue = blocks[i][j];
				int expectedValue = (i * N + j + 1) % (N * N);
				if (actualValue > 0 && actualValue != expectedValue) {
					int jact = (actualValue - 1) % (N);
					int iact = (actualValue - 1) / (N);
					int dist = Math.abs(i - iact) + Math.abs(j - jact);
					cnt += dist;
				}

			}
		}
		return cnt;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return this.hamming() == 0;
	}

	// public Board twin() // a board obtained by exchanging two adjacent blocks
	// in the same row
	public Board twin() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				int actualValue = blocks[i][j];
				int actualValue1 = blocks[i][j + 1];
				if (actualValue > 0 && actualValue1 > 0) {
					return twinBoard(this, i, j, i, j + 1);
					// blocks1 = null;
				}
			}
		}
		return null;
	}

	/*
	 * public Board twin() { for (int i = 0; i < N; i++) { for (int j = 0; j < N
	 * - 1; j++) { if (blocks[i][j] > 0 && blocks[i][j + 1] > 0) { return
	 * twinBoard(this, i, j, i, j + 1); } } } return null; }
	 */
	private Board twinBoard(Board board, int i1, int j1, int i2, int j2) {
		int[][] twin = copyBlocks(board.blocks);
		exch(twin, i1, j1, i2, j2);
		return new Board(twin);
	}

	// exchange a[i] and a[j]
	private static void exch(int[][] a, int i, int j, int i1, int j1) {
		int swap = a[i][j];
		a[i][j] = a[i1][j1];
		a[i1][j1] = swap;
	}

	public boolean equals(Object y) // does this board equal y?
	{
		if (y == this)
			return true;
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;
		Board that = (Board) y;
		return Arrays.deepEquals(this.blocks, that.blocks);
	}

	// // all neighboring boards
	public Iterable<Board> neighbors() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] == 0) {
					Queue<Board> neighbors = new Queue<Board>();
					if (i > 0) { // Top neighbor
						neighbors.enqueue(twinBoard(this, i, j, i - 1, j));
					}
					if (i < N - 1) { // Bottom neighbor
						neighbors.enqueue(twinBoard(this, i, j, i + 1, j));
					}
					if (j > 0) { // Left neighbor
						neighbors.enqueue(twinBoard(this, i, j, i, j - 1));
					}
					if (j < N - 1) { // Right neighbor
						neighbors.enqueue(twinBoard(this, i, j, i, j + 1));
					}
					return neighbors;
				}
			}
		}
		return null;
	}

	// string representation of the board (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {

		/*
		 * String filename = args[0]; In in = new In(filename); int N =
		 * in.readInt(); Point[] points = new Point[N];
		 * 
		 * for (int i = 0; i < N; i++) { int x = in.readInt(); int y =
		 * in.readInt(); points[i] = new Point(x, y);
		 * 
		 * }
		 */

		File file = new File("puzzle04.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			int N = sc.nextInt();
			int a[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					a[i][j] = sc.nextInt();
			}

			Board Br = new Board(a);
			StdOut.println("manhattan " + Br.manhattan());
			// StdOut.println(Br.toString());
			StdOut.println(Br.twin().toString());
			StdOut.println(Br.twin().twin().toString());
			StdOut.println("neighbors");
			for (Board s : Br.neighbors())
	            StdOut.println(s.toString());
			
			// StdOut.println();
			// findCollinearPoints(points);
			/*
			 * while(sc.hasNextLine()){ System.out.println(sc.nextLine()); };
			 */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
