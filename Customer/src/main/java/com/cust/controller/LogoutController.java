package com.cust.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	public LogoutController() {
		System.out.println("Logout Controller Object Created...");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session1 = request.getSession(false);
		if (session1 != null) {
			session1.invalidate();
		}
		return "redirect:/login	";
	}
	
	@GetMapping("/login")
	public String doLogin() {
	    return "/WEB-INF/pages/LogoutPage.jsp";
	}

}