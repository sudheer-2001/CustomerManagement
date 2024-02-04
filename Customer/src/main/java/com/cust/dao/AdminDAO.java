package com.cust.dao;

import javax.persistence.EntityNotFoundException;

import com.cust.entity.AdminEntity;

public interface AdminDAO {

	AdminEntity getAdminEntityByEmail(String email) throws EntityNotFoundException;
	
}
