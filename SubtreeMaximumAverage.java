package com.amazon.oa2;

import java.util.List;

public class SubtreeMaximumAverage {
	class TreeNode {
	    int val;
	    List<TreeNode> children;
	    TreeNode() {}
	    TreeNode(int val) { this.val = val; }
	}
	
	private double maxAvg = Double.MIN_VALUE;
	public double maximumAverageSubtree(TreeNode root) {
		if(root == null)
			return maxAvg;
		dfs(root);
		return maxAvg;
	}
	
	private int[] dfs(TreeNode root) {
		if(root == null)
			return new int[] {0, 0};
		
		int curTotal = root.val;
	    int count = 1;
		for(TreeNode child : root.children) {
			int[] childValue = dfs(child);
			
			curTotal += childValue[0];
			count += childValue[1];
		}
		maxAvg = Math.max(maxAvg, 1.0 * curTotal/count);
		
		return new int[] {curTotal, count};
	}

}
