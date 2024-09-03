package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Warranty;
import com.example.demo.repositories.WarrantyRepository;

@Service
public class WarrantyService {

	@Autowired
	private WarrantyRepository warrantyRepo;
	
	// Devuelve todas las garantias
	public List<Warranty> allWarranties(){
		return warrantyRepo.findAll();
	}
	// Devuelve una garantia
	public Warranty warranty(Long id) {
		return warrantyRepo.findById(id).orElse(null);
	}
	// Crea una garantia
	public Warranty saveWarranty(Warranty warranty) {
		return warrantyRepo.save(warranty);
	}
	// Elimina una garantia
	public void deleteWarranty(Long id) {
		warrantyRepo.deleteById(id);
	}
	// Devuelve todas las garantias de acuerdo a su fecha, en orden ascendente
	public List<Warranty> allWarrantiesByDateAsc(){
		return warrantyRepo.findAllByOrderByWarrantyDateAsc();
	}
	// Devuelve todas las garantias de acuerdo a su fecha, en orden descendente
	public List<Warranty> allWarrantiesByDateDesc(){
		return warrantyRepo.findAllByOrderByWarrantyDateAsc();
	}
	// Devuelve todas las garantias de acuerdo a fecha especifica
	public List<Warranty> allWarrantiesByDate(Date date){
		return warrantyRepo.findAllByWarrantyDate(date);
	}
	// Devuelve una garantia de acuerdo a su numero de recibo
	public Warranty allWarrantiesByReceiptNumber(String numberReceipt){
		return warrantyRepo.findByProduct_NumberReceipt(numberReceipt);
	}
	
	
	
}
