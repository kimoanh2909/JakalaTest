package com.jakala.distributor.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jakala.distributor.model.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Integer> {
    
	@Query("SELECT c FROM Contract c WHERE " +
           "(:customerName IS NULL OR c.user.name LIKE %:customerName%) " +
           "AND (:startDate IS NULL OR c.startDate = :startDate) " +
           "AND (:contractType IS NULL OR c.contractType = :contractType) " +
           "AND (:userType IS NULL OR c.user.userType = :userType)")
	
    List<Contract> findByFilters(@Param("customerName") String customerName,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("contractType") String contractType,
                                 @Param("userType") String userType);
    
    List<Contract> findByUserId(int userId);
}
