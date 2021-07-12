package com.fundtracker.fundtrackerbackend.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface ISortedDataDTO {

	String getfonkodu();
	String getfonadi();
	Double geteskibirimpaydegeri();
	Double getbirimpaydegeri();
	Double getdifference();
	Double getpercentagegain();
	
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate geteskiTarih();
	
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate gettarih();
	
}
