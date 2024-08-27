package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProductReceipt;
import com.example.demo.repositories.ProductReceiptRepository;

@Service
public class ProductReceiptService {

	@Autowired
	ProductReceiptRepository productRepo;
	
	//Devuelve todos los recibos
	public List<ProductReceipt>allProductReceipts(){
		return productRepo.findAll();
	}
	// Devuelve un recibo
	public ProductReceipt productReceipt(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	// Crea un recibo
	public ProductReceipt saveProductReceipt(ProductReceipt productReceipt) {
		return productRepo.save(productReceipt);
	}
	// Elimina un recibo
	public void deleteProductReceipt(Long id) {
		productRepo.deleteById(id);
	}
	// Devuelve todos los recibos de acuerdo a coincidencias en su numero
	public List<ProductReceipt>allProductReceiptByNumber(String number){
		return productRepo.findByNumberContaining(number);
	}
	// Devuelve todos los recibos de acuerdo a su producto
	public List<ProductReceipt>allProductReceiptByProduct(String product){
		return productRepo.findAllByProduct(product);
	}
	// Devuelve todos los recibos de acuerdo a quien lo recibio
	public List<ProductReceipt>allProductReceiptByReceivedBy(String receivedBy){
		return productRepo.findAllByReceivedBy(receivedBy);
	}
	// Devuelve todos los recibos de acuerdo a una fecha determinada
	public List<ProductReceipt>allProductReceiptByDate(Date date){
		return productRepo.findAllByReceiptDate(date);
	}
	// Devuelve todos los recibos de acuerdo a las fechas, en orden Ascendente
	public List<ProductReceipt>allProductReceiptByDateAsc(){
		return productRepo.findAllByOrderByReceiptDateAsc();
	}
	// Devuelve todos los recibos de acuerdo a las fechas, en orden Descendente
	public List<ProductReceipt>allProductReceiptByDateDesc(){
		return productRepo.findAllByOrderByReceiptDateDesc();
	}
}