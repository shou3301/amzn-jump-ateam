/*

Analysis:

Things to pay attention to:
1. You can apply Ctrl-V multiple times to append buffered 'A's to the end.
2. Initially, there will be no good to use (Ctrl-A, Ctrl-C, Ctril-V)
	For example, if you type 'A', and use the combo above, then you can only
	get 2 'A's by 4 keys.

Now, let's assume you already have X 'A's, then you can compute:

2X > X + 3

This means you key 3 times, you need (Ctrl-A, Ctrl-C, Ctril-V) benefits more than
you key 'A' 3 times (which both costs 3 keys). You will find if you can only key less
than 6 times, the max number of 'A's you can get is equal to the number of keys you 
can have.

After we know this property, we just need to decide from which point, we will only
keep using Ctrl-V to append buffered 'A's.

Thus, we have formula:

M[j] = 
1. j if j <= 6
2. otherwise, ((j-i+1) - 2) * M[i], where 1 <= i <= j - 3

(j-i+1) means the number of keys you have from i (exclusive) to j (inclusive)
and because we need 2 keys for Ctrl-A, Ctrl-C, we minus 2 from it.

I use memo/recursiion to solve this.

*/
public class Solution {
	int[] memo;

	public int maxNumOfA(int N) {
		memo = new int[N+1];
		return helper(N);
	}

	private int helper(int n) {
		if (n <= 6) return n;

		if (memo[n] != 0) return memo[n];

		int max = 0;
		for (int i = n-3; i > 0; i--) {
			max = Math.max(max, maxNumOfA(i) * (n - i - 1));
		}

		memo[n] = max;
		return max;
	}
}