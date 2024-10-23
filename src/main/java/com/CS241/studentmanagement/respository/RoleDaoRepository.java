/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.RoleDao;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.CS241.studentmanagement.entity.Role;

/**
 * @author akash
 */

@Repository
public class RoleDaoRepository implements RoleDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Role findRoleByName(String theRoleName) {
		
		Session session = entityManager.unwrap(Session.class);
		Query<Role> query = session.createQuery("from Role where name=:role", Role.class);
		query.setParameter("role", theRoleName);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}
}
