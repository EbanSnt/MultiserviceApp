package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Communications;
import com.example.demo.repositories.CommunicationsRepository;

@Service
public class CommunicationsService {

	@Autowired
	private CommunicationsRepository communicationsRepo;
	
	// Devuelve todas las comunicaciones
	public List<Communications> allCommunications(){
		return communicationsRepo.findAll();
	}
	// Devuelve una comunicacion
	public Communications communication(Long id) {
		return communicationsRepo.findById(id).orElse(null);
	}
	// Guarda una comunicacion
	public Communications saveCommunication(Communications communication) {
		return communicationsRepo.save(communication);
	}
	// Elimina una comunicacion
	public void deleteCommunication(Long communicationId) {
		communicationsRepo.deleteById(communicationId);
	}
	//Devuelve todas las comunicaciones en orden ascendente
	public List<Communications>communicationsOrderByCommunicationDateAsc(){
		return communicationsRepo.findAllByOrderByCommunicationDateAsc();
	}
	//Devuelve todas las comunicaciones en orden descendente
	public List<Communications>communicationsOrderByCommunicationDateDesc(){
		return communicationsRepo.findAllByOrderByCommunicationDateDesc();
	}
	//Devuelve todas las comunicaciones por su tipo
	public List<Communications>communicationsByType(String communicationType){
		return communicationsRepo.findByCommunicationType(communicationType);
	}
	
	//Devuelve todas las comunicaciones por su fecha
	public List<Communications>communicationsByDate(Date communicationDate){
		return communicationsRepo.findByCommunicationDate(communicationDate);
	}
	
	//Devuelve una comunicacion por su numero de recibo
	public Communications communicationsByNumberReceipt(String numberReceipt){
		return communicationsRepo.findByProduct_NumberReceipt(numberReceipt);
	}
	
}
