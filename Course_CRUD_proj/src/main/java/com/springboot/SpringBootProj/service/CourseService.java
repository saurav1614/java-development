package com.springboot.SpringBootProj.service;
import java.util.List;
import java.util.Optional;

import com.springboot.SpringBootProj.entities.*;

public interface CourseService {
	
	public List<Course> getCourses();
    public Optional<Course> getCourse(long courseId);
    public Course addCourse(Course course);
    public Course updateCourse(Course course);
    public void deleteCourse(long courseId);

}

