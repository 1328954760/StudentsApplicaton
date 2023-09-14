package cuz.students.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import cuz.students.mapper.StudentMapper;
import cuz.students.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    private final Gson gson = new Gson();

    @GetMapping("/student") // 显示学生信息
    public String getStudentList(){
        List<Student> studentList = studentMapper.selectList(null);
        return gson.toJson(studentList);
    }

    @GetMapping("student/{id}") // 根据学号显示学生信息
    public String getStudentById(@PathVariable("id") String id){
        Student s = studentMapper.selectById(id);
        return gson.toJson(s);
    }

    @PostMapping("/addstudent") // 添加学生信息
    public void addStudent(@RequestBody Student student){
        studentMapper.insert(student);
    }

    @DeleteMapping("/delstudent") // 删除学生信息
    public void delStudent(@RequestBody Student student){
        studentMapper.deleteById(student);
    }

    @PutMapping("/updatestudent") // 更新学生信息
    public void updateStudent(@RequestBody Student student){
        studentMapper.updateById(student);
    }

    @GetMapping("/student/pie") // 学生信息处理
    public String getStuValueList() {
        List<Student> studentList = studentMapper.selectList(null);
        Map<String, Double> collect=studentList.stream().filter(x-> ObjectUtils.isNotEmpty(x.getName())).
                collect(Collectors.groupingBy(Student::getName, Collectors.summingDouble(Student::getGpa)));
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
