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

import com.example.demo.models.Communications;
import com.example.demo.models.ProductReceipt;
import com.example.demo.models.Users;
import com.example.demo.services.CommunicationsService;
import com.example.demo.services.ProductReceiptService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CommunicationsController {

	@Autowired
	private CommunicationsService communicationServ;
	
	@Autowired
	private ProductReceiptService productServ;
	

	@GetMapping("/communications")
	public String communications(@ModelAttribute("newCommunicaction") Communications newCommunicaction,
			Model model, HttpSession session) {
		List<Communications> communications = communicationServ.allCommunications();
		model.addAttribute("communications",communications);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "communications.jsp";
	}
	
	
	@PostMapping("/createCommunication")
	public String newCommunication(@Valid @ModelAttribute("newCommunicaction") Communications newCommunicaction,
			 BindingResult result,HttpSession session,Model model) {
		//Validación de que el usuario inició sesión
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/login";
        }
        if(result.hasErrors()) {
        	List <Communications> communications = communicationServ.allCommunications();
            model.addAttribute("communications", communications);
            return "communications.jsp";
        }else {
        	communicationServ.saveCommunication(newCommunicaction);
        	return "redirect:/";
        }
	}
	
	
	@GetMapping("/communication/{id}")
	public String communication(@PathVariable("id") Long id,@ModelAttribute("editCommunication") Communications editCustomer, Model model) {
		Communications communication = communicationServ.communication(id);
		model.addAttribute("communication",communication);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "customer.jsp";
	}
	
	@DeleteMapping("/deleteCommunication/{id}")
	public String deleteCustomer(@PathVariable("id") Long id,HttpSession session) {
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/";
        }
  
    	communicationServ.deleteCommunication(id);
    	return "redirect:/";
        
	}
	
	@GetMapping("/communicationsDateAsc")
	public String communicationsDateAsc(@ModelAttribute("newCommunicaction") Communications newCommunicaction,
			Model model, HttpSession session) {
		List<Communications> communications = communicationServ.communicationsOrderByCommunicationDateAsc();
		model.addAttribute("communications",communications);
		return "communications.jsp";
	}
	
	@GetMapping("/communicationsDateDesc")
	public String communicationsDateDesc(@ModelAttribute("newCommunicaction") Communications newCommunicaction,
			Model model, HttpSession session) {
		List<Communications> communications = communicationServ.communicationsOrderByCommunicationDateDesc();
		model.addAttribute("communications",communications);
		return "communications.jsp";
	}
	
	@GetMapping("/communicationsType")
	public String communicationsType(@RequestParam("filter") String filter,@ModelAttribute("newCommunicaction") Communications newCommunicaction,
			Model model, HttpSession session) {
		List<Communications> communications = communicationServ.communicationsByType(filter);
		model.addAttribute("communications",communications);
		return "communications.jsp";
	}
	
	@GetMapping("/communicationsDate")
	public String communicationsDate(@RequestParam("filter") Date filter,@ModelAttribute("newCommunicaction") Communications newCommunicaction,
			Model model, HttpSession session) {
		List<Communications> communications = communicationServ.communicationsByDate(filter);
		model.addAttribute("communications",communications);
		return "communications.jsp";
	}
	
	@GetMapping("/communicationsReceipt")
	public String communicationsReceipt(@RequestParam("filter") String filter,@ModelAttribute("newCommunicaction") Communications newCommunicaction,
			Model model, HttpSession session) {
		Communications communications = communicationServ.communicationsByNumberReceipt(filter);
		model.addAttribute("communications",communications);
		return "communications.jsp";
	}
	
}