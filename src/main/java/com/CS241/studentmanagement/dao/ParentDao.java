/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.dao;

import java.util.List;

import com.CS241.studentmanagement.entity.Parent;

/**
 * @author akash
 */

public interface ParentDao {
	
	public Parent findByParentName(String theParentName);
	
	public void save(Parent parent);

	public Parent findByParentId(int id);
	
	public List<Parent> findAllParent();
	
	public void deleteById(int id);
}
