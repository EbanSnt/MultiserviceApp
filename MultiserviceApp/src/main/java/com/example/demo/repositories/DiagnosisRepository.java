package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Diagnosis;

@Repository
public interface DiagnosisRepository  extends CrudRepository<Diagnosis, Long>{

	List <Diagnosis>findAll();
	List <Diagnosis>findAllByOrderByRepairPriceAsc();
	List <Diagnosis>findAllByOrderByRepairPriceDesc();
	List <Diagnosis>findAllByOrderByDiagnosisDateAsc();
	List <Diagnosis>findAllByOrderByDiagnosisDateDesc();
	List<Diagnosis> findByCommunicationDate(Date diagnosisDate);
	Diagnosis findByProduct_NumberReceipt(String numberReceipt);
}
