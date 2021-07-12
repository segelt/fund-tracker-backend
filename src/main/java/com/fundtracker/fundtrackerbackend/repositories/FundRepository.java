package com.fundtracker.fundtrackerbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundtracker.fundtrackerbackend.domain.Fund;

@Repository
public interface FundRepository extends JpaRepository<Fund, String>{
	public Fund findFundByFonKoduIgnoreCase(String FonKodu);
	
	public List<Fund> findByFonKoduIgnoreCaseContaining (String fonQuery);
}
