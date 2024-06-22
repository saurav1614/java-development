package com.springboot.SpringBootProj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.SpringBootProj.entities.Course;
import com.springboot.SpringBootProj.service.CourseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MyController {
	
	@Autowired
    private CourseService courseService;
    
    @GetMapping("/home")  
    public String home()
    {

        return "this is home page";
     }
    
    @GetMapping("/about")
    public String about()
    {
    	return "This is about page";
    }
    
    // get all courses from course table
   @GetMapping("/courses")
   public List<Course> getCourses()
   {
     
	   return this.courseService.getCourses();
  }

// single course get
@GetMapping("/courses/{courseId}")
public Optional<Course> getCourse(@PathVariable String courseId)
{
    return this.courseService.getCourse(Long.parseLong(courseId));
}

// course add

@PostMapping("/courses")
public Course addCourse(@RequestBody Course course)
{

  return this.courseService.addCourse(course);

}
// update course using PUT request
@CrossOrigin(origins = "http://localhost:8080")
@PutMapping("/courses")
public Course updateCourse(@RequestBody Course course)
{

    return this.courseService.updateCourse(course);

}

@DeleteMapping("/courses/{courseId}")
public void deleteCourse(@PathVariable String courseId)
{

  this.courseService.deleteCourse(Long.parseLong(courseId));
}
	
	

}
