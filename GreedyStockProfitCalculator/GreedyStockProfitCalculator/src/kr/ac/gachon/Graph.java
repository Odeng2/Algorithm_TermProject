package kr.ac.gachon;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Double> list;
	private double average;
	
	public Graph(ArrayList<Double> newList) {
		list = newList;
		
		for (double d : list) average += d;
		average /= list.size();
	}
	
	public int getListNum() {
		return list.size();
	}
	
	public double getPercent(int x) {
		return (list.get(x) / (average * 2)) * 100;
	}
}
