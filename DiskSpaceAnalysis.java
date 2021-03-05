package com.amazon.oa2;

import java.util.LinkedList;

public class DiskSpaceAnalysis {
	public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k==0)
            return new int[0];
        
        int n=nums.length;
        int[] result = new int[n-k+1];
        LinkedList<Integer> dequeue = new LinkedList<>();
        
        int j=0;
        for(int i=0;i<n;i++){
            while(!dequeue.isEmpty() && dequeue.peekFirst() < i-k+1)
                dequeue.pollFirst();
            
            while(!dequeue.isEmpty() && nums[dequeue.peekLast()] < nums[i])
                dequeue.pollLast();
            
            dequeue.offerLast(i);
            
            if(i >= k-1)
                result[j++] = nums[dequeue.peekFirst()];
        }
        return result;
    }
}
