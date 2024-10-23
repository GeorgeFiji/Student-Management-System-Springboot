/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.CS241.studentmanagement.entity.Parent;
import com.CS241.studentmanagement.user.UserDto;

/**
 * @author akash
 */

public interface ParentService extends UserDetailsService {
	
	public Parent findByParentName(String userName);

	public void save(UserDto userDto);
	
	public void save(Parent parent);
	
	public Parent findByParentId(int id);
	
	public List<Parent> findAllParent();
	
	public void deleteById(int id);
}
