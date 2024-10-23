/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CS241.studentmanagement.dao.RoleDao;
import com.CS241.studentmanagement.dao.AdminDao;
import com.CS241.studentmanagement.entity.Role;
import com.CS241.studentmanagement.entity.Admin;

/**
 * @author akash
 */

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao AdminDao;
	
	@Autowired 
	private RoleDao roleDao;
	

	@Override
	@Transactional
	public Admin findByAdminName(String AdminName) {
		return AdminDao.findByAdminName(AdminName);
	}
	
        @Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = AdminDao.findByAdminName(username);
		if (admin == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Collection<Role> role = new ArrayList<>();
		role.add(admin.getRole());
		return new org.springframework.security.core.userdetails.User(admin.getUserName(), admin.getPassword(),
				mapRolesToAuthorities(role));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


}
