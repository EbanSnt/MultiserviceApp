package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Warranty;

public interface WarrantyRepository extends CrudRepository<Warranty, Long> {

	List<Warranty>findAll();
	List<Warranty>findAllByOrderByWarrantyDateAsc();
	List<Warranty>findAllByOrderByWarrantyDateDesc();
	List<Warranty>findAllByWarrantyDate(Date warrantyDate);
	Warranty findByProduct_NumberReceipt(String numberReceipt);
}
