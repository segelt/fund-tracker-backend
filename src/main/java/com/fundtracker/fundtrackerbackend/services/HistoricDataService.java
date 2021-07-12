package com.fundtracker.fundtrackerbackend.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fundtracker.fundtrackerbackend.domain.HistoricData;
import com.fundtracker.fundtrackerbackend.dto.DateValuePairDTO;
import com.fundtracker.fundtrackerbackend.dto.ISortedDataDTO;
import com.fundtracker.fundtrackerbackend.dto.LatestFundSummaryDTO;
import com.fundtracker.fundtrackerbackend.dto.OverallGainPairDTO;
import com.fundtracker.fundtrackerbackend.dto.PortfolioDTO;
import com.fundtracker.fundtrackerbackend.dto.ValueGainPairDTO;
import com.fundtracker.fundtrackerbackend.repositories.HistoricDataRepository;

@Service
@Transactional
public class HistoricDataService implements IHistoricDataService {

    @Autowired
    HistoricDataRepository historicDataRepository;
	
    @Autowired
    SimpleDateFormat simpleDateFormat;
    
    @Autowired
    DateTimeFormatter dateTimeFormatter;
    
	@Override
	public List<HistoricData> fetchOneYearTimeSpanData(String FundCode) {
		return historicDataRepository.findAllByFonKodu(FundCode);
	}
	
	@Override
	public List<HistoricData> fetchFundDataBetweenDates(String FundCode, LocalDate startDate, LocalDate endDate) {
		return historicDataRepository.findAllByFonKoduAndTarihGreaterThanEqualAndTarihLessThanEqual(FundCode, startDate, endDate);
	}

	@Override
	public List<DateValuePairDTO> fetchFundSince(String FundCode, LocalDate startDate) {
		return historicDataRepository.
				findAllByFonKoduAndTarihGreaterThanEqual(FundCode, startDate)
				.stream()
				.map(data -> new DateValuePairDTO(data.getBirimPayDegeri(), data.getTarih()))
				.collect(Collectors.toList());
	}
	
	@Override
	public float fetchFundValueAtDate(String FundCode, LocalDate exactDate) {
		HistoricData _RetrievedData = historicDataRepository.findByFonKoduAndTarihExact(FundCode, exactDate);
		
		if(_RetrievedData == null) return -1;
		return _RetrievedData.getBirimPayDegeri();
	}

	/* historicdata/batch/v3
	 * (non-Javadoc)
	 * @see com.fundtracker.fundtrackerbackend.services.IHistoricDataService#getBatchFundGainRatio(java.util.Map)
	 * Deprecated -- not used.
	 */
	@Override
	public Map<String, ValueGainPairDTO> getBatchFundGainRatio(Map<String, Object> dataMap) {
		//Parse the input
		try {
			Map<String, ValueGainPairDTO> serviceMap = new HashMap<>();
			
			//For each fund
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			    String fundCode = entry.getKey();
			    LinkedHashMap<?, ?> valueSet = (LinkedHashMap<?, ?>) entry.getValue();
			    //String as Date, String as float
			    
			    double totalInvested = 0;
			    double totalSharesBought = 0;
			    
			    for(Map.Entry<?, ?> dateValuePairs : valueSet.entrySet()){
			    
				    Date pairDate = new Date(simpleDateFormat.
				    		parse((String)dateValuePairs.getKey()).getTime());

				    ArrayList<?> values = (ArrayList<?>)dateValuePairs.getValue();

				    double fundPriceAtThatTime = (double)values.get(0);
				    double countBought = (double)(Integer)(values.get(1));

				    totalInvested += fundPriceAtThatTime * countBought;
				    totalSharesBought += countBought;
			    }

			    HistoricData latestFundData = this.fetchLatestFundData(fundCode);
			    
			    double currentTotalInvestment = latestFundData.getBirimPayDegeri() * totalSharesBought;
			    
			    //Calculate gain amount, and gain ratio
			    double gains = currentTotalInvestment - totalInvested;
			    double gainRatio = (gains / totalInvested) * 100;

			    serviceMap.put(fundCode, null);
			}
			return serviceMap;
		}catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public HistoricData fetchLatestFundData(String FundCode) {
		
		return historicDataRepository
				.findFirstByFonKoduOrderByTarihDesc(FundCode);
	}

	/* historicdata/batch/v4
	 * (non-Javadoc)
	 * @see com.fundtracker.fundtrackerbackend.services.IHistoricDataService#getBatchDistinctGainRatio(java.util.Map)
	 */
	@Override
	public Map<String, Map<String, List<OverallGainPairDTO>>> getBatchDistinctGainRatio(Map<String, Object> dataMap) {
		//First, parse the input
		try {
			Map<String, Map<String, List<OverallGainPairDTO>>> serviceMap = new HashMap<>();
						
			//For each fund
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			    String fundCode = entry.getKey();
			    HistoricData latestFundData = this.fetchLatestFundData(fundCode);
			    
			    float _LatestFundPrice = latestFundData.getBirimPayDegeri();
			    
			    LinkedHashMap<?, ?> valueSet = (LinkedHashMap<?, ?>) entry.getValue();
			    //String as Date, String as float
			
			    Map<String, List<OverallGainPairDTO>> gainsMap = new HashMap<>();
			    List<OverallGainPairDTO> itemList = null;
			    //For each date
			    for(Map.Entry<?, ?> dateValuePairs : valueSet.entrySet()){
			    
				    /*Date pairDate = new Date(new SimpleDateFormat("dd-MM-yyyy").
				    		parse((String)dateValuePairs.getKey()).getTime());*/
			    	LocalDate pairDate = LocalDate.parse((String)dateValuePairs.getKey(), dateTimeFormatter);
				    
			    	/*float pairETFValue = Float.parseFloat((String)dateValuePairs.getValue());*/
				    //Log: pairDate, (ArrayList<Float>)dateValuePairs.getValue());
				    
			    	ArrayList<?> values = (ArrayList<?>)dateValuePairs.getValue();

				    double fundPriceAtThatTime = (double)values.get(0);
				    double countBought = (double)(Integer)(values.get(1));

				    double totalInvested = fundPriceAtThatTime * countBought;
				    double currentTotalInvestment = latestFundData.getBirimPayDegeri() * countBought;    

				    //calculate gain amount, and gain ratio
				    double gains = currentTotalInvestment - totalInvested;
				    double gainRatio = (gains / totalInvested) * 100;
				    
				    OverallGainPairDTO itemToBeAdded = new OverallGainPairDTO(pairDate, 
				    		countBought,
				    		totalInvested,
				    		fundPriceAtThatTime,
				    		gains,
				    		gainRatio,
				    		_LatestFundPrice,
				    		currentTotalInvestment
				    		);
				    
				    itemList = gainsMap.get("distinct");
				    
				    //Add this item to an existing collection
				    if(itemList == null){
				    	itemList = new ArrayList<OverallGainPairDTO>();
				    	gainsMap.put("distinct", itemList);
				    }
				    itemList.add(itemToBeAdded);
			    }
			    //Add overall to gainsMap
			    
			    double totalPrice = itemList
						.stream()
						.mapToDouble(i -> i.getFundValue() * i.getTotalSharesOwned()).sum();
				
			    double totalSharesOwned = itemList.stream().mapToDouble(item -> item.getTotalSharesOwned()).sum();
				double amountGained = itemList.stream().mapToDouble(item -> item.getAmountGained()).sum();
				double amountPaid = itemList.stream().mapToDouble(item -> item.getTotalSharesOwned() * item.getFundValue()).sum();
				double totalGainRatio = amountGained * 100/ amountPaid;
			    
				OverallGainPairDTO overall = new OverallGainPairDTO(null, 
						totalSharesOwned,
						totalPrice,
						-1,
						amountGained,
						totalGainRatio,
						_LatestFundPrice,
						_LatestFundPrice * totalSharesOwned
						);
				List<OverallGainPairDTO> overallCollection = new ArrayList<OverallGainPairDTO>();
				overallCollection.add(overall);
				
				gainsMap.put("overall", overallCollection);
			    
			    serviceMap.put(fundCode, gainsMap);
			    
			}
			return serviceMap;
		}catch(Exception ex){
			System.out.println(ex);
			return null;
			
		}
	}

	@Override
	public List<PortfolioDTO> getPortfolioSummary(Map<String, Object> fonMap) {
		//First, parse the input
		try {
			List<PortfolioDTO> portfolioOfEachFund = new ArrayList<PortfolioDTO>();
			
			for (Map.Entry<String, Object> entry : fonMap.entrySet()) {
				
			    String fundCode = entry.getKey();
			    List<LatestFundSummaryDTO> latestInfo = historicDataRepository.findLatestInfoAboutFund(fundCode, 
			    		PageRequest.of(0, 1));
			    
			    if(latestInfo == null || latestInfo.size() != 1) {
			    	//handle error...
			    	return null;
			    }
			    
			    LatestFundSummaryDTO latestFundSummary = latestInfo.get(0);
			    PortfolioDTO thisAccount = new PortfolioDTO();
			    thisAccount.setFundType(latestFundSummary.getFonTuru());
			    
			    //[0.102 , 1000] will be parsed as value
			    LinkedHashMap<?, ?> valueSet = (LinkedHashMap<?, ?>) entry.getValue();
			    
			    double totalInvested = 0;
			    for(Map.Entry<?, ?> dateValuePairs : valueSet.entrySet()){
			    
				    Date pairDate = new Date(simpleDateFormat.
				    		parse((String)dateValuePairs.getKey()).getTime());
				    
				    ArrayList<?> values = (ArrayList<?>)dateValuePairs.getValue();
				    
				    double fundPriceAtThatTime = (double)values.get(0);
				    double countBought = (double)(Integer)(values.get(1));

				    totalInvested += fundPriceAtThatTime * countBought;
			    }
			    thisAccount.setTotalPrice(totalInvested);
			    thisAccount.setPercentageTotal(0);
			    
			    portfolioOfEachFund.add(thisAccount);
			}
			
			double totalPrice = portfolioOfEachFund
					.stream()
					.mapToDouble(i -> i.getTotalPrice()).sum();
			
			portfolioOfEachFund = portfolioOfEachFund
					.stream()
					.collect(Collectors.groupingBy(obj -> obj.getFundType()))
					.values()
					.stream() // stream of List<PortfolioDTO>
					.map(item -> { //one collection of same type funds
						PortfolioDTO reduced = item
								.stream()
								.reduce((item1, item2) -> new PortfolioDTO(item1.getTotalPrice() + item2.getTotalPrice(), item1.getFundType(), 0))
								.get();
								
						reduced.setPercentageTotal(reduced.getTotalPrice() / totalPrice);
						return reduced;
					}).collect(Collectors.toList());
			return portfolioOfEachFund;
		}catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	}

	
	@Override
	public List<ISortedDataDTO> SortDate(int mode, int asc) {
		String intervalDif = "";
		switch(mode){
		case 0:
			intervalDif = "'1 day'";
			break;
		case 1:
			intervalDif = "'1 week'";
			break;
		case 2:
			intervalDif = "'2 week'";
			break;
		case 3:
			intervalDif = "'1 month'";
			break;
		case 4:
			intervalDif = "'3 month'";
			break;
		case 5:
			intervalDif = "'1 year'";
			break;
		case 6:
			intervalDif = "'3 year'";
			break;
		default:
			//todo: handle exception
			intervalDif = "'3 year'";
			break;
		}
		
		try {
			if(asc == 0) return historicDataRepository.GetSortListASC(intervalDif, 200);
			else return historicDataRepository.GetSortListDESC(intervalDif, 200);
		}catch(Exception ex){
			System.out.println(ex);
			return null;
		}
		
	}
}
