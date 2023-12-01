package kr.ac.gachon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Stock {									// 주식을 나타내는 클래스
	private String name;								// 주식 이름
	private TreeMap<String, Double> tradingRecords;		// 거래 기록
	private List<String> keysList;	// 날짜 순서 기록
	
	// 주식 객체 생성자
	public Stock(String newName) {
		name = newName;
		tradingRecords = new TreeMap<>();
		keysList = new ArrayList<>();
	}
	
	// 주식에 거래 기록 추가
	public void addTradingRecords(String newRecords) {
		String date;
		double price;
		String[] record = newRecords.split(" ");
		
		date = record[0];
		price = Double.parseDouble(record[1]);
		
		tradingRecords.put(date, price);
		
		updateKeysList();
	}
	
	// 날짜 순서 업데이트
	private void updateKeysList() {
		keysList.clear();
		
		for (Entry<String, Double> entry : tradingRecords.entrySet()) {
			keysList.add(entry.getKey());
		}
	}
	
	// 주식 이름 반환
	public String getName() {
		return name;
	}
	
	public ArrayList<Double> getPrices() {
		ArrayList<Double> prices = new ArrayList<>();
		
		for (Entry<String, Double> entry : tradingRecords.entrySet()) {
			prices.add(entry.getValue());
		}
		
		return prices;
	}
	
	// 주식 리스트에서 이름을 기준으로 주식을 찾아 반환해줌
	public static Stock findStockByName(List<Stock> stockList, String stockName) {
		for (Stock stock : stockList) if (stock.getName().equalsIgnoreCase(stockName)) return stock;
	
		return null;
	}
	
	// 주식에 대한 최대 이익 계산 및 출력
	public ArrayList<String> calculateProfit() {
		ArrayList<String> result = new ArrayList<>();
		
		// 만약 기록의 갯수가 2개 미만이면 중단
		if (tradingRecords == null || tradingRecords.size() < 2) return null;

		// 최대 이익 계산
		double minPrice = tradingRecords.get(keysList.get(0));
		double maxProfit = 0;
		double buyPrice = minPrice;
		double sellPrice = minPrice;
		
		for (int i = 1; i < keysList.size(); i++) {
			double currentPrice = tradingRecords.get(keysList.get(i));
			
			if (currentPrice < minPrice) {
				minPrice = currentPrice;
				buyPrice = currentPrice;
			}
			
			double currentProfit = currentPrice - minPrice;
			
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
				sellPrice = currentPrice;
			}
		}
		
		if (maxProfit == 0) return null;
		
		// 결과 저장 및 출력
		result.add("" + buyPrice);
		result.add(getKeyByValue(tradingRecords, buyPrice));
		result.add("" + sellPrice);
		result.add(getKeyByValue(tradingRecords, sellPrice));
		result.add("" + maxProfit);
		
		return result;
	}
	
	// TreeMap에서 Value를 가진 Key 탐색
	private static <K, V> K getKeyByValue(TreeMap<K, V> map, V value) {
		for (Entry<K, V> entry: map.entrySet()) if (value.equals(entry.getValue())) return entry.getKey();
		
		return null;
	}
}
