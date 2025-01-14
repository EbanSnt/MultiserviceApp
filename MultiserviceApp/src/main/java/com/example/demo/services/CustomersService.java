package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customers;
import com.example.demo.repositories.CustomersRepository;

@Service
public class CustomersService {

	@Autowired
	CustomersRepository customersRepo;
	
	// Devuelve todos las clientes
	public List<Customers> allCostumers(){
		return customersRepo.findAll();
	}
	// Devuelve un cliente
	public Customers customers(Long id) {
		return customersRepo.findById(id).orElse(null);
	}
	// Guarda un cliente
	public Customers saveCustomer(Customers customer) {
		return customersRepo.save(customer);
	}
	// Elimina un cliente
	public void deleteCustomer(Long customerId) {
		customersRepo.deleteById(customerId);
	}
	// Devuelve todos los clientes de acuerdo a la coincidencia de sus nombres
	public List<Customers> customersByName(String name) {
		return customersRepo.findByNameContaining(name);
	}
	// Devuelve todos los clientes de acuerdo a la coincidencia de sus numeros de telefono
	public List<Customers> customersByPhone(String phone) {
		return customersRepo.findByPhoneContaining(phone);
	}
}
