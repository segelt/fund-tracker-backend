package com.fundtracker.fundtrackerbackend.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fundtracker.fundtrackerbackend.domain.Fund;
import com.fundtracker.fundtrackerbackend.services.FundService;

@RestController
@RequestMapping("/api/funds")
public class FundResource {
	
	@Autowired
	FundService fundService;
	

	/*
	 * Gets a list of all fund names. Deprecated
	 */
	@GetMapping("")
	public ResponseEntity<List<Fund>> getAllFunds() {
		List<Fund> funds = fundService.fetchAllFunds();
		return new ResponseEntity<>(funds, HttpStatus.OK);
	}
	
	/*
	 * Get information about a single (exact) fund
	 */
	@PostMapping("/v1")
	public ResponseEntity<Fund> getExactFund(@RequestBody Map<String, Object> fundMap) {
		String Kod = (String) fundMap.get("Kod");
		Fund funds = fundService.getFundByName(Kod);
		return new ResponseEntity<>(funds, HttpStatus.OK);
	}
	
	/*
	 * Get a list of funds matching specified input
	 */
	@RequestMapping(value = "/v2", method=RequestMethod.GET)
	public 	@ResponseBody List<Fund> getAllMatchingFunds(@RequestParam("Kod") String Kod) {
		List<Fund> funds = fundService.getAllMatchingFunds(Kod);
		return funds;
	}
	
}
 
