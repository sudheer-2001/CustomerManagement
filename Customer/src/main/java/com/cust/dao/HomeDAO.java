package com.cust.dao;

import java.util.List;

import com.cust.entity.CustomerEntity;

public interface HomeDAO {

	List<CustomerEntity> getAllCustomers();
	
	CustomerEntity getEntityById(int customerId);
	
	boolean updateMemberEntity(CustomerEntity entity);
	
	boolean deleteMemberByID(int id);
	
	List<CustomerEntity> searchCustomers(String searchTerm);
}
