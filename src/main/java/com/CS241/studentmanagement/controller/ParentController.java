/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.CS241.studentmanagement.entity.Parent;
import com.CS241.studentmanagement.service.ParentService;

/**
 * @author akash
 */

@Controller
@RequestMapping("/parent")
public class ParentController {
	
	@Autowired
	private ParentService parentService;

	@GetMapping("/{parentId}")
	public String showParentPanel(@PathVariable("parentId") int parentId, Model theModel) {
		Parent parent = parentService.findByParentId(parentId); //accessing parent logged in
		theModel.addAttribute("parent", parent);
		return "parent/student";
	}	
}
