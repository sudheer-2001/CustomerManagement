package com.cust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cust.service.AddCustomerService;

@Controller
public class AddCustomerController {
	
	private AddCustomerService customerService;
	
	@Autowired
	public AddCustomerController(AddCustomerService customerService) {
		this.customerService = customerService;
	}

	public AddCustomerController() {
		System.out.println("Inside add customer controller...");
	}

	@RequestMapping(value="/addCustomer")
	public String addingCustomer() {
		return "/WEB-INF/pages/AddCustomer.jsp";
	}
	
	@RequestMapping(value="/saveUserData")
	public String getCustomers(@RequestParam String firstName, @RequestParam String lastName,@RequestParam String street
			,@RequestParam String address, @RequestParam String city, @RequestParam String state,
			@RequestParam String email, @RequestParam String mobileNumber, Model model) {
		
		boolean emailExists = customerService.checkEmailExists(email);
		if(emailExists) {
			model.addAttribute("result","Email already exists try with different one..");
			return "/WEB-INF/pages/AddCustomer.jsp";
		}
		else {
			long mobileNumber1 = Long.parseLong(mobileNumber);
			boolean res = customerService.saveData(firstName, lastName, street, address, city, state, email, mobileNumber1);
			if(res) {
				model.addAttribute("updateMessage", "New customer successfully added");
			}
		}
		return "/getAllCustomers";
	}
}
