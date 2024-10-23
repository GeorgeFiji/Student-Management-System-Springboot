/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.CS241.studentmanagement.entity.Admin;

/**
 * @author akash
 */

public interface AdminService extends UserDetailsService {


    public Admin findByAdminName(String userName);
}
