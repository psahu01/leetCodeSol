package com.amazon.oa2;

public class BreakAPalindrome {
	public String breakPalindrome(String palindrome) {
		char[] charSt = palindrome.toCharArray();

		for (int i = 0; i < charSt.length / 2; i++) {
			if (charSt[i] != 'a') {
				charSt[i] = 'a';
				return String.valueOf(charSt);
			}
		}

		charSt[charSt.length - 1] = 'b';
		return (palindrome.length() < 2) ? "" : String.valueOf(charSt);
	}
}
