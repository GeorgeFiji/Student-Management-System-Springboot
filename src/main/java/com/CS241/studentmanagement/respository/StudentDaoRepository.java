/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.StudentDao;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.CS241.studentmanagement.entity.Student;

/**
 * @author akash
 */

@Repository
public class StudentDaoRepository implements StudentDao {
	
	@Autowired
	private EntityManager entityManager;
		
	@Override
	public Student findByStudentName(String theStudentName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Student> query = session.createQuery("from Student where userName=:user", Student.class);
		query.setParameter("user", theStudentName);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}	
	}
	
	@Override
	public Student findByStudentId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Student> query = session.createQuery("from Student where id=:theId", Student.class);
		query.setParameter("theId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}	
	}

	@Override
	public void save(Student student) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(student);
	}

	@Override
	public List<Student> findAllStudents() {
		Session session = entityManager.unwrap(Session.class);
		List<Student> students = session.createQuery("from Student", Student.class).getResultList();
		return students;
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Student where id=:studentId");
		query.setParameter("studentId", id);
		query.executeUpdate();
	}

}
