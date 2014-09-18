public class SuccessorDelete {
	private int[] id;
	private int[] sz;
	private int[] maximum;

	public SuccessorDelete(int N) {
		id = new int[N];
		sz = new int[N];
		maximum = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
			maximum[i] = i;
		}
	}

	public int delete(int p) {
		if (p == id.length - 1)
			return p; // here if we try to delete the maximum of the whole set,
						// just return it.
		return union(p, p + 1);
	}

	private int union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		if (sz[rootP] >= sz[rootQ]) {
			sz[rootP] += sz[rootQ];
			id[rootQ] = rootP;
			if (maximum[rootQ] > maximum[rootP]) {
				maximum[rootP] = maximum[rootQ];
			}
			return maximum[rootP];
		} else {
			sz[rootQ] += sz[rootP];
			id[rootP] = rootQ;
			if (maximum[rootP] > maximum[rootQ]) {
				maximum[rootQ] = maximum[rootP];
			}
			return maximum[rootQ];
		}
	}

	private int root(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]]; // path compression
			p = id[p];
		}
		return p;
	}
}