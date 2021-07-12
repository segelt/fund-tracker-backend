package com.fundtracker.fundtrackerbackend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundtracker.fundtrackerbackend.domain.Fund;
import com.fundtracker.fundtrackerbackend.repositories.FundRepository;

@Service
@Transactional
public class FundService implements IFundService {

    @Autowired
    FundRepository fundRepository;
	
	@Override
	public List<Fund> fetchAllFunds() {
		return fundRepository.findAll();
	}

	@Override
	public Fund getFundByName(String FundName) {
		return fundRepository.findFundByFonKoduIgnoreCase(FundName);
	}
	
	@Override
	public List<Fund> getAllMatchingFunds(String FundQuery) {
		return fundRepository.findByFonKoduIgnoreCaseContaining(FundQuery);
	}

}
