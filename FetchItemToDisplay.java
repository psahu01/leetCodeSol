package com.amazon.oa2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

public class FetchItemToDisplay {
	public static List<String> fetchItemsToDisplay(int numOfItems, HashMap<String, Pair<Integer, Integer>> items, int sortParameter, int sortOrder,
			int itemsPerPage, int pageNumber) {
		List<String> result = new ArrayList<>();
		if(numOfItems == 0)
			return result;
		ArrayList<String> keyList = new ArrayList<>(items.keySet());
		Collections.sort(keyList, new Comparator<String>() {
			public int compare(String a, String b) {
				if(sortParameter == 1) {
					Pair<Integer, Integer> aDesc = items.get(a);
					Pair<Integer, Integer> bDesc = items.get(b);
					return (sortOrder == 0) ? (aDesc.getKey() - bDesc.getKey()) : (bDesc.getKey() - aDesc.getKey()); 
				} else if(sortParameter == 2) {
					Pair<Integer, Integer> aDesc = items.get(a);
					Pair<Integer, Integer> bDesc = items.get(b);
					return (sortOrder == 0) ? (aDesc.getValue() - bDesc.getValue()) : (bDesc.getValue() - aDesc.getValue());
				} else {
					return (sortOrder == 0) ? a.compareTo(b) : b.compareTo(a);
				}
			}
		});
		
//		int totalPages = (numOfItems % itemsPerPage == 0) ? (numOfItems / itemsPerPage) : (numOfItems / itemsPerPage) + 1;
		
		int startIndexItem = (pageNumber) * itemsPerPage;
		int lastIndexItem = (startIndexItem + itemsPerPage > numOfItems) ? numOfItems : startIndexItem + itemsPerPage; 
		
		
		return keyList.subList(startIndexItem, lastIndexItem);
	}
	
	public static void main(String [] arg) {
//		int numOfItems = 5;
//		HashMap<String, Pair<Integer, Integer>> items = new HashMap<>();
//		int sortParameter = 2;
//		int sortOrder = 1;
//		int itemsPerPage = 2;
//		int pageNumber = 0;
//		
////		items.put("p1", new Pair<Integer, Integer>(1, 2));
////		items.put("p2", new Pair<Integer, Integer>(2, 1));
//		items.put("ow", new Pair<Integer, Integer>(585, 629));
//		items.put("rp", new Pair<Integer, Integer>(942, 960));
//		items.put("df", new Pair<Integer, Integer>(374, 463));
//		items.put("vj", new Pair<Integer, Integer>(186, 880));
//		items.put("kl", new Pair<Integer, Integer>(153, 850));
		
		int numOfItems = 5;
		HashMap<String, Pair<Integer, Integer>> items = new HashMap<>();
		int sortParameter = 1;
		int sortOrder = 1;
		int itemsPerPage = 3;
		int pageNumber = 1;
		
		items.put("prod1", new Pair<Integer, Integer>(10, 5));
		items.put("prod2", new Pair<Integer, Integer>(3, 3));
		items.put("prod3", new Pair<Integer, Integer>(17, 4));
		items.put("prod4", new Pair<Integer, Integer>(9, 4));
		items.put("prod5", new Pair<Integer, Integer>(1, 5));

		
		System.out.println(fetchItemsToDisplay(numOfItems, items, sortParameter, sortOrder, itemsPerPage, pageNumber));
	}

}
