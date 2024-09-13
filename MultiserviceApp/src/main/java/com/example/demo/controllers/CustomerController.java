package com.example.demo.controllers;

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

import com.example.demo.models.Customers;
import com.example.demo.models.Users;
import com.example.demo.services.CustomersService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CustomerController {

	@Autowired
	private CustomersService customerServ;
	
	// Vista que lleva a la pagina principal de "Customers"
	@GetMapping("/customers")
	public String customers(@ModelAttribute("newCustomer") Customers newCustomer,
			Model model, HttpSession session) {
		List<Customers> customers = customerServ.allCostumers();
		model.addAttribute("customers",customers);
		return "customers.jsp";
	}
	
	// Permite crear un nuevo cliente o editarlo
	@PostMapping("/createCustomer")
	public String newCustomer(@Valid @ModelAttribute("newCustomer") Customers newCustomer,
			 BindingResult result,HttpSession session,Model model) {
		//Validación de que el usuario inició sesión
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/login";
        }
        if(result.hasErrors()) {
        	List <Customers> customers = customerServ.allCostumers();
            model.addAttribute("customers", customers);
            return "customers.jsp";
        }else {
        	customerServ.saveCustomer(newCustomer);
        	return "redirect:/";
        }
	}
	
	// Obtiene un cliente, de acuerdo a su id
	@GetMapping("/customer/{id}")
	public String customer(@PathVariable("id") Long id,@ModelAttribute("editCustomer") Customers editCustomer, Model model) {
		Customers customer = customerServ.customers(id);
		model.addAttribute("customer",customer);
		return "customer.jsp";
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") Long id,HttpSession session) {
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/";
        }
  
    	customerServ.deleteCustomer(id);
    	return "redirect:/";
        
	}
	
	// Vista en el que devuelven los clientes filtrados (el usuario usa el input de buscar)
	@GetMapping("/customersFilter")
	public String customersFilter(@RequestParam("filter") String filter,Model model ) {
        List<Customers> customers = customerServ.customersByName(filter);
        model.addAttribute("customers",customers);
		return "customers.jsp";
	}
}













