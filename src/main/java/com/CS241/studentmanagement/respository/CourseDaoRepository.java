/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.CourseDao;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.CS241.studentmanagement.entity.Course;

/**
 * @author akash
 */

@Repository
public class CourseDaoRepository implements CourseDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void saveCourse(Course course) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(course);
	}

	@Override
	public List<Course> findAllCourses() {
		Session session = entityManager.unwrap(Session.class);
		List<Course> courses = session.createQuery("from Course", Course.class).getResultList();
		return courses;
	}

	@Override
	public Course findCourseById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Course> query = session.createQuery("from Course where id=:courseId", Course.class);
		query.setParameter("courseId", id);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public void deleteCourseById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete Course where id=:courseId");
		query.setParameter("courseId", id);
		query.executeUpdate();
	}

}
