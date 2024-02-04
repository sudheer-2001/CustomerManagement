package com.cust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cust.dao.AddCustomerDAO;
import com.cust.entity.CustomerEntity;

@Component
public class AddCustomerService {

	private AddCustomerDAO customerDao;
	
	@Autowired
	public AddCustomerService(AddCustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public AddCustomerService() {
		System.out.println("Inside add customer service..");
	}
	
	public boolean checkEmailExists(String email) {
		boolean result = customerDao.isEmailExists(email);
		return result;
	}
	
	public boolean saveData(String firstName, String lastName, String street,
			String address, String city, String state, String email, long mobileNumber) {
		CustomerEntity entity = new CustomerEntity(firstName,lastName,street,address,city,state,email,mobileNumber);
		boolean result = customerDao.saveDatatoDB(entity);
		return result;
	}
}