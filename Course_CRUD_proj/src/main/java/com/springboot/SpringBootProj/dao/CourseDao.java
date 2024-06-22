package com.springboot.SpringBootProj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.SpringBootProj.entities.Course;

public interface CourseDao extends JpaRepository<Course,Long> {

}
