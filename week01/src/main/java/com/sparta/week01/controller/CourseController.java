package com.sparta.week01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @GetMapping("/courses")
    public Course getCourses() {
        Course course = new Course();
        course.setTitle(" ");
        course.setDays(35);
        course.setTutor(" ");
        return course;
    }
}