package com.cust.dao;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cust.entity.CustomerEntity;

@Component
public class HomeDaoImpl implements HomeDAO{

	private SessionFactory sessionfact;
	
	@Autowired
	public HomeDaoImpl(SessionFactory sessionfact) {
		this.sessionfact = sessionfact;
	}

	public HomeDaoImpl() {
		System.out.println("HomeDaoImpl object created..");
	}

	@Override
	public List<CustomerEntity> getAllCustomers() {
		System.out.println("Invoked getAllMembers() in DAO");
		Session session = null;
		String hql = "from CustomerEntity";
		try {
			session = sessionfact.openSession();
			Query query = session.createQuery(hql);
			List resultList = query.getResultList();
			resultList.forEach(obj -> System.out.println(obj));
			return resultList;
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed");
			}
		}
	}

	@Override
	public CustomerEntity getEntityById(int customerId) {
		Session session = null;
		CustomerEntity entity = null;
		try {
			session = sessionfact.openSession();
			entity = session.get(CustomerEntity.class, customerId);
			if (entity != null) {
				return entity;
			} else {
				System.out.println("entity is null");
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entity;
	}
	
	@Override
	public boolean updateMemberEntity(CustomerEntity entity) {
		boolean flag = false;
		Session session = null;
		try {
			session = sessionfact.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			flag = true;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}
	
	@Override
	public boolean deleteMemberByID(int id) {
		boolean flag = false;
		Session session = null;
		try {
			session = sessionfact.openSession();
			Transaction transaction = session.beginTransaction();
			CustomerEntity entity = session.get(CustomerEntity.class, id);
			session.delete(entity);
			transaction.commit();
			flag = true;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}
	public List<CustomerEntity> searchCustomers(String searchTerm) {
	    try {
	        Session session = sessionfact.openSession();
	        String hql = "FROM CustomerEntity " +
	                     "WHERE firstName LIKE :searchTerm " +
	                     "OR lastName LIKE :searchTerm " +
	                     "OR address LIKE :searchTerm " +
	                     "OR city LIKE :searchTerm " +
	                     "OR state LIKE :searchTerm " +
	                     "OR email LIKE :searchTerm " +
	                     "OR mobileNumber LIKE :searchTerm";
	        Query<CustomerEntity> query = session.createQuery(hql, CustomerEntity.class);
	        query.setParameter("searchTerm", "%" + searchTerm + "%");
	        return query.getResultList();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
}
