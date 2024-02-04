package com.cust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cust.dao.HomeDAO;
import com.cust.entity.CustomerEntity;

@Component
public class HomeService {
	
	private HomeDAO homeDao;

	@Autowired
	public HomeService(HomeDAO homeDao) {
		this.homeDao = homeDao;
	}

	public HomeService() {
		System.out.println("Home service object created..");
	}
	
	public List<CustomerEntity> getAllData(){
		System.out.println("Invoked getAllData() in HomeService");
		List<CustomerEntity> allCustomers = homeDao.getAllCustomers();
		return allCustomers;
	}
	
	public CustomerEntity getEntityById(int id) {
		return homeDao.getEntityById(id);
	}
	
	public boolean updateMemberEntity(int customerId, String firstName, String lastName, String street,
			String address, String city, String state, String email, long mobileNumber) {
		CustomerEntity entity = new CustomerEntity(customerId,firstName,lastName,street,address,city,state,email,mobileNumber);
		return homeDao.updateMemberEntity(entity);
	}

	public boolean deleteMemberEntityById(int id) {
		return homeDao.deleteMemberByID(id);
	}
	
	public List<CustomerEntity> searchBar(String queryTerm){
		return homeDao.searchCustomers(queryTerm);
	}
}
