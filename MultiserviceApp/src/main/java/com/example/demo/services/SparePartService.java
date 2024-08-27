package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.SparePart;
import com.example.demo.repositories.SparePartRepository;

@Service
public class SparePartService {

	@Autowired
	SparePartRepository sparePartRepo;
	
	//Devuelve toda la lista de repuestos
	public List<SparePart>allSpareParts(){
		return sparePartRepo.findAll();
	}
	// Devuelve un repuesto
	public SparePart sparePart(Long id) {
		return sparePartRepo.findById(id).orElse(null);
	}
	// Crea un repuesto
	public SparePart saveSparePart(SparePart sparePart) {
		return sparePartRepo.save(sparePart);
	}
	// Elimina un repuesto
	public void deleteSparePart(Long id) {
		sparePartRepo.deleteById(id);
	}
	// Devuelve una lista de acuerdo al tipo de repuesto
	public List<SparePart>allBySparePartType(String sparePartType){
		return sparePartRepo.findAllBySparePartType(sparePartType);
	}
	// Devuelve una lista de acuerdo a la coincidencias en el nombre del repuesto
	public List<SparePart>allBySparePartName(String name){
		return sparePartRepo.findByNameContaining(name);
	}
}