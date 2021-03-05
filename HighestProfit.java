package com.amazon.oa2;

import java.util.*;
public class HighestProfit {
    public static int findProfit(int numSuppliers, int[] inventory, int order) {
        int profit = 0;
        PriorityQueue<Integer> itemInventory = new PriorityQueue<Integer>(new Comparator<Integer>(){
        	public int compare(Integer a, Integer b) {
        		return b-a;
        	}
		});
        for(int item : inventory) {
        	itemInventory.add(item);
        }
        
        while(!itemInventory.isEmpty()) {
        	if(order-- == 0) return profit;
        	
        	int itemQ = itemInventory.poll();
        	profit += itemQ--;
       
        	if(itemQ > 0) itemInventory.add(itemQ);
        }
        return profit;
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int numSuppliers = Integer.parseInt(scanner.nextLine());
        int[] inventory = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int order = Integer.parseInt(scanner.nextLine());

        scanner.close();
        System.out.println(findProfit(numSuppliers, inventory, order));
    }
}