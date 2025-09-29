class Solution {
    // Approach 1
    // TC= O(n^3) ; SC= O(n^2)
  //  This code solves the Super Egg Drop problem using dynamic programming to find the minimum number of attempts needed to determine the critical floor with k eggs and n floors. The DP table dp[i][j] represents the minimum attempts needed with i eggs and j floors. For base case, with 1 egg we must try floors sequentially, so dp[1][j] = j. For each state dp[i][j], it tries dropping an egg from every possible floor f and takes the worst case: if egg breaks (dp[i-1][f-1]) or doesn't break (dp[i][j-f]), adding 1 for the current attempt. The algorithm finds the minimum across all possible floor choices to get the optimal strategy.
    public int superEggDrop1(int k, int n) {
        int[][] dp = new  int[k+1][n+1];
        for (int j=1;j<=n;j++)
        {
            dp[1][j] =j;
        }
        for (int i=2;i<=k;i++)
        {
            for (int j=1;j<=n;j++)
            {
                for (int f=1;f<=j;f++)
                {
                    dp[i][j]= Math.min(dp[i][j], 1+Math.max(dp[i-1][f-1], dp[i][j-f]));
                }
            }
        }
        return dp[k][n];
    }
    // Approach 2
    // TC= O(n^2) ; SC= O(n^2)
    //This code uses a reverse approach to solve the Super Egg Drop problem by asking "what's the maximum number of floors we can handle with k eggs in attempts tries?" instead of finding minimum attempts for given floors. The DP table dp[attempts][j] represents the maximum floors that can be handled with j eggs in exactly attempts tries. The recurrence dp[attempts][j] = 1 + dp[attempts-1][j-1] + dp[attempts-1][j] means: drop an egg from an optimal floor, and handle the worst case of either egg breaking (search lower floors with j-1 eggs) or not breaking (search upper floors with j eggs). The algorithm incrementally increases attempts until dp[attempts][k] >= n, meaning we can handle at least n floors with k eggs in that many attempts. This approach is more efficient as it avoids the inner loop over all possible floors.
    public int superEggDrop2(int k, int n) {
        int[][] dp =new  int[n+1][k+1];
        int attempts = 0;

        while (dp[attempts][k] <n)
        {
            attempts++;
            for (int j=1;j<=k;j++)
            {
                dp[attempts][j]= 1+ dp[attempts-1][j-1] + dp[attempts-1][j];
            }
        }

        return attempts;
    }

}