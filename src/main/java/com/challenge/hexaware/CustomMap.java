package com.challenge.hexaware;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CustomMap<K,V> {

	private TreeMap<K, V> map = null;
	private CustomMap() {
		map = new TreeMap<K,V>();
	}
	
	private CustomMap(final Map<K,V> unorderedMap) {
		map = new TreeMap<K,V>();
		if(unorderedMap == null || unorderedMap.isEmpty()) {
			return;
		}
		map.putAll(unorderedMap);
	}
	
	public static final <K, V> CustomMap<K,V> emptyMap() {
		return new  CustomMap<K,V>();
	}
	
	public static final <K,V> CustomMap<K,V> of(final Map<K, V> map) {
		return new CustomMap<K,V>(map);
	}
	
	public final CustomMap<K,V> put(final K key, final V value) {
		map.put(key, value);
		return this;
	}
	
	public final Map.Entry<K, V> find(K key) {
		return map.entrySet().stream().filter(entry -> entry.getKey().equals(key)).findFirst().orElse(null);
	}
	
	public final List<Map.Entry<K, V>> findAll(K key) {
		return map.entrySet().stream()
							.filter(entry -> entry.getKey().equals(key))
							.collect(Collectors.toList());
	}
	

	
	public Iterator<Map.Entry<K,V>> ascending() {
		return map.entrySet().iterator();
	}
	
	public Iterator<Map.Entry<K,V>> descending() {
		return map.descendingMap().entrySet().iterator();
	}
	
	
	
	public static void main(String args[]) {
		ascendingTest();
		descendingTest();
		uniqueKeyTest();
		searchTest();
	}
	
	private static void ascendingTest() {
		header("Ascending Test");
		final CustomMap<Integer, String> myMap = testMap();
		
		StreamSupport.stream(Spliterators.spliteratorUnknownSize(myMap.ascending(), Spliterator.ORDERED),false)
								.forEach(entry -> {
								System.out.println(entry.getKey()+", "+entry.getValue());
							});
	}
	private static void descendingTest() {
		header("Descending Test");
		final CustomMap<Integer, String> myMap = testMap();
		
		StreamSupport.stream(Spliterators.spliteratorUnknownSize(myMap.descending(), Spliterator.ORDERED),false)
								.forEach(entry -> {
								System.out.println(entry.getKey()+", "+entry.getValue());
							});
	}
	
	private static void uniqueKeyTest() {
		header("Unique Key Test");
		final CustomMap<Integer, String> myMap = testMap();
		myMap.put(1, "One");
		final List<Map.Entry<Integer, String>> ones = myMap.findAll(1);
		System.out.println("Size should be 1:"+ones.size());
		
	}
	
	private static final void searchTest() {
		header("Search Test");
		final CustomMap<Integer, String> myMap = testMap();
		
		Map.Entry<Integer, String> entry = myMap.find(-1);
		System.out.println("Entry Shold Not be null : "+ entry);
		
		entry = myMap.find(-11);
		System.out.println("Entry Shold be null : "+ null);
		
	}
	
	private static final CustomMap<Integer, String> testMap() {
		final CustomMap<Integer, String> myMap = CustomMap.emptyMap();
		myMap.put(1, "One")
			.put(10, "Ten")
			.put(2, "Two")
			.put(8, "Eight")
			.put(1, "One")
			.put(-1, "NegativeOne")
			.put(Integer.MIN_VALUE, "MinInteger")
			.put(Integer.MAX_VALUE, "MaxInteger");
		return myMap;
	}
	
	private static void header(final String header) {
		System.out.println("\n===============================");
		System.out.println(header);
		System.out.println("==============================");
	}
}
