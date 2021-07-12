package com.fundtracker.fundtrackerbackend.dto;

public class PortfolioDTO {
	double totalPrice;
	String fundType;
	double percentageTotal;
	
	public PortfolioDTO(double totalPrice, String fundType, double percentageTotal) {
		super();
		this.totalPrice = totalPrice;
		this.fundType = fundType;
		this.percentageTotal = percentageTotal;
	}

	public PortfolioDTO(){
		
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	
	public double getPercentageTotal() {
		return percentageTotal;
	}
	public void setPercentageTotal(double percentageTotal) {
		this.percentageTotal = percentageTotal;
	}
	
	@Override
	public String toString() {
		return "PortfolioDTO [totalPrice=" + totalPrice + ", fundType=" + fundType + ", percentageTotal="
				+ percentageTotal + "]";
	}
}
