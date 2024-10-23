/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CS241.studentmanagement.dao.RoleDao;
import com.CS241.studentmanagement.dao.ParentDao;
import com.CS241.studentmanagement.entity.Role;
import com.CS241.studentmanagement.entity.Parent;
import com.CS241.studentmanagement.user.UserDto;

/**
 * @author akash
 */

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentDao parentDao;
	
	@Autowired 
	private RoleDao roleDao;

	@Override
	@Transactional
	public Parent findByParentName(String parentName) {
		return parentDao.findByParentName(parentName);
	}
	
	@Override
	@Transactional
	public Parent findByParentId(int id) {
		return parentDao.findByParentId(id);
	}

	@Override
	@Transactional
	public void save(UserDto userDto) {
		Parent parent = new Parent();
		parent.setUserName(userDto.getUserName());
		parent.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		parent.setFirstName(userDto.getFirstName());
		parent.setLastName(userDto.getLastName());
		parent.setEmail(userDto.getEmail());		
		parent.setRole(userDto.getRole());		
		parentDao.save(parent);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Parent parent = parentDao.findByParentName(username);
		if (parent == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Collection<Role> role = new ArrayList<>();
		role.add(parent.getRole());
		return new org.springframework.security.core.userdetails.User(parent.getUserName(), parent.getPassword(),
				mapRolesToAuthorities(role));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Parent> findAllParent() {
		return parentDao.findAllParent();
	}

	@Override
	@Transactional
	public void save(Parent parent) {
		parentDao.save(parent);
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		parentDao.deleteById(id);
	}
}
