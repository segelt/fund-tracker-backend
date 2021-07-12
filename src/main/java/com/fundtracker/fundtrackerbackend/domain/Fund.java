package com.fundtracker.fundtrackerbackend.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Fon")
public class Fund {

	@Id
	@Column(name = "FonKodu")
	private String fonKodu;
	
	@Column(name = "companyType")
	private int companyType;
	
	@OneToMany(fetch= FetchType.LAZY)
	@JoinColumn(name="FonKodu")
	private List<HistoricData> historyData;
	
	@Column(name = "FonAdi")
	private String fonAdi;
	
	@Column(name = "FonTuru")
	private String fonTuru;
	
	@Column(name = "FonTipi")
	private String fonTipi;
	
	public Fund(){
		companyType = -1;
		fonKodu = "def";
		fonAdi = "def";
		fonTuru = "def";
		fonTipi = "def";
	}
	public Fund(int companyType, String FonKodu, String FonAdi, String FonTuru, String FonTipi, List<HistoricData> historyData){
		this.companyType = companyType;
		this.fonKodu = FonKodu;
		this.fonAdi = FonAdi;
		this.fonTuru = FonTuru;
		this.fonTipi = FonTipi;
	}
	
	public int getCompanyType() {
		return companyType;
	}
	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}
	
	public String getFonKodu() {
		return fonKodu;
	}
	public void setFonKodu(String fonKodu) {
		this.fonKodu = fonKodu;
	}
	
	public String getFonAdi() {
		return fonAdi;
	}
	public void setFonAdi(String fonAdi) {
		this.fonAdi = fonAdi;
	}
	
	public String getFonTuru() {
		return fonTuru;
	}
	public void setFonTuru(String fonTuru) {
		this.fonTuru = fonTuru;
	}
	
	public String getFonTipi() {
		return fonTipi;
	}
	public void setFonTipi(String fonTipi) {
		this.fonTipi = fonTipi;
	}
	
}
