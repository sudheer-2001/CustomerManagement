package com.cust.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cust.entity.CustomerEntity;

@Component
public class AddCustomerDaoImpl implements AddCustomerDAO{

	public AddCustomerDaoImpl() {
		System.out.println("Customer Dao Impl created..");
	}
	
	private SessionFactory sessionfact;

	@Autowired
	public AddCustomerDaoImpl(SessionFactory sessionfact) {
		this.sessionfact = sessionfact;
	}

	
	@Override
	public boolean saveDatatoDB(CustomerEntity entity){
		System.out.println("Invoked saveDatatoDB()..");
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = sessionfact.openSession();
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			transaction.rollback();
			System.out.println("transaction is rollbacked " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session closed...");
			}
		}
		return flag;
	}
	

	@Override
	public boolean isEmailExists(String email) {
		Session session = null;
		String hql = "SELECT COUNT(*) FROM CustomerEntity WHERE email = :email";
		try {
			session = sessionfact.openSession();
			Query<Long> query = session.createQuery(hql, Long.class);
			query.setParameter("email", email);
			Long count = query.uniqueResult();
			return count != null && count > 0;
		} finally {
			if(session!=null) {
				session.close();
			}
		}
	}



	
}
