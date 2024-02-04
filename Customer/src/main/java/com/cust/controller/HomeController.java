package com.cust.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cust.additional.CompareCity;
import com.cust.additional.CompareEmail;
import com.cust.additional.CompareName;
import com.cust.entity.CustomerEntity;
import com.cust.service.HomeService;

@Controller
public class HomeController {

	private HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}

	public HomeController() {
		System.out.println("Inside the HomePage controller...");
	}

	@RequestMapping(value="/getHomePage")
	public String getHomePage() {
		return "redirect:/getAllCustomers";
	}
	
	@RequestMapping(value = "/getAllCustomers")
	public String getAllMembers(Model model, HttpSession session) {
		List<CustomerEntity> customers = homeService.getAllData();
		if (customers != null) {
			model.addAttribute("viewCustomers", customers);
		} else {
			model.addAttribute("updateMessage", "No Member Found..!");
		}
		return "/WEB-INF/pages/HomePage.jsp";
	}
	
	@RequestMapping(value = "/editCustomer/{customerId}", method = RequestMethod.GET)
	public String editMember(@PathVariable int customerId, Model model) {
		System.out.println("Edit Entity");
		CustomerEntity entity = homeService.getEntityById(customerId);
		model.addAttribute("customerId", entity.getCustomerId());
		model.addAttribute("firstName", entity.getFirstName());
		model.addAttribute("lastName", entity.getLastName());
		model.addAttribute("street", entity.getStreet());
		model.addAttribute("address", entity.getAddress());
		model.addAttribute("city", entity.getCity());
		model.addAttribute("state", entity.getState());
		model.addAttribute("email", entity.getEmail());
		model.addAttribute("mobileNumber", entity.getMobileNumber());
		return "/WEB-INF/pages/EditCustomer.jsp";
	}
	
	@RequestMapping(value = "/updateDetails/{customerId}", method = RequestMethod.POST)
	public String updateMemberDetails(@PathVariable int customerId,@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String street
			,@RequestParam String address, @RequestParam String city, @RequestParam String state,
			@RequestParam String email, @RequestParam String mobileNumber, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("Updating ");
		long mobileNumber1 = Long.parseLong(mobileNumber);
		boolean customerDetails = homeService.updateMemberEntity(customerId,firstName,lastName,street,address,city,state,email,mobileNumber1);
		System.out.println(firstName + " " + lastName + " " + street + " " + address + " " + city + " " + state
				+ " " + email + " " + mobileNumber1);
		System.out.println(customerDetails);
		if (customerDetails) {
			redirectAttributes.addFlashAttribute("updateMessage","Customer information updated successfully..");
			System.out.println("Edit Success");
			return "redirect:/getAllCustomers";
		} else {
			redirectAttributes.addFlashAttribute("result", "Something Went Wrong While Updating");
			System.out.println("Edit not success");
		}
		return "/WEB-INF/pages/EditCustomer.jsp";
	}

	@RequestMapping(value = "/deleteCustomer/{customerId}")
	public String deleteCustomer(HttpServletRequest request, @PathVariable int customerId,RedirectAttributes redirectAttributes) {
		System.out.println("Invoked deleteCustomer()");
		System.out.println("delete customerId : " + customerId);
		boolean deleteById = homeService.deleteMemberEntityById(customerId);
		System.out.println("Deleted: " + deleteById);
		if (deleteById) {
			redirectAttributes.addFlashAttribute("updateMessage", "Deleted Successful.");
			return "redirect:/getAllCustomers";
		} else {
			redirectAttributes.addFlashAttribute("result", "Something Went Wrong while deleting..!");
			System.out.println("Something Went Wrong while deleting...!");
		}
		return "/WEB-INF/pages/EditCustomer.jsp";
	}
	
	@RequestMapping(value="/searchBy")
	public String filterBy(@RequestParam String searchBy, Model model) {
		
		List<CustomerEntity> customers = homeService.getAllData();
		if (customers != null) {
			switch(searchBy) {
			case "First Name": 
				Collections.sort(customers, new CompareName());
				System.out.println(searchBy);
				break;
			case "City":
				Collections.sort(customers, new CompareCity());
				System.out.println(searchBy);
				break;
			case "Email":
				Collections.sort(customers, new CompareEmail());
				System.out.println(searchBy);
				break;
			case "Phone":
				Collections.sort(customers);
				System.out.println(searchBy);
				break;
			case "Normal":
				System.out.println(searchBy);
				break;
			}
			model.addAttribute("viewCustomers", customers);
		}
		else {
			model.addAttribute("updateMessage", "No Member Found..!");
		}
		return "/WEB-INF/pages/HomePage.jsp";
	}
	
	@RequestMapping(value = "searching")
	public String searchBar(@RequestParam String searching, Model model) {
	    List<CustomerEntity> customers = homeService.searchBar(searching);
	    if (customers != null && !customers.isEmpty()) {
	        model.addAttribute("viewCustomers", customers);
	    } else {
	        model.addAttribute("updateMessage", "No customers found for the search term.");
	    }
	    return "/WEB-INF/pages/HomePage.jsp";
	}

}
