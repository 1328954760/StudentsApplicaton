package cuz.students.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.google.gson.Gson;
import cuz.students.mapper.CourseMapper;
import cuz.students.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
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
    @GetMapping("/course/pie")
    public String getCouValueList() {
        List<Course> courseList = courseMapper.selectList(null);
        Map<String, Double> collect=courseList.stream().filter(x-> ObjectUtils.isNotEmpty(x.getName())).
                collect(Collectors.groupingBy(Course::getName, Collectors.summingDouble(Course::getCredit)));
        List<Map<String, Object>> mapList= new ArrayList<>();
        /*if(CollectionUtils.isNotEmpty(collect)){*/
        for (String key:collect.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", key);
            map.put("value", collect.get(key));
            mapList.add(map);
        }
        return gson.toJson(mapList);
    }
}
