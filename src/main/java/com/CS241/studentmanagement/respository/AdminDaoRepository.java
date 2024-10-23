/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.AdminDao;
import com.CS241.studentmanagement.dao.AdminDao;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.CS241.studentmanagement.entity.Admin;

/**
 * @author akash
 */

@Repository
public class AdminDaoRepository implements AdminDao {
	
	@Autowired
	private EntityManager entityManager;
		
	@Override
	public Admin findByAdminName(String theAdminName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Admin> query = session.createQuery("from Admin where userName=:user", Admin.class);
		query.setParameter("user", theAdminName);
                
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}	
	}
}
