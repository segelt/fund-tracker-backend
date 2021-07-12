package com.fundtracker.fundtrackerbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fundtracker.fundtrackerbackend.domain.Fund;

@Service
public interface IFundService {

	public List<Fund> fetchAllFunds();
	
	public Fund getFundByName(String FundName);
	
	public List<Fund> getAllMatchingFunds(String FundQuery);
	
}
