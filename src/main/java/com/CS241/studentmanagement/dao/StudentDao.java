/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.dao;

import java.util.List;
import com.CS241.studentmanagement.entity.Student;

/**
 * @author akash
 */

public interface StudentDao {
	
	public Student findByStudentName(String theStudentName);
	
	public void save(Student student);
	
	public Student findByStudentId(int id);
	
	public List<Student> findAllStudents();
	
	public void deleteById(int id);
}
