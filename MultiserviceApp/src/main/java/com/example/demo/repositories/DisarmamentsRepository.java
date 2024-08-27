package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.models.Disarmaments;

@Repository
public interface DisarmamentsRepository extends CrudRepository<Disarmaments, Long> {

	List <Disarmaments>findAll();
	List <Disarmaments>findAllByOrderByDisarmamentDateAsc();
	List <Disarmaments>findAllByOrderByDisarmamentDateDesc();
	List<Disarmaments> findByDisarmamentDate(Date disarmamentDate);
	Disarmaments findByProduct_NumberReceipt(String numberReceipt);
}

