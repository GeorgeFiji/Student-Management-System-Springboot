/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CS241.studentmanagement.dao.StudentCourseDetailsDao;
import com.CS241.studentmanagement.entity.StudentCourseDetails;

/**
 * @author akash
 */

@Service
public class StudentCourseDetailsServiceImpl implements StudentCourseDetailsService {
	
	@Autowired
	private StudentCourseDetailsDao studentCourseDetailsDao;
	
	@Override
	@Transactional
	public List<StudentCourseDetails> findByStudentId(int id) {
		return studentCourseDetailsDao.findByStudentId(id);
	}

	@Override
	@Transactional
	public StudentCourseDetails findByStudentAndCourseId(int studentId, int courseId) {
		return studentCourseDetailsDao.findByStudentAndCourseId(studentId, courseId);
	}

	@Override
	@Transactional
	public void deleteByStudentAndCourseId(int studentId, int courseId) {
		studentCourseDetailsDao.deleteByStudentAndCourseId(studentId, courseId);
	}

	@Override
	@Transactional
	public void save(StudentCourseDetails studentCourseDetails) {
		studentCourseDetailsDao.save(studentCourseDetails);
		
	}

	@Override
	@Transactional
	public void deleteByStudentId(int id) {
		studentCourseDetailsDao.deleteByStudentId(id);
	}
}
