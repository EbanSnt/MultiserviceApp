package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Disarmaments;
import com.example.demo.repositories.DisarmamentsRepository;

@Service
public class DisarmamentsService {

	@Autowired
	DisarmamentsRepository disarmamentsRepo;
	
	// Devuelve todos los desarmes
		public List<Disarmaments> allDisarmament(){
			return disarmamentsRepo.findAll();
		}
		// Devuelve un desarme
		public Disarmaments disarmament(Long id) {
			return disarmamentsRepo.findById(id).orElse(null);
		}
		// Guarda un desarme
		public Disarmaments saveDisarmament(Disarmaments disarmament) {
			return disarmamentsRepo.save(disarmament);
		}
		// Elimina un desarme
		public void deleteDisarmament(Long disarmamentId) {
			disarmamentsRepo.deleteById(disarmamentId);
		}
		// Devuelve todos los desarmes de acuerdo a su fecha, en orden ascendente
		public List<Disarmaments> allDisarmamentsByDateAsc(){
			return disarmamentsRepo.findAllByOrderByDisarmamentDateAsc();
		}
		// Devuelve todos los desarmes de acuerdo a su fecha, en orden descendente
		public List<Disarmaments> allDisarmamentsByDateDesc(){
			return disarmamentsRepo.findAllByOrderByDisarmamentDateDesc();
		}
		
		// Devuelve todos los desarmes de acuerdo a una fecha especifica
		public List<Disarmaments> allDisarmamentsByDateFilter(Date disarmamentsDate){
			return disarmamentsRepo.findByDisarmamentDate(disarmamentsDate);
		}
		// Devuelve el desarme de acuerdo a un numero de recibo
		public Disarmaments disarmamentByReceipt(String numberReceipt){
			return disarmamentsRepo.findByProduct_NumberReceipt(numberReceipt);
		}
}
