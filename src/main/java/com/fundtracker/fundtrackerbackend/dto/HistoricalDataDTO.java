package com.fundtracker.fundtrackerbackend.dto;

import java.util.List;

public class HistoricalDataDTO {
	private double currentPrice;
	private List<DateValuePairDTO> historicalData;
	
	public HistoricalDataDTO(){
		
	}
	
	public HistoricalDataDTO(double currentPrice, List<DateValuePairDTO> historicalData) {
		super();
		this.historicalData = historicalData;
	}
	
	public HistoricalDataDTO(List<DateValuePairDTO> historicalData) {
		super();
		this.historicalData = historicalData;
		this.setCurrentPrice(this.LatestPrice());
	}

	public double LatestPrice(){
		if(historicalData == null || historicalData.size() == 0){
			return -1;
		}
		return historicalData.get(historicalData.size() - 1).payDegeri;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public List<DateValuePairDTO> getHistoricalData() {
		return historicalData;
	}

	public void setHistoricalData(List<DateValuePairDTO> historicalData) {
		this.historicalData = historicalData;
	}
	
	
	
	
}
