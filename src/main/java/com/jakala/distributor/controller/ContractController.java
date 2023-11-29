package com.jakala.distributor.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.jakala.distributor.model.Contract;
import com.jakala.distributor.repository.ContractRepository;

@RestController
@RequestMapping("/distributor")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;

    // Insert new contract/contracts
    @PostMapping("/contracts/create")
    public ResponseEntity<List<Contract>> createContracts(@RequestBody List<Contract> contracts) {
    	for(Contract c:contracts)
			if(!c.isValid())
				return new ResponseEntity<List<Contract>>(HttpStatus.BAD_REQUEST);
    	try {
            contractRepository.saveAll(contracts);
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Search contracts by single or various filters
    @GetMapping("/contracts")
    public List<Contract> searchContracts(@RequestParam(required = false) String customerName,
                                          @RequestParam(required = false) LocalDate startDate,
                                          @RequestParam(required = false) String contractType,
                                          @RequestParam(required = false) String userType) {

        if (customerName != null || startDate != null || contractType != null || userType != null) {
            return contractRepository.findByFilters(customerName, startDate, contractType, userType);
        } else {
            return (List<Contract>) contractRepository.findAll();
        }
    }

    // Retrieves the contracts of one user
    @GetMapping("/users/{userId}/contracts")
    public List<Contract> getUserContracts(@PathVariable int userId) {
        return contractRepository.findByUserId(userId);
    }
}
