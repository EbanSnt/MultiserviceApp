package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.History;
import com.example.demo.repositories.HistoryRepository;

@Service
public class HistoryService {

	@Autowired
	HistoryRepository historyRepo;
	
	// Devuelve todos los elementos del historial
	public List<History> allHistory(){
		return historyRepo.findAll();
	}
	// Devuelve un elemento del historial
	public History historyElement(Long id) {
		return historyRepo.findById(id).orElse(null);
	}
	// Crea un elemento del historial
	public History saveHistory(History history) {
		return historyRepo.save(history);
	}
	// Elimina un elemento del historial
	public void deleteHistory(Long id) {
		historyRepo.deleteById(id);
	}
	// Devuelve la lista de todos los elementos de una tabla determinada
	public List<History> allHistoryByTableName(String tableName){
		return historyRepo.findAllByTableName(tableName);
	}
	// Devuelve la lista de todos los elementos del historial de una fecha determinada
	public List<History> allHistoryByDate(Date historyDate){
		return historyRepo.findByHistoryDate(historyDate);
	}
	// Devuelve la lista de todos los elementos del historial por una determinada accion
	public List<History> allHistoryByAction(String action){
		return historyRepo.findAllByAction(action);
	}
}