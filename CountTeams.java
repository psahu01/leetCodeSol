package com.amazon.oa2;

import java.util.ArrayList;
import java.util.List;

public class CountTeams {
	private static int count = 0;
	private static List<List<Integer>> result = new ArrayList<>();
	public static int countTeams(int num, int[] skills, int minAssociates, int minLevel, int maxLevel) {
		List<Integer> associates = new ArrayList<Integer>();
		
		for(int skill : skills) {
			if(skill >= minLevel && skill <= maxLevel)
				associates.add(skill);
		}
		countAllCombinations(associates, minAssociates, new ArrayList<>(),0);
		return result.size();
	}
	
	public static void countAllCombinations(List<Integer> associates, int minAssociates, List<Integer> prefix, int start) {
		if(prefix.size() >= minAssociates) {
			result.add(new ArrayList<>(prefix));
		}
		
		for(int i=start;i<associates.size();i++) {
			prefix.add(associates.get(i));
			countAllCombinations(associates, minAssociates, prefix, i+1);
			prefix.remove(prefix.size()-1);
		}
	}
	
	public static void main(String[] arg) {
		int num = 6;
		int[] skills = new int[] {12, 4, 6, 13, 5, 10};
		int minAssociates = 4;
		int minLevel = 4;
		int maxLevel = 10;
		System.out.println(countTeams(num, skills, minAssociates, minLevel, maxLevel));
	}

}
