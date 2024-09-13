package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Diagnosis;
import com.example.demo.models.ProductReceipt;
import com.example.demo.models.Users;
import com.example.demo.services.DiagnosisService;
import com.example.demo.services.ProductReceiptService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class DiagnosisController {

	@Autowired
	private DiagnosisService diagnosisServ;
	
	@Autowired
	private ProductReceiptService productServ;
	
	@GetMapping("/diagnoses")
	public String diagnoses(Model model) {
		List<Diagnosis> diagnoses = diagnosisServ.allDiagnosis();
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	
	@PostMapping("/createDiagnosis")
	public String newDiagnosis(@Valid @ModelAttribute("newDiagnosis") Diagnosis newDiagnosis,
			 BindingResult result,HttpSession session,Model model) {
		//Validación de que el usuario inició sesión
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/login";
        }
        if(result.hasErrors()) {
        	List <Diagnosis> diagnoses = diagnosisServ.allDiagnosis();
            model.addAttribute("diagnoses", diagnoses);
            return "diagnoses.jsp";
        }else {
        	diagnosisServ.saveDiagnosis(newDiagnosis);
        	return "redirect:/";
        }
	}
	
	@GetMapping("/createDiagnosis")
	public String newDiagnosis(@Valid @ModelAttribute("newDiagnosis") Diagnosis newDiagnosis,HttpSession session,Model model) {
		//Validación de que el usuario inició sesión
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/login";
        }
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
        return "createDiagnosis.jsp";
	}
	
	@GetMapping("/diagnosis/{id}")
	public String diagnosis(@PathVariable("id") Long id,@ModelAttribute("editDiagnoses") Diagnosis editDiagnoses, Model model) {
		Diagnosis diagnosis = diagnosisServ.diagnosis(id);
		model.addAttribute("diagnosis",diagnosis);
		return "diagnosis.jsp";
	}
	
	@DeleteMapping("/deleteDiagnosis/{id}")
	public String deleteDiagnosis(@PathVariable("id") Long id,HttpSession session) {
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/";
        }
  
    	diagnosisServ.deleteDiagnosis(id);
    	return "redirect:/";
        
	}
	
	@GetMapping("/diagnosesByPriceAsc")
	public String diagnosesByPriceAsc(Model model) {
		List<Diagnosis> diagnoses = diagnosisServ.allDiagnosisByPriceAsc();
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	@GetMapping("/diagnosesByPriceDesc")
	public String diagnosesByPriceDesc(Model model) {
		List<Diagnosis> diagnoses = diagnosisServ.allDiagnosisByPriceDesc();
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	@GetMapping("/diagnosesByDateAsc")
	public String diagnosesByDateAsc(Model model) {
		List<Diagnosis> diagnoses = diagnosisServ.allDiagnosisByDateAsc();
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	@GetMapping("/diagnosesByDateDesc")
	public String diagnosesByDateDesc(Model model) {
		List<Diagnosis> diagnoses = diagnosisServ.allDiagnosisByDateDesc();
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	@GetMapping("/diagnosesByDate")
	public String diagnosesByDate(Model model,@RequestParam("filter") Date filter) {
		List<Diagnosis> diagnoses = diagnosisServ.allDiagnosisByDateFilter(filter);
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	@GetMapping("/diagnosesByReceipt")
	public String diagnosesByReceipt(Model model,@RequestParam("receipt") String receipt) {
		Diagnosis diagnoses = diagnosisServ.diagnosisByReceipt(receipt);
		model.addAttribute("diagnoses",diagnoses);
		return "diagnoses.jsp";
	}
	
	
}