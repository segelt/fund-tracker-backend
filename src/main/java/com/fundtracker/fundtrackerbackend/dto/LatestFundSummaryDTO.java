package com.fundtracker.fundtrackerbackend.dto;

import java.time.LocalDate;

public class LatestFundSummaryDTO {
	
	private String fonKodu;
	private float birimPayDegeri;
	private LocalDate tarih;
	private String fonTuru;
		
	public LatestFundSummaryDTO() {
		
	}

	public LatestFundSummaryDTO(String fonKodu, float birimPayDegeri, LocalDate tarih, String fonTuru) {
		this.fonKodu = fonKodu;
		this.birimPayDegeri = birimPayDegeri;
		this.tarih = tarih;
		this.fonTuru = fonTuru;
	}

	public String getFonKodu() {
		return fonKodu;
	}

	public void setFonKodu(String fonKodu) {
		this.fonKodu = fonKodu;
	}

	public float getBirimPayDegeri() {
		return birimPayDegeri;
	}

	public void setBirimPayDegeri(float birimPayDegeri) {
		this.birimPayDegeri = birimPayDegeri;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}

	public String getFonTuru() {
		return fonTuru;
	}

	public void setFonTuru(String fonTuru) {
		this.fonTuru = fonTuru;
	}
	
	
	
}
