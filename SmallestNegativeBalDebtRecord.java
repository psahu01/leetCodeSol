package com.amazon.oa2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class SmallestNegativeBalDebtRecord {

	class debtRecord{
	    String borrower = ""; // importer
	    String lender = ""; //exporter
	    int amount = 0;
	}
	
	public List<String> minimumDebtMembers(List<debtRecord> records){
		Map<String, Integer> mapping = new HashMap<String, Integer>();
		for(debtRecord record : records) {
			mapping.putIfAbsent(record.borrower, 0);
			mapping.putIfAbsent(record.lender, 0);
						
			mapping.put(record.borrower, mapping.get(record.borrower) - record.amount);
			mapping.put(record.lender, mapping.get(record.lender) + record.amount);
		}
		int min = Integer.MAX_VALUE;
		List<String> result = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : mapping.entrySet()) {
			if(entry.getValue() < 0) {
				if(entry.getValue() < min) {
					min = entry.getValue();
					result.clear();
					result.add(entry.getKey());
				} else if(entry.getValue() == min)
					result.add(entry.getKey());
			}
		}
		
		if(result.isEmpty())
			result.add("Nobody has a negative balance");
		
		return result;
	}
	
    public static String[] debtRecords(int numCols, int numRows, String[][] debts) {
		Map<String, Integer> mapping = new TreeMap<String, Integer>();
		for(int i = 0;i<debts.length;i++) {
			String borrower = debts[i][0];
			String lender = debts[i][1];
			int amount = Integer.parseInt(debts[i][2]);
			mapping.putIfAbsent(borrower, 0);
			mapping.putIfAbsent(lender, 0);
						
			mapping.put(borrower, mapping.get(borrower) - amount);
			mapping.put(lender, mapping.get(lender) + amount);
		}
		
		List<String> result = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : mapping.entrySet()) {
			if(entry.getValue() < 0) {
				result.add(entry.getKey());
			}
		}
		if(result.isEmpty())
			result.add("Nobody has a negative balance");
		return result.toArray(new String[result.size()]);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numCols = Integer.parseInt(scanner.nextLine());
        int numRows = Integer.parseInt(scanner.nextLine());
        String [][] debts = new String[numRows][numCols];
        for (int i=0; i<numRows; i++) {
            String [] line = scanner.nextLine().split(" ");
            for (int j=0; j<numCols; j++) {
               debts[i][j] = line[j];
            }
        }

        System.out.println(String.join(" ", debtRecords(numCols, numRows, debts)));
    }
}

