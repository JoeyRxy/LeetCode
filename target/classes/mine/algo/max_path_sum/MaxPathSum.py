'''
首先，想一个简化版(single path)，找从root到任意点得最大值。类似于maxDepth，每次加root.val而不再是+1

求单路的时候，如果root加左儿子单路或者右儿子单路最后的值都小于0，则返回0，意味着不要root开始的这个单路了
本题思路，divide & conquer
求最大路径和就等于下面三个值的最大值：
左子树的最大路径和
右子树最大路径和
左子树单路 + 右子树单路 + root.val
'''

class Solution(object):
    def maxPathSum(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        res, _ = self.helper(root)
        return res
        
    def helper(self, root):
        if not root:
            return -sys.maxint, 0
            
        left = self.helper(root.left)
        right = self.helper(root.right)
        singlePathSum = max(left[1] + root.val, right[1] + root.val, 0)
        maxPathSum = max(left[0], right[0], left[1] + right[1] + root.val)
        return maxPathSum, singlePathSum