package com.jakala.distributor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jakala.distributor.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
