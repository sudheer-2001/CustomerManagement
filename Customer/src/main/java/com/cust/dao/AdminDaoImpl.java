package com.cust.dao;

import javax.persistence.EntityNotFoundException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cust.entity.AdminEntity;


@Component
public class AdminDaoImpl implements AdminDAO {
	
	private SessionFactory sessionfact;

	@Autowired
	public AdminDaoImpl(SessionFactory sessionfact) {
		this.sessionfact = sessionfact;
	}

	public AdminDaoImpl() {
		System.out.println("Created Object ->Login DAO");
	}

	@Override
	public AdminEntity getAdminEntityByEmail(String userName) throws EntityNotFoundException {
		Session session = null;
		AdminEntity entity = null;
		String hql = "from AdminEntity where userName = '" + userName + "'";
		try {
			session = sessionfact.openSession();
			Query query = session.createQuery(hql);
			entity = (AdminEntity) query.uniqueResult();
			System.out.println(entity);
			if (entity != null) {
				return entity;
			} else {
				throw new EntityNotFoundException("AdminEntity with UserName " + userName + " not found");
			}
		} catch (HibernateException e) {
			System.out.println("Exception in getAdminEntityByEmail: " + e.getMessage());
			throw new EntityNotFoundException("Exception in getAdminEntityByEmail: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
