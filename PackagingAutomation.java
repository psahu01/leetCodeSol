package com.amazon.oa2;

import java.util.Arrays;

public class PackagingAutomation {
	public static int packaging(int[] arr, int numGroups) {
		Arrays.sort(arr);
		
		if(arr[0] > 1)
			arr[0] = 1;
		
		for(int i=1;i<arr.length;i++) {
			if(arr[i] >= arr[i-1]+1)
				arr[i] = arr[i-1]+1;
			else
				return i;
		}
		
		return numGroups;
	}
	
	public static void main(String[] arg) {
		int[] arr = new int[] {2,3,2,3};
		System.out.println(packaging(arr, arr.length));
	}

}
