/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.CS241.studentmanagement.dao.GradeDetailsDao;
import com.CS241.studentmanagement.entity.GradeDetails;
import com.CS241.studentmanagement.service.GradeDetailsServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author akash
 */

class GradeTest {

    @InjectMocks
    private GradeDetailsServiceImpl gradeDetailsService;

    @Mock
    private GradeDetailsDao gradeDetailsDao;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Session session;

    @Mock
    private Query<GradeDetails> query;

    private GradeDetails gradeDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gradeDetails = new GradeDetails(1, 85, 90, 78, 88, 92, 95);
        when(entityManager.unwrap(Session.class)).thenReturn(session);
    }

    @Test
    void testServiceSave() {
        gradeDetailsService.save(gradeDetails);
        verify(gradeDetailsDao, times(1)).save(gradeDetails);
    }

    @Test
    void testServiceFindById() {
        when(gradeDetailsDao.findById(anyInt())).thenReturn(gradeDetails);
        GradeDetails foundGradeDetails = gradeDetailsService.findById(1);
        assertNotNull(foundGradeDetails);
        assertEquals(gradeDetails.getId(), foundGradeDetails.getId());
        verify(gradeDetailsDao, times(1)).findById(1);
    }

    @Test
    void testServiceDeleteById() {
        gradeDetailsService.deleteById(1);
        verify(gradeDetailsDao, times(1)).deleteById(1);
    }

    @Test
    void testDaoSave() {
        doNothing().when(session).saveOrUpdate(any(GradeDetails.class));
        gradeDetailsDao.save(gradeDetails);
        verify(session, times(1)).saveOrUpdate(gradeDetails);
    }

    @Test
    void testDaoFindById() {
        when(session.createQuery("from GradeDetails where id = :id", GradeDetails.class)).thenReturn(query);
        when(query.setParameter(eq("id"), anyInt())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(gradeDetails);
        
        GradeDetails foundGradeDetails = gradeDetailsDao.findById(1);
        
        assertNotNull(foundGradeDetails);
        assertEquals(gradeDetails.getId(), foundGradeDetails.getId());
        
        verify(query, times(1)).setParameter("id", 1);
        verify(query, times(1)).getSingleResult();
    }

    @Test
    void testDaoDeleteById() {
        when(session.createQuery("delete from GradeDetails where id = :id")).thenReturn(query);
        when(query.setParameter(eq("id"), anyInt())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);
        
        gradeDetailsDao.deleteById(1);
        
        verify(query, times(1)).setParameter("id", 1);
        verify(query, times(1)).executeUpdate();
    }
}
