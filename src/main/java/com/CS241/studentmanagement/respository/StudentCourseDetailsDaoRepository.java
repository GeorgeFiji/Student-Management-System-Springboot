/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.StudentCourseDetailsDao;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.CS241.studentmanagement.entity.StudentCourseDetails;

/**
 * @author akash
 */

@Repository
public class StudentCourseDetailsDaoRepository implements StudentCourseDetailsDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<StudentCourseDetails> findByStudentId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<StudentCourseDetails> query = session.createQuery("from student_course_details where studentId=:id", StudentCourseDetails.class);
		query.setParameter("id", id);
		
		try {
			return query.getResultList();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public StudentCourseDetails findByStudentAndCourseId(int studentId, int courseId) {
		Session session = entityManager.unwrap(Session.class);
		Query<StudentCourseDetails> query = session.createQuery("from student_course_details where studentId=:studentId and courseId=:courseId", StudentCourseDetails.class);
		query.setParameter("studentId", studentId);
		query.setParameter("courseId", courseId);
		
		try {
			return query.getSingleResult();
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public void deleteByStudentAndCourseId(int studentId, int courseId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete student_course_details where studentId=:studentId and courseId=:courseId");
		query.setParameter("studentId", studentId);
		query.setParameter("courseId", courseId);
		query.executeUpdate();
	}

	@Override
	public void save(StudentCourseDetails studentCourseDetails) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(studentCourseDetails);
	}

	@Override
	public void deleteByStudentId(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete student_course_details where studentId=:studentId");
		query.setParameter("studentId", id);
		query.executeUpdate();
	}
}
