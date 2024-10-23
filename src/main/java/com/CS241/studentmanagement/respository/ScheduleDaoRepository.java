/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.respository;

import com.CS241.studentmanagement.dao.ScheduleDao;
import com.CS241.studentmanagement.entity.Schedule;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author akash
 */

@Repository
public class ScheduleDaoRepository implements ScheduleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveSchedule(Schedule schedule) {
        entityManager.persist(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return entityManager.createQuery("from Schedule", Schedule.class).getResultList();
    }
}
