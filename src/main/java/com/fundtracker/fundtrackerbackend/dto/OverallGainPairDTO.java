package com.fundtracker.fundtrackerbackend.dto;

import java.time.LocalDate;

public class OverallGainPairDTO extends ValueGainPairDTO {
	private double totalPrice;
	private double latestFundPrice;
	private double currentTotalPrice;
	
	public OverallGainPairDTO(){
		super();
	}
	
	public OverallGainPairDTO(LocalDate dateAcquired, double totalSharesOwned, double totalPrice, double fundValue, double amountGained,
			double gainRatio, double latestFundPrice, double currentTotalPrice) {
		super(dateAcquired, totalSharesOwned, fundValue, amountGained, gainRatio);
		this.totalPrice = totalPrice;
		this.latestFundPrice = latestFundPrice;
		this.currentTotalPrice = currentTotalPrice;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setLocalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public double getLatestFundPrice() {
		return latestFundPrice;
	}

	public void setLatestFundPrice(double latestFundPrice) {
		this.latestFundPrice = latestFundPrice;
	}

	public double getCurrentTotalPrice() {
		return currentTotalPrice;
	}

	public void setCurrentTotalPrice(double currentTotalPrice) {
		this.currentTotalPrice = currentTotalPrice;
	}
}
