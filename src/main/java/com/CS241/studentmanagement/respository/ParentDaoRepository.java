/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.ParentDao;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.CS241.studentmanagement.entity.Parent;

/**
 * @author akash
 */

@Repository
public class ParentDaoRepository implements ParentDao {
	
	@Autowired
	private EntityManager entityManager;
		
	@Override
	public Parent findByParentName(String theParentName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Parent> query = session.createQuery("from Parent where userName=:user", Parent.class);
		query.setParameter("user", theParentName);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
		
	}
	
	@Override
	public Parent findByParentId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Parent> query = session.createQuery("from Parent where id=:theId", Parent.class);
		query.setParameter("theId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
		
	}

	@Override
	public void save(Parent parent) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(parent);

	}

	@Override
	public List<Parent> findAllParent() {
		Session session = entityManager.unwrap(Session.class);
		List<Parent> parent = session.createQuery("from Parent", Parent.class).getResultList();
		return parent;
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Parent where id=:parentId");
		query.setParameter("parentId", id);
		query.executeUpdate();
	}

}
