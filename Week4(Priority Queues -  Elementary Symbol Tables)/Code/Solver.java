import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver {
	private final SearchNode goal; // the target node

	public Solver(Board initial) // find a solution to the initial board (using
									// the A* algorithm)
	{

		MinPQ<SearchNode> queue1 = new MinPQ<SearchNode>();
		MinPQ<SearchNode> queue2 = new MinPQ<SearchNode>();

		queue1.insert(new SearchNode(initial, null, 0));
		queue2.insert(new SearchNode(initial.twin(), null, 0));

		while (true) {
			SearchNode node1 = queue1.delMin();

			if (node1.getBoard().isGoal()) {
				this.goal = node1;
				break;
			}

			for (Board neighbors : node1.board.neighbors()) {
				if (node1.getPrevious() != null
						&& neighbors.equals(node1.getPrevious().getBoard()))
					continue;
				queue1.insert(new SearchNode(neighbors, node1,
						node1.movesMade + 1));

			}

		}

	}

	public boolean isSolvable() {
		return goal != null;
	}

	private static class SearchNode implements Comparable<SearchNode> {
		private final Board board; // the current board
		private final SearchNode previous; // the pointer to the previous node
		private final int movesMade; // the number of moves made so far

		/** Creates a search node. */
		public SearchNode(Board board, SearchNode previous, int movesMade) {
			this.board = board;
			this.previous = previous;
			this.movesMade = movesMade;
		}

		/** Returns the current board. */
		public Board getBoard() {
			return board;
		}

		/** Returns the previous node. */
		public SearchNode getPrevious() {
			return previous;
		}

		/** Returns the number of moves made so far. */
		public int getMovesMade() {
			return movesMade;
		}

		@Override
		public int compareTo(SearchNode that) {
			int m1 = this.board.manhattan() + this.movesMade;
			int m2 = that.board.manhattan() + that.movesMade;
			return m1 - m2;
		}
	}

	// public boolean isSolvable(Board initial) // is the initial board
	// solvable?

	 public int moves() // min number of moves to solve initial board; -1 if no solution
	 {
		 if (this.isSolvable())
		 return goal.movesMade;
		 else return -1;
	 }
	 
	
	 public Iterable<Board> solution() // sequence of boards in a shortest
	 {
		 if ( !this.isSolvable())return null;
		 
		 Stack<Board> solution = new Stack<Board>();
		 /*SearchNode n = goal;
		 for (int i = 0; i < goal.movesMade; i++)	 
		 {
			 solution.push(n.board);
			 n = n.previous;
		 }*/
		 for(SearchNode n = goal; n != null; n = n.getPrevious() )
		 {
			 solution.push(n.getBoard());
		 }
		 
		 return solution;
		 
		 
	 }
	// solution; null if no solution

	public static void main(String[] args) // solve a slider puzzle (given
											// below)
	{
		/*
		 * // create initial board from file In in = new In(args[0]); int N =
		 * in.readInt(); int[][] blocks = new int[N][N]; for (int i = 0; i < N;
		 * i++) for (int j = 0; j < N; j++) blocks[i][j] = in.readInt(); Board
		 * initial = new Board(blocks);
		 * 
		 * // solve the puzzle Solver solver = new Solver(initial);
		 * 
		 * // print solution to standard output
		 * 
		 * if (!solver.isSolvable()) StdOut.println("No solution possible");
		 * else { StdOut.println("Minimum number of moves = " + solver.moves());
		 * for (Board board : solver.solution()) StdOut.println(board); }
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

			Board initial = new Board(a);
			
			Solver solver = new Solver(initial);
			
			 if (!solver.isSolvable()) StdOut.println("No solution possible");
			  else { StdOut.println("Minimum number of moves = " + solver.moves());
			  for (Board board : solver.solution()) StdOut.println(board); }

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