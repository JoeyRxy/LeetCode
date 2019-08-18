func max(i, j int) int {
    if i > j {
        return i
    }
    return j
}

func helper(start int, a []int, cost int, dp []int) int {
    if start >= len(a) {
        return 0
    }
    res := 0
    if dp[start] != math.MinInt32 && cost == math.MaxInt32 {
        return dp[start]
    }
    if cost == math.MaxInt32 {
        // Buy or do nothing
        res = max(helper(start+1, a, -1 * a[start], dp), helper(start+1, a, cost, dp))
        dp[start] = res
    } else if a[start] + cost > 0 {
        // Sell or hold
        res = max(helper(start+1, a, cost, dp), a[start] + cost + helper(start+2, a, math.MaxInt32, dp))
    }
    return res
}

func maxProfit(a []int) int {
    dp := make([]int, 0, len(a))
    for i := 0; i < len(a); i++ {
        dp = append(dp, math.MinInt32)
    }
    return helper(0, a, math.MaxInt32, dp)
}