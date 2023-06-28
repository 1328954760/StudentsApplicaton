package cuz.students.controller;

import com.google.gson.Gson;
import cuz.students.mapper.CourseMapper;
import cuz.students.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;

    private final Gson gson = new Gson();

    @GetMapping("/course")
    public String getCourseList(){
        List<Course> courseList = courseMapper.selectList(null);
        return gson.toJson(courseList);
    }
}
