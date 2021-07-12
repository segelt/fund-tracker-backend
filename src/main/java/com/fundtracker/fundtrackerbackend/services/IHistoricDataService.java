package com.fundtracker.fundtrackerbackend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fundtracker.fundtrackerbackend.domain.HistoricData;
import com.fundtracker.fundtrackerbackend.dto.DateValuePairDTO;
import com.fundtracker.fundtrackerbackend.dto.ISortedDataDTO;
import com.fundtracker.fundtrackerbackend.dto.OverallGainPairDTO;
import com.fundtracker.fundtrackerbackend.dto.PortfolioDTO;
import com.fundtracker.fundtrackerbackend.dto.ValueGainPairDTO;

@Service
public interface IHistoricDataService {

	public List<HistoricData> fetchOneYearTimeSpanData(String FundCode);
	
	public List<HistoricData> fetchFundDataBetweenDates(String FundCode, LocalDate startDate, LocalDate endDate); 
	
	public List<DateValuePairDTO> fetchFundSince(String FundCode, LocalDate startDate); 
	
	public Map<String, ValueGainPairDTO> getBatchFundGainRatio(Map<String, Object> fonMap);

	public Map<String, Map<String, List<OverallGainPairDTO>>> getBatchDistinctGainRatio(Map<String, Object> fonMap);
	
	public List<PortfolioDTO> getPortfolioSummary(Map<String, Object> fonMap);
	
	public HistoricData fetchLatestFundData(String FundCode);
	
	public List<ISortedDataDTO> SortDate(int mode, int asc);

	public float fetchFundValueAtDate(String FundCode, LocalDate exactDate);
}
