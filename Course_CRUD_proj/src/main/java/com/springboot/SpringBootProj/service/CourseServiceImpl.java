package com.springboot.SpringBootProj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.SpringBootProj.dao.CourseDao;
import com.springboot.SpringBootProj.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
    private CourseDao courseDao;
		
	public List<Course> getCourses() {
		
		return courseDao.findAll();
	}
	
	@SuppressWarnings("deprecation")
	public Optional<Course> getCourse(long courseId) {
		
		return courseDao.findById(courseId);
	}
	
	public Course addCourse(Course course) {
	
		courseDao.save(course);
	    return course;
	}
	
	public Course updateCourse(Course course) {
	
		 courseDao.save(course);
         return course;
	}
	
	public void deleteCourse(long courseId) {
		Course entity =  courseDao.getOne(courseId);
        courseDao.delete(entity);
		
	}

}
