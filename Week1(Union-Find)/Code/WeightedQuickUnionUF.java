

/****************************************************************************
 *  Compilation:  javac WeightedQuickUnionUF.java
 *  Execution:  java WeightedQuickUnionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Weighted quick-union (without path compression).
 *
 ****************************************************************************/

/**
 *  The <tt>WeightedQuickUnionUF</tt> class represents a union-find data structure.
 *  It supports the <em>union</em> and <em>find</em> operations, along with
 *  methods for determinig whether two objects are in the same component
 *  and the total number of components.
 *  <p>
 *  This implementation uses weighted quick union by size (without path compression).
 *  Initializing a data structure with <em>N</em> objects takes linear time.
 *  Afterwards, <em>union</em>, <em>find</em>, and <em>connected</em> take
 *  logarithmic time (in the worst case) and <em>count</em> takes constant
 *  time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *     
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class WeightedQuickUnionUF {
    private int[] id;    // id[i] = parent of i
    private int[] sz;    // sz[i] = number of objects in subtree rooted at i
    private int count;   // number of components
    private int countRoot; // number of roots
    private int[] maximum; // keep track of the maximum object in each connected component
    private int[] height; // Union by Height
    /**
     * Initializes an empty union-find data structure with N isolated components 0 through N-1.
     * @throws java.lang.IllegalArgumentException if N < 0
     * @param N the number of objects
     */
    public WeightedQuickUnionUF(int N) {
        count = N;
        countRoot = N;
        id = new int[N];
        sz = new int[N];
        maximum = new int[N];
        height = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            maximum[i] = i;
            height[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     * @return the number of components (between 1 and N)
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     * @param p the integer representing one site
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
     */
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }
	
    /**
     * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt>
     *    are in the same component, and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public  boolean  isConnectedAll() {
        return countRoot == 1;
    }
    
    public int CntRoot(){
    	return countRoot;
    	}
    
    public int maxInSameComponent(int p){
    	return maximum[find(p)];
    	}
    
    /**
     * Merges the component containing site<tt>p</tt> with the component
     * containing site <tt>q</tt>.
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {return;}

        // make smaller root point to larger one
        if   (sz[rootP] < sz[rootQ]) { id[rootP] = rootQ; sz[rootQ] += sz[rootP]; if (maximum[rootP] > maximum[rootQ]) {maximum[rootQ] = maximum[rootP];}}
        else                         { id[rootQ] = rootP; sz[rootP] += sz[rootQ]; if (maximum[rootP] < maximum[rootQ]) {maximum[rootP] = maximum[rootQ];}}
        count--;
        countRoot--;
    }
    
    public void print() {
    	for (int i = 0; i < id.length; i++)
    		 StdOut.print(id[i] + " "); 
    }

	public void unionByHeight(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;
		if (height[rootP] > height[rootQ]) {
			// we link the shorter tree to the bigger tree
			id[rootQ] = rootP;
		} else if (height[rootP] < height[rootQ]) {
			// we link the shorter tree to the bigger tree
			id[rootP] = rootQ;
		} else {
			// we link the tree of q to the tree of p
			id[rootQ] = rootP;
			// and do not forget the height increase
			height[rootP]++;
		}
	}

    /**
     * Reads in a sequence of pairs of integers (between 0 and N-1) from standard input, 
     * where each integer represents some object;
     * if the objects are in different components, merge the two components
     * and print the pair to standard output.
     */
    public static void main(String[] args) {
    	/**   int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");*/
    	
    	/*int N = 10;
    	WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
    	uf.print();
    	StdOut.println();
    	uf.union(2, 5);
    	uf.print();
    	StdOut.println();
    	uf.union(0, 7);
    	uf.union(2, 9);
    	uf.union(6, 4);
    	uf.union(1, 8);
    	uf.union(6, 5);
    	uf.union(0, 1);
    	uf.union(0, 5);
    	uf.union(9, 3);
        
    	uf.print();*/
    	
    	/* Q1
    	Social network connectivity. Given a social network containing N members 
    	and a log file containing M timestamps at which times pairs of members formed friendships, 
    	design an algorithm to determine the earliest time at which all members are connected 
    	(i.e., every member is a friend of a friend of a friend ... of a friend). 
    	Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. 
    	The running time of your algorithm should be MlogN or better and use extra space proportional to N.
    	*/
    	
    	/*
    	int N = 4;
    	WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
    	StdOut.println();
    	StdOut.println( uf.isConnectedAll());
    	StdOut.println( uf.CntRoot());
    	uf.print();StdOut.println();
    	uf.union(0, 1);
    	uf.union(0, 1);
    	uf.union(0, 3);
    	uf.union(2, 3);
    	uf.print();
    	StdOut.println();
    	StdOut.println( uf.isConnectedAll());
    	StdOut.println( uf.CntRoot());
    	*/
    	
    	/* Q2
    	 Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) 
    	  returns the largest element in the connected component containing i. 
    	  The operations, union(), connected(), and find() should all take logarithmic time or better.
         For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.
    	*/
    	
    	/*int N = 4;
    	WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
    	StdOut.println();
    	StdOut.println( uf.isConnectedAll());
    	StdOut.println( uf.CntRoot());
    	uf.print();StdOut.println();
    	uf.union(0, 1);
    	uf.union(0, 1);
    	uf.union(0, 3);
    	uf.union(2, 3);
    	uf.print();
    	StdOut.println();
    	StdOut.println("Only One root? " + uf.isConnectedAll());
    	StdOut.println("Count Roots " + uf.CntRoot());	
    	StdOut.println("Max component " + uf.maxInSameComponent(0));*/
    	
    	/*
    	Q4
    	Union-by-size. Develop a union-find implementation that uses the same basic strategy as weighted quick-union 
    	but keeps track of tree height and always links the shorter tree to the taller one. 
    	Prove a lgN upper bound on the height of the trees for N sites with your algorithm.
    	*/
    	
    	int N = 6;
    	WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
    	StdOut.println();
    	StdOut.println( uf.isConnectedAll());
    	StdOut.println( uf.CntRoot());
    	uf.print();StdOut.println();
    	uf.unionByHeight(0, 1);
    	uf.unionByHeight(0, 1);
    	uf.unionByHeight(2, 2);uf.unionByHeight(3, 2);uf.unionByHeight(4, 3);uf.unionByHeight(5, 2);
    	uf.print();
    	StdOut.println();
    	StdOut.println("Only One root? " + uf.isConnectedAll());
    	StdOut.println("Count Roots " + uf.CntRoot());	
    	StdOut.println("Max component " + uf.maxInSameComponent(0));
    	StdOut.println("Haight " + uf.height[5]);
    	
    	
    }

}

