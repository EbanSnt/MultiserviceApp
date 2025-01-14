package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Diagnosis;
import com.example.demo.repositories.DiagnosisRepository;

@Service
public class DiagnosisService {

	@Autowired
	DiagnosisRepository diagnosisRepo;
	
	// Devuelve todos los diagnosticos
	public List<Diagnosis> allDiagnosis(){
		return diagnosisRepo.findAll();
	}
	// Devuelve un diagnostico
	public Diagnosis diagnosis(Long id) {
		return diagnosisRepo.findById(id).orElse(null);
	}
	// Guarda un diagnostico
	public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
		return diagnosisRepo.save(diagnosis);
	}
	// Elimina un diagnostico
	public void deleteDiagnosis(Long diagnosisId) {
		diagnosisRepo.deleteById(diagnosisId);
	}
	// Devuelve todos los diagnosticos de acuerdo a su precio de reparacion, en orden ascendente
	public List<Diagnosis> allDiagnosisByPriceAsc(){
		return diagnosisRepo.findAllByOrderByRepairPriceAsc();
	}
	// Devuelve todos los diagnosticos de acuerdo a su precio de reparacion, en orden descendente
	public List<Diagnosis> allDiagnosisByPriceDesc(){
		return diagnosisRepo.findAllByOrderByRepairPriceDesc();
	}
	// Devuelve todos los diagnosticos de acuerdo a su fecha, en orden ascendente
	public List<Diagnosis> allDiagnosisByDateAsc(){
		return diagnosisRepo.findAllByOrderByDiagnosisDateAsc();
	}
	// Devuelve todos los diagnosticos de acuerdo a su fecha, en orden descendente
	public List<Diagnosis> allDiagnosisByDateDesc(){
		return diagnosisRepo.findAllByOrderByDiagnosisDateDesc();
	}
	// Devuelve todos los diagnosticos de acuerdo a una fecha especifica
	public List<Diagnosis> allDiagnosisByDateFilter(Date diagnosisDate){
		return diagnosisRepo.findByCommunicationDate(diagnosisDate);
	}
	// Devuelve el diagnostico de acuerdo a un numero de recibo
	public Diagnosis diagnosisByReceipt(String numberReceipt){
		return diagnosisRepo.findByProduct_NumberReceipt(numberReceipt);
	}
}
