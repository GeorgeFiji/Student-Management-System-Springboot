/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import com.CS241.studentmanagement.entity.Schedule;

/**
 * @author akash
 */

public interface ScheduleService {

    public void saveSchedule(Schedule schedule);

    Schedule getSchedule();
    
}
