package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Communications;

@Repository
public interface CommunicationsRepository extends CrudRepository<Communications, Long> {

	List <Communications>findAll();
	List <Communications>findAllByOrderByCommunicationDateAsc();
	List <Communications>findAllByOrderByCommunicationDateDesc();
	List<Communications> findByCommunicationType(String communicationType);
	List<Communications> findByCommunicationDate(Date communicationDate);
	Communications findByProduct_NumberReceipt(String numberReceipt);
}
