class Solution{

    // Approach 1: Tabulation
    // TC = O(n^3) ; SC =O(n^2)
    public int maxCoins(int[] nums) {
        int n= nums.length;
        int[][] dp = new int[n][n];

        for (int le=1; le<=n; le++)
        {
            for (int i=0;i<=n-le;i++)
            {
                int j= i+le-1;

                for (int k=i; k<=j; k++)
                {
                    int before=0, after=0;
                    if (i!=k)
                    {
                        before= dp[i][k-1];
                    }
                    if (j!=k)
                    {
                        after= dp[k+1][j];
                    }
                    int curr= nums[k];
                    int prev= (i != 0) ? nums[i-1] : 1;
                    int next= (j != n-1) ? nums[j+1] : 1;
                    int balloonItself= prev* curr*next;
                    if (i!=0)
                    {
                        prev= nums[i-1];
                    }
                    if(j!=n-1)
                    {
                        next= nums[j+1];
                    }
                    dp[i][j]= Math.max(dp[i][j], before+ balloonItself+ after);
                }
            }
        }
        return dp[0][n-1];
    }


    // Approach 2: Memoization with Recursion
    int n;
    int[][] memo;
    public int maxCoins1(int[] nums) {

        this.n = nums.length;
        this.memo = new int[n][n];
        return helper(nums, 0, n-1);
    }
    private int helper1(int[] nums, int left, int right){
        if (left>right){return 0;}
        int max=0;
        if (memo[left][right]!=0){
            return memo[left][right];
        }

        for (int k=left;k<=right;k++){
            // before + after+ balloon itself
            // int before, after=0;

            int before= helper(nums, left, k-1);
            int after= helper(nums, k+1, right);

            int prev=1, next=1;
            if (left!=0)
            {
                prev = nums[left-1];
            }
            if (right!=0)
            {
                next = nums[right-1];
            }
            int balloonItself= before+prev *nums[k]* next + after;
            max= Math.max(max,balloonItself);
        }
        memo[left][right]=max;
        return max;
    }

}