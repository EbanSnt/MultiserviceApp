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

import com.example.demo.models.Disarmaments;
import com.example.demo.models.ProductReceipt;
import com.example.demo.models.Users;
import com.example.demo.services.DisarmamentsService;
import com.example.demo.services.ProductReceiptService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DisarmamentsController {

	@Autowired
	private DisarmamentsService disarmamentsServ;
	
	@Autowired
	private ProductReceiptService productServ;
	
	@GetMapping("/disarmaments")
	public String disarmaments(Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament) {
		List<Disarmaments> disarmaments = disarmamentsServ.allDisarmament();
		model.addAttribute("disarmaments",disarmaments);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "disarmaments.jsp";
	}
	
	@PostMapping("/disarmamentCreate")
	public String disarmamentCreate(Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament,HttpSession session,BindingResult result) {
		 Users userTemp = (Users) session.getAttribute("userInSession"); 
	        if(userTemp == null) {
	            return "redirect:/login";
	        }
	        if(result.hasErrors()) {
	        	List<Disarmaments> disarmaments = disarmamentsServ.allDisarmament();
	        	model.addAttribute("disarmaments",disarmaments);
	            List<ProductReceipt> productReceipts = productServ.allProductReceipts();
	            model.addAttribute("productReceipts",productReceipts);
	            return "disarmaments.jsp";
	        }else {
	        	disarmamentsServ.saveDisarmament(newDisarmament);
	        	return "redirect:/";
	        }
	}
	
	@GetMapping("/disarmament/{id}")
	public String disarmament(@PathVariable("id") Long id,Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament) {
		Disarmaments disarmament = disarmamentsServ.disarmament(id);
		model.addAttribute("disarmament",disarmament);
		List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "disarmament.jsp";
	}
	
	@DeleteMapping("/deleteDisarmament")
	public String deleteDisarmament(@RequestParam("id") Long id,HttpSession session) {
		 Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
	        if(userTemp == null) {
	            return "redirect:/";
	        }
	        disarmamentsServ.deleteDisarmament(id);
	        return "redirect:/";
	        
	}
	
	@GetMapping("/disarmamentsByDateAsc")
	public String disarmamentsByDateAsc(Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament) {
		List<Disarmaments> disarmaments = disarmamentsServ.allDisarmamentsByDateAsc();
		model.addAttribute("disarmaments",disarmaments);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "disarmaments.jsp";
	}
	
	@GetMapping("/disarmamentsByDateDesc")
	public String disarmamentsByDateDesc(Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament) {
		List<Disarmaments> disarmaments = disarmamentsServ.allDisarmamentsByDateDesc();
		model.addAttribute("disarmaments",disarmaments);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "disarmaments.jsp";
	}
	
	@GetMapping("/disarmamentsByDate")
	public String disarmamentsByDate(Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament,@RequestParam("filter") Date filter) {
		List<Disarmaments> disarmaments = disarmamentsServ.allDisarmamentsByDateFilter(filter);
		model.addAttribute("disarmaments",disarmaments);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "disarmaments.jsp";
	}
	
	@GetMapping("/disarmamentsByReceipt")
	public String disarmamentsByReceipt(Model model,@ModelAttribute("newDisarmament") Disarmaments newDisarmament,@RequestParam("filter") String filter) {
		Disarmaments disarmaments = disarmamentsServ.disarmamentByReceipt(filter);
		model.addAttribute("disarmaments",disarmaments);
        List<ProductReceipt> productReceipts = productServ.allProductReceipts();
        model.addAttribute("productReceipts",productReceipts);
		return "disarmaments.jsp";
	}
}
