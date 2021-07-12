package com.fundtracker.fundtrackerbackend.resources;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundtracker.fundtrackerbackend.domain.HistoricData;
import com.fundtracker.fundtrackerbackend.dto.DateValuePairDTO;
import com.fundtracker.fundtrackerbackend.dto.HistoricalDataDTO;
import com.fundtracker.fundtrackerbackend.dto.ISortedDataDTO;
import com.fundtracker.fundtrackerbackend.dto.OverallGainPairDTO;
import com.fundtracker.fundtrackerbackend.dto.PortfolioDTO;
import com.fundtracker.fundtrackerbackend.services.HistoricDataService;
import com.fundtracker.fundtrackerbackend.utils.DateUtils;

@RestController
@RequestMapping("/api/historicdata")
public class HistoricDataResource {

	@Autowired
	HistoricDataService historicDataService;
	
    @Autowired
    SimpleDateFormat simpleDateFormat;
    
    @Autowired
    DateTimeFormatter dateTimeFormatter;
	
	/*
	 * Gets one year range of historic data. Deprecated
	 */
	@GetMapping("/s/{Kod}")
	public ResponseEntity<List<HistoricData>> getOneYearSpanHistoricData(@PathVariable String Kod) {
		List<HistoricData> dataCollection = historicDataService.fetchOneYearTimeSpanData(Kod);
		return new ResponseEntity<>(dataCollection, HttpStatus.OK);
	}
	
	/*
	 * Gets latest fund price
	 */
	@GetMapping("/latest")
	public ResponseEntity<HistoricData> getLatestFundPrice(@RequestBody Map<String, Object> fundMap) {
		
		try{
			String Kod = (String) fundMap.get("Kod");
			HistoricData latestPrice = historicDataService.fetchLatestFundData(Kod);
			return new ResponseEntity<>(latestPrice, HttpStatus.OK);	
		}catch(Exception ex){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Gets historic data of a single fund, between given date interval
	 */
	@GetMapping("/span")
	public ResponseEntity<List<HistoricData>> getSpanHistoricData(@RequestBody Map<String, Object> bodyMap){
		try {
			String Kod = (String) bodyMap.get("Kod");
						
			LocalDate beginningDate = LocalDate.parse((String) bodyMap.get("beg"), dateTimeFormatter);
			
			LocalDate endDate = LocalDate.parse((String) bodyMap.get("end"), dateTimeFormatter);
			
			List<HistoricData> dataCollection = historicDataService.fetchFundDataBetweenDates(Kod, beginningDate, endDate);
			return new ResponseEntity<>(dataCollection, HttpStatus.OK);	
		}
		catch(Exception ex){
			System.out.println(ex);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * Gets historic fund data, for a single fund with calculated beginning time intervals (0 = 1 wk, 1 = 1 mo, 2 = 3 mo, 3 = 1 yr, 4 = 3 yr..)
	 */
	@PostMapping("/single/v1")
	public ResponseEntity<List<DateValuePairDTO>> getSingleFundList(@RequestBody Map<String, Object> fonMap) {
		String fundCode = fonMap.keySet().toArray()[0].toString();
		int value = (int)fonMap.get(fonMap.keySet().toArray()[0]);
		LocalDate beginningDate = DateUtils.priorDateFetcher(value);
		List<DateValuePairDTO> timeValues = historicDataService.fetchFundSince(fundCode, beginningDate);
		return new ResponseEntity<>(timeValues, HttpStatus.OK);
	}

	/*
	 * Gets batch fund data, with calculated beginning time intervals (1 wk, 1 mo, 3 mo, 1 yr, 3 yr..)
	 */
	@PostMapping("/batch/v1")
	public ResponseEntity<Map<String, HistoricalDataDTO>> getBatchFundList(@RequestBody Map<String, Object> fonMap) {
		Map<String, HistoricalDataDTO> outputMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : fonMap.entrySet()) {
		    String fundCode = entry.getKey();
		    int value = (int)entry.getValue();
		    
		    LocalDate beginningDate = DateUtils.priorDateFetcher(value);
		    
		    List<DateValuePairDTO> timeValues = historicDataService.fetchFundSince(fundCode, beginningDate);
		    HistoricalDataDTO _historicalDataOutput = new HistoricalDataDTO(timeValues);
		    outputMap.put(fundCode, _historicalDataOutput);
		}
		
		return new ResponseEntity<>(outputMap, HttpStatus.OK);
	}
	
	/*
	 * Gets batch fund data, with any time beginning interval
	 */
	@GetMapping("/batch/v2")
	public ResponseEntity<Map<String, List<DateValuePairDTO>>> getBatchFundListUniqueTime(@RequestBody Map<String, Object> fonMap) {
		try{
			Map<String, List<DateValuePairDTO>> outputMap = new HashMap<>();
						
			for (Map.Entry<String, Object> entry : fonMap.entrySet()) {
			    String fundCode = entry.getKey();
				
				LocalDate beginningDate = LocalDate.parse((String) (String)entry.getValue(), dateTimeFormatter);
			    
			    List<DateValuePairDTO> timeValues = historicDataService.fetchFundSince(fundCode, beginningDate);
			    outputMap.put(fundCode, timeValues);
			}	
		    return new ResponseEntity<>(outputMap, HttpStatus.OK);
		}catch(Exception ex){
			System.out.println(ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/batch/gains")
	public ResponseEntity<Map<String, Map<String, List<OverallGainPairDTO>>>> getFundGains(@RequestBody Map<String, Object> fonMap){
		Map<String, Map<String, List<OverallGainPairDTO>>> gains = historicDataService.getBatchDistinctGainRatio(fonMap);
		return new ResponseEntity<>(gains, HttpStatus.OK);
	}
	
	@GetMapping("/priceFromDate")
	public ResponseEntity<Float> getPriceValueFromFundCodeAndDate(@RequestParam("fundCode") String FundCode, @RequestParam("Date") String dateInStr){
		try {
			LocalDate beginningDate = LocalDate.parse(dateInStr, dateTimeFormatter);
			float returnValue = historicDataService.fetchFundValueAtDate(FundCode, beginningDate);
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Deprecated
	@PostMapping("/batch/portfolio")
	public ResponseEntity<List<PortfolioDTO>> getPortfolioSummary(@RequestBody Map<String, Object> fonMap){
		List<PortfolioDTO> gains = historicDataService.getPortfolioSummary(fonMap);
		//System.out.println(gains);
		return new ResponseEntity<>(gains, HttpStatus.OK);
	}
	
	@PostMapping("/sort")
	public ResponseEntity<List<ISortedDataDTO>> getSortedSummary(@RequestBody Map<String, Object> sortMap){
		try{
			int sortInterval = Integer.parseInt(sortMap.get("TimeInterval").toString());
			int sortMode = Integer.parseInt(sortMap.get("Kod").toString());

			//Validation
			if(sortInterval < 0 || sortInterval > 6) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			List<ISortedDataDTO> sortedResults = historicDataService.SortDate(sortInterval, sortMode);

			sortedResults = sortedResults.stream().filter(p -> p.getpercentagegain() != null 
					&& p.getpercentagegain() > -100 
					&& p.getbirimpaydegeri() != null && p.getbirimpaydegeri() > 0)
					.limit(40)
					.collect(Collectors.toList());
			
			return new ResponseEntity<>(sortedResults, HttpStatus.OK);	
		}catch(Exception ex){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
