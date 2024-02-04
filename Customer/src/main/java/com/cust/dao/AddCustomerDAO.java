package com.cust.dao;

import com.cust.entity.CustomerEntity;

public interface AddCustomerDAO {
	
	boolean saveDatatoDB(CustomerEntity entity);
	
	public boolean isEmailExists(String email);
	
}
