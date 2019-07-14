import java.util.HashMap;

class ShortestHamiltonPath {
    int n;
    int[][] adjMat;
    public ShortestHamiltonPath(int n, int[][] adjMat) {
        this.n = n;
        this.adjMat = adjMat;
    }
    public HashMap<String, Object> solve() {
        int[][] dp = new int[1 << n][n];
        int[][] prev = new int [1 << n][n];
        dp[1][0] = prev[1][0] = 0;
        for (int i = 3; i < 1 << n; i += 2)
            for (int j = 1; j < n; ++j)
                if (((i >> j) & 1) != 0) {
                    if (i - (1 << j) == 1) {
                        dp[i][j] = adjMat[0][j];
                        prev[i][j] = 0;
                    } else {
                        dp[i][j] = (1 << 31) - 1;
                        for (int k = 1; k < n; ++k)
                            if (k != j && ((i >> k) & 1) != 0)
                                if (dp[i - (1 << j)][k] + adjMat[k][j] < dp[i][j]) {
                                    dp[i][j] = dp[i - (1 << j)][k] + adjMat[k][j];
                                    prev[i][j] = k;
                                }
                    }
                }
        int[] path = new int[n];
        int node = n - 1;
        int state = (1 << n) - 1;
        for (int i = n - 1; i >= 0; --i) {
            path[i] = node;
            node = prev[state][node];
            state -= 1 << path[i];
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("path", path);
        result.put("time", dp[(1 << n) - 1][n - 1]);
        return result;
    }
}