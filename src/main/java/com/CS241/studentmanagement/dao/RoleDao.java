/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.dao;

import com.CS241.studentmanagement.entity.Role;

/**
 * @author akash
 */

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);
}
