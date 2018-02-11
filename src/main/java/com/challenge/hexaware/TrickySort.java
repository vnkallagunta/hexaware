package com.challenge.hexaware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrickySort {
	
	public static final String EMPTY_STRING = "";
	
	public static final String sort(final String wordsAndNumbers, final String delimiter) {
		if(wordsAndNumbers == null || wordsAndNumbers.trim().length() == 0) {
			return EMPTY_STRING;
		}
		
		final String[] tokens = wordsAndNumbers.trim().split(delimiter+"+");
		final List<Integer> integers = new ArrayList<>();
		final List<String> words = new ArrayList<>();
		final Type typePlaceHolder[] = new Type[tokens.length];
		for(int i=0; i<tokens.length; i++) {
			if(Utils.isInteger(tokens[i])) {
				typePlaceHolder[i] = Type.INTEGER;
				integers.add(Integer.parseInt(tokens[i]));
				continue;
			}
			typePlaceHolder[i] =Type.WORD;
			words.add(tokens[i]);
		}
		Collections.sort(integers);
		Collections.sort(words);
		
		int currentIntIndex = 0;
		int currentWordIndex = 0;
		
		final StringBuffer trickySorted = new StringBuffer();
		for(Type type: typePlaceHolder) {
			if(type == Type.INTEGER) {
				trickySorted.append(integers.get(currentIntIndex++)).append(delimiter);
				continue;
			}
			trickySorted.append(words.get(currentWordIndex++)).append(delimiter);
		}
		return trickySorted.toString();
	}
	
	public static void main(String args[]) {
		final String BLANK = "";
		final String ALL_NUMBERS = "1 2 3 4 5 6 34 12 43";
		final String ALL_WORDS = "TRUCK CAR bus Van CarVan RV train Flight Boat";
		final String WORDS_NUMBERS_NOSPACE = "cartruck84bus61";
		final String WORDS_NUMBERS_NOSPACE_EXTRA_SPACE = "car        truck 8 4 bus 6 1";
		final String WORDS_NUMBERS_SPECIAL_CHAR = "car tr'uck 8 4 bus 6 1";
		final String WORDS_NUMBERS1 = "car truck 8 4 bus 6 1";
		
		
		test("Blank", BLANK, BLANK);
		test("All Numbers", ALL_NUMBERS, "1 2 3 4 5 6 12 34 43");
		test("All Words", ALL_WORDS, "Boat CAR CarVan Flight RV TRUCK Van bus train");
		test("Words And Numbers NoSpace", WORDS_NUMBERS_NOSPACE, WORDS_NUMBERS_NOSPACE);
		test("Words And Numbers ExtraSpace", WORDS_NUMBERS_NOSPACE_EXTRA_SPACE, "bus car 1 4 truck 6 8");
		test("Words And Numbers SpecialChar", WORDS_NUMBERS_SPECIAL_CHAR, "bus car 1 4 tr'uck 6 8");
		test("Words And Numbers", WORDS_NUMBERS1, "bus car 1 4 truck 6 8");

	}
	
	private static void test(final String header, final String input, final String expected) {
		header(header);
		System.out.println("Expected : "+expected);
		System.out.println("Actual   : "+ TrickySort.sort(input, " "));
	}
	
	private static void header(final String header) {
		System.out.println("\n===============================");
		System.out.println(header);
		System.out.println("==============================");
	}
}
