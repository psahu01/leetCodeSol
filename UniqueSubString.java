package com.amazon.oa2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UniqueSubString {
	public static Set<String> uniqueSubstringSizeK(String s, int k) {
        Set<String> set = new HashSet<>();
        int[] ch = new int[26];
        int i=0;
        int j=0;
        while(i<=j && j<s.length()) {
            ch[s.charAt(j)-'a']++;
            while(ch[s.charAt(j)-'a'] != 1) {
                ch[s.charAt(i)-'a']--;
                i++;
            }
            if(j-i+1 == k) {
                set.add(s.substring(i, j+1));
                ch[s.charAt(i)-'a']--;
                i++;
            }
            j++;
        }
        System.out.println(set.size());
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        return set;
    }
    
    public static void main(String[] args) {
        uniqueSubstringSizeK("abcabc", 3);
        uniqueSubstringSizeK("abacab", 3);
        uniqueSubstringSizeK("awaglknagawunagwkwagl", 4);
    }
}
