package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PickUp;

@Repository
public interface PickUpRepository extends CrudRepository<PickUp,Long>{

	List<PickUp>findAll();
	List<PickUp> findByPickupDate(Date pickupDate);
	List<PickUp>findAllByPaymentMethod(String paymentMethod);
	PickUp findByProduct_NumberReceipt(String numberReceipt);
	List<PickUp>findAllByOrderByPickupDateAsc();
	List<PickUp>findAllByOrderByPickupDateDesc();
	List<PickUp>findAllByProductReceipt(boolean productReceipt);
}
