/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.dao;

import java.util.List;
import com.CS241.studentmanagement.entity.Teacher;

/**
 * @author akash
 */

public interface TeacherDao {
	
	public Teacher findByTeacherName(String theTeacherName);
	
	public Teacher findByTeacherId(int id);
	
	public void save(Teacher teacher);
	
	public List<Teacher> findAllTeachers();
	
	public void deleteTeacherById(int id);
	
}
