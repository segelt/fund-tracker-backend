package com.fundtracker.fundtrackerbackend.dto;

import java.time.LocalDate;

public class DateValuePairDTO {
	float payDegeri;
	LocalDate tarih;
	
	public DateValuePairDTO(float payDegeri, LocalDate tarih) {
		super();
		this.payDegeri = payDegeri;
		this.tarih = tarih;
	}
	
	public DateValuePairDTO(){
		
	}

	public float getPayDegeri() {
		return payDegeri;
	}

	public void setPayDegeri(float payDegeri) {
		this.payDegeri = payDegeri;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}
	
	
	
}
