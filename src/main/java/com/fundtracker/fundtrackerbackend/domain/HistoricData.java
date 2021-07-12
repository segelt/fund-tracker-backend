package com.fundtracker.fundtrackerbackend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "HistoricData")
@IdClass(FundCodeKey.class)
public class HistoricData{
	@Id
	@Column(name= "FonKodu", insertable=false, updatable=false)
	private String fonKodu;
	
	@Id
	private LocalDate tarih;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="FonKodu")
	private Fund fund;
	
	@Column(name = "ToplamDeger")
	private BigDecimal toplamDeger;
	
	@Column(name = "BirimPayDegeri")
	private float birimPayDegeri;
	
	@Column(name = "DolasimdakiPaySayisi")
	@ColumnDefault("0")
	private BigDecimal dolasimdakiPaySayisi;
	
	@Column(name = "YatirimciSayisi")
	@ColumnDefault("0")
	private long yatirimciSayisi;
	
	@Column(name = "BankaBonosu")
	@ColumnDefault("0")
	private float bankaBonosu;
	
	@Column(name = "Diger")
	@ColumnDefault("0")
	private float diger;
	
	@Column(name = "DevletTahvili")
	@ColumnDefault("0")
	private float devletTahvili;
	
	@Column(name = "DovizOdemeliBono")
	@ColumnDefault("0")
	private float dovizOdemeliBono;
	
	@Column(name = "DovizOdemeliTahvil")
	@ColumnDefault("0")
	private float dovizOdemeliTahvil;
	
	@Column(name = "Eurobond")
	@ColumnDefault("0")
	private float eurobond;
	
	@Column(name = "FinansmanBonosu")
	@ColumnDefault("0")
	private float finansmanBonosu;
	
	@Column(name = "FonKatilmaBelgesi")
	@ColumnDefault("0")
	private float fonKatilmaBelgesi;
	
	@Column(name = "GayrimenkulSertifikasi")
	@ColumnDefault("0")
	private float gayrimenkulSertifikasi;
	
	@Column(name = "HazineBonosu")
	@ColumnDefault("0")
	private float hazineBonosu;
	
	@Column(name = "HisseSenedi")
	@ColumnDefault("0")
	private float hisseSenedi;
	
	@Column(name = "KamuDisBorclanmaAraci")
	@ColumnDefault("0")
	private float kamuDisBorclanmaAraci;
	
	@Column(name = "KamuKiraSertifikasi")
	@ColumnDefault("0")
	private float kamuKiraSertifikasi;
	
	@Column(name = "KatilimHesabi")
	@ColumnDefault("0")
	private float katilimHesabi;
	
	@Column(name = "KiymetliMaden")
	@ColumnDefault("0")
	private float kiymetliMaden;
	
	@Column(name = "OzelSektorKiraSertifikasi")
	@ColumnDefault("0")
	private float ozelSektorKiraSertifikasi;
	
	@Column(name = "OzelSektorTahvili")
	@ColumnDefault("0")
	private float ozelSektorTahvili;
	
	@Column(name = "TersRepo")
	@ColumnDefault("0")
	private float tersRepo;

	@Column(name = "TPP")
	@ColumnDefault("0")
	private float tPP;
	
	@Column(name = "TurevAraci")
	@ColumnDefault("0")
	private float turevAraci;
	
	@Column(name = "VarligaDayaliMenkulKiymet")
	@ColumnDefault("0")
	private float varligaDayaliMenkulKiymet;
	
	@Column(name = "VadeliMevduat")
	@ColumnDefault("0")
	private float vadeliMevduat;
	
	@Column(name = "YabanciBorclanmaAraci")
	@ColumnDefault("0")
	private float yabanciBorclanmaAraci;
	
	@Column(name = "YabanciHisseSenedi")
	@ColumnDefault("0")
	private float yabanciHisseSenedi;

	@Column(name = "YabanciMenkulKiymet")
	@ColumnDefault("0")
	private float yabanciMenkulKiymet;
	
	
	public HistoricData(){	}


	public String getFonKodu() {
		return fonKodu;
	}
	public void setFonKodu(String fonKodu) {
		this.fonKodu = fonKodu;
	}

	public LocalDate getTarih() {
		return tarih;
	}
	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}

	public BigDecimal getToplamDeger() {
		return toplamDeger;
	}
	public void setToplamDeger(BigDecimal toplamDeger) {
		this.toplamDeger = toplamDeger;
	}


	public float getBirimPayDegeri() {
		return birimPayDegeri;
	}
	public void setBirimPayDegeri(float birimPayDegeri) {
		this.birimPayDegeri = birimPayDegeri;
	}


	public BigDecimal getDolasimdakiPaySayisi() {
		return dolasimdakiPaySayisi;
	}
	public void setDolasimdakiPaySayisi(BigDecimal dolasimdakiPaySayisi) {
		this.dolasimdakiPaySayisi = dolasimdakiPaySayisi;
	}


	public long getYatirimciSayisi() {
		return yatirimciSayisi;
	}
	public void setYatirimciSayisi(long yatirimciSayisi) {
		this.yatirimciSayisi = yatirimciSayisi;
	}


	public float getBankaBonosu() {
		return bankaBonosu;
	}
	public void setBankaBonosu(float bankaBonosu) {
		this.bankaBonosu = bankaBonosu;
	}


	public float getDiger() {
		return diger;
	}
	public void setDiger(float diger) {
		this.diger = diger;
	}


	public float getDevletTahvili() {
		return devletTahvili;
	}
	public void setDevletTahvili(float devletTahvili) {
		this.devletTahvili = devletTahvili;
	}


	public float getDovizOdemeliBono() {
		return dovizOdemeliBono;
	}
	public void setDovizOdemeliBono(float dovizOdemeliBono) {
		this.dovizOdemeliBono = dovizOdemeliBono;
	}


	public float getDovizOdemeliTahvil() {
		return dovizOdemeliTahvil;
	}
	public void setDovizOdemeliTahvil(float dovizOdemeliTahvil) {
		this.dovizOdemeliTahvil = dovizOdemeliTahvil;
	}


	public float getEurobond() {
		return eurobond;
	}
	public void setEurobond(float eurobond) {
		this.eurobond = eurobond;
	}


	public float getFinansmanBonosu() {
		return finansmanBonosu;
	}
	public void setFinansmanBonosu(float finansmanBonosu) {
		this.finansmanBonosu = finansmanBonosu;
	}


	public float getFonKatilmaBelgesi() {
		return fonKatilmaBelgesi;
	}
	public void setFonKatilmaBelgesi(float fonKatilmaBelgesi) {
		this.fonKatilmaBelgesi = fonKatilmaBelgesi;
	}


	public float getGayrimenkulSertifikasi() {
		return gayrimenkulSertifikasi;
	}
	public void setGayrimenkulSertifikasi(float gayrimenkulSertifikasi) {
		this.gayrimenkulSertifikasi = gayrimenkulSertifikasi;
	}


	public float getHazineBonosu() {
		return hazineBonosu;
	}
	public void setHazineBonosu(float hazineBonosu) {
		this.hazineBonosu = hazineBonosu;
	}


	public float getHisseSenedi() {
		return hisseSenedi;
	}
	public void setHisseSenedi(float hisseSenedi) {
		this.hisseSenedi = hisseSenedi;
	}


	public float getKamuDisBorclanmaAraci() {
		return kamuDisBorclanmaAraci;
	}
	public void setKamuDisBorclanmaAraci(float kamuDisBorclanmaAraci) {
		this.kamuDisBorclanmaAraci = kamuDisBorclanmaAraci;
	}


	public float getKamuKiraSertifikasi() {
		return kamuKiraSertifikasi;
	}
	public void setKamuKiraSertifikasi(float kamuKiraSertifikasi) {
		this.kamuKiraSertifikasi = kamuKiraSertifikasi;
	}


	public float getKatilimHesabi() {
		return katilimHesabi;
	}
	public void setKatilimHesabi(float katilimHesabi) {
		this.katilimHesabi = katilimHesabi;
	}


	public float getKiymetliMaden() {
		return kiymetliMaden;
	}
	public void setKiymetliMaden(float kiymetliMaden) {
		this.kiymetliMaden = kiymetliMaden;
	}


	public float getOzelSektorKiraSertifikasi() {
		return ozelSektorKiraSertifikasi;
	}
	public void setOzelSektorKiraSertifikasi(float ozelSektorKiraSertifikasi) {
		this.ozelSektorKiraSertifikasi = ozelSektorKiraSertifikasi;
	}


	public float getOzelSektorTahvili() {
		return ozelSektorTahvili;
	}
	public void setOzelSektorTahvili(float ozelSektorTahvili) {
		this.ozelSektorTahvili = ozelSektorTahvili;
	}


	public float getTersRepo() {
		return tersRepo;
	}
	public void setTersRepo(float tersRepo) {
		this.tersRepo = tersRepo;
	}


	public float getTPP() {
		return tPP;
	}
	public void setTPP(float tPP) {
		this.tPP = tPP;
	}


	public float getTurevAraci() {
		return turevAraci;
	}
	public void setTurevAraci(float turevAraci) {
		this.turevAraci = turevAraci;
	}


	public float getVarligaDayaliMenkulKiymet() {
		return varligaDayaliMenkulKiymet;
	}
	public void setVarligaDayaliMenkulKiymet(float varligaDayaliMenkulKiymet) {
		this.varligaDayaliMenkulKiymet = varligaDayaliMenkulKiymet;
	}


	public float getVadeliMevduat() {
		return vadeliMevduat;
	}
	public void setVadeliMevduat(float vadeliMevduat) {
		this.vadeliMevduat = vadeliMevduat;
	}


	public float getYabanciBorclanmaAraci() {
		return yabanciBorclanmaAraci;
	}
	public void setYabanciBorclanmaAraci(float yabanciBorclanmaAraci) {
		this.yabanciBorclanmaAraci = yabanciBorclanmaAraci;
	}


	public float getYabanciHisseSenedi() {
		return yabanciHisseSenedi;
	}
	public void setYabanciHisseSenedi(float yabanciHisseSenedi) {
		this.yabanciHisseSenedi = yabanciHisseSenedi;
	}


	public float getYabanciMenkulKiymet() {
		return yabanciMenkulKiymet;
	}
	public void setYabanciMenkulKiymet(float yabanciMenkulKiymet) {
		this.yabanciMenkulKiymet = yabanciMenkulKiymet;
	}
}
