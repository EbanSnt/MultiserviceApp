package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.PickUp;
import com.example.demo.repositories.PickUpRepository;

@Service
public class PickUpService {

	@Autowired
	PickUpRepository pickUpRepo;
	
	// Devuelve todos los retiros
	public List<PickUp>allPickUp(){
		return pickUpRepo.findAll();
	}
	// Devuelve un retiro
	public PickUp pickUp(Long id) {
		return pickUpRepo.findById(id).orElse(null);
	}
	// Crea un retiro
	public PickUp savePickUp(PickUp pickUp) {
		return pickUpRepo.save(pickUp);
	}
	// Elimina un retiro
	public void deletePickUp(Long id) {
		pickUpRepo.deleteById(id);
	}
	// Devuelve todos los retiros de acuerdo a su metodo de pago
	public List<PickUp>allPickUpByPaymentMethod(String paymentMethod){
		return pickUpRepo.findAllByPaymentMethod(paymentMethod);
	}
	// Devuelve todos los retiros de una determinada fecha
	public List<PickUp>allPickUpByDate(Date date){
		return pickUpRepo.findByPickupDate(date);
	}
	// Devuelve un retiro de acuerdo al numero de recibo
	public PickUp pickUpByReceipt(String numberReceipt) {
		return pickUpRepo.findByProduct_NumberReceipt(numberReceipt);
	}
	// Devuelve todos los retiros de acuerdo a su fecha, de forma ascendente
	public List<PickUp>allPickUpByDateAsc(){
		return pickUpRepo.findAllByOrderByPickupDateAsc();
	}
	// Devuelve todos los retiros de acuerdo a su fecha, de forma descendente
	public List<PickUp>allPickUpByDateDesc(){
		return pickUpRepo.findAllByOrderByPickupDateDesc();
	}
	// Devuelve todos los retiros de acuerdo si fueron retirados
	public List<PickUp>allPickUpByReceipt(boolean productReceipt){
		return pickUpRepo.findAllByProductReceipt(productReceipt);
	}
}