/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CS241.studentmanagement.dao.CourseDao;
import com.CS241.studentmanagement.entity.Course;

/**
 * @author akash
 */

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	
	@Override
	@Transactional
	public void save(Course course) {
		courseDao.saveCourse(course);
	}

	@Override
	@Transactional
	public List<Course> findAllCourses() {
		return courseDao.findAllCourses();
	}

	@Override
	@Transactional
	public Course findCourseById(int id) {
		return courseDao.findCourseById(id);
	}

	@Override
	@Transactional
	public void deleteCourseById(int id) {
		courseDao.deleteCourseById(id);		
	}

}
