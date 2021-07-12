package com.fundtracker.fundtrackerbackend.dto;

import java.time.LocalDate;

public class ValueGainPairDTO {
	private LocalDate dateAcquired;
	private double totalSharesOwned;
	private double fundValue;
	private double amountGained;
	private double gainRatio;
	
	
	public ValueGainPairDTO(){
		super();
	}
	
	public ValueGainPairDTO(LocalDate dateAcquired, double totalSharesOwned, double fundValue, double amountGained,
			double gainRatio) {
		super();
		this.dateAcquired = dateAcquired;
		this.totalSharesOwned = totalSharesOwned;
		this.fundValue = fundValue;
		this.amountGained = amountGained;
		this.gainRatio = gainRatio;
	}
	
	
	public LocalDate getDateAcquired() {
		return dateAcquired;
	}
	public void setDateAcquired(LocalDate dateAcquired) {
		this.dateAcquired = dateAcquired;
	}
	public double getTotalSharesOwned() {
		return totalSharesOwned;
	}
	public void setTotalSharedOwned(double totalSharesOwned) {
		this.totalSharesOwned = totalSharesOwned;
	}
	public double getFundValue() {
		return fundValue;
	}
	public void setFundValue(double fundValue) {
		this.fundValue = fundValue;
	}
	public double getAmountGained() {
		return amountGained;
	}
	public void setAmountGained(double amountGained) {
		this.amountGained = amountGained;
	}
	public double getGainRatio() {
		return gainRatio;
	}
	public void setGainRatio(double gainRatio) {
		this.gainRatio = gainRatio;
	}
	
	
	
	
}
