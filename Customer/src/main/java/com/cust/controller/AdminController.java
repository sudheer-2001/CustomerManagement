package com.cust.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cust.entity.AdminEntity;
import com.cust.exception.InvalidException;
import com.cust.service.AdminService;

@Controller
public class AdminController {

	public AdminController() {
		System.out.println("Inside Admin Controller...");
	}
	
	private AdminService loginService;

	@Autowired
	public AdminController(AdminService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value = "/checkCredentials")
	public String UserLogin(@RequestParam String username, @RequestParam String password, Model model,
			HttpServletRequest request) {
		System.out.println("Invoked UserLogin in controller...");
		try {
			boolean isValidUser = loginService.loginCredentials(username, password);
			return "/getHomePage";
		} catch (InvalidException | EntityNotFoundException e) {
			model.addAttribute("result", "Login failed: " + e.getMessage());
		}
		return "index.jsp";
	}
	
}
