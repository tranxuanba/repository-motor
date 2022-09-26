package com.macOfBa.repository;

import com.macOfBa.model.Motor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMotorRepository extends PagingAndSortingRepository<Motor, Long> {

}
