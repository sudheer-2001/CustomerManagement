package com.cust.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cust.dao.AdminDAO;
import com.cust.entity.AdminEntity;
import com.cust.exception.InvalidException;

@Component
public class AdminService {

	public AdminService() {
		System.out.println("Inside Admin Service..");
	}
	
	private AdminDAO loginDao;

	@Autowired
	public AdminService(AdminDAO loginDao) {
		this.loginDao = loginDao;
	}
	
	public boolean loginCredentials(String mail, String password) throws InvalidException, EntityNotFoundException {
		System.out.println("Invoked loginCredentials() in service");
		if (mail == null || mail.isEmpty() || mail.isBlank()) {
			throw new InvalidException("Please Enter valid mail..");
		}

		if (password == null || password.isEmpty() || password.isBlank()) {
			throw new InvalidException("Please Enter valid Password..");
		}

		AdminEntity entity = loginDao.getAdminEntityByEmail(mail);
		if (entity != null) {
			if (entity.getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidException("Password mismatches, Try again!..");
			}
		} else {
			throw new InvalidException("mail doesn't exist, Please Register!...");
		}
	}
	
	
}
