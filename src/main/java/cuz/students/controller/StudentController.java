package cuz.students.controller;

import cuz.students.mapper.StudentMapper;
import cuz.students.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.List;

@CrossOrigin("*")
@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    private final Gson gson = new Gson();

    @GetMapping("/student")
    public String getStudentList(){
        List<Student> studentList = studentMapper.selectList(null);
        return gson.toJson(studentList);
    }

    @GetMapping("student/{id}")
    public String getStudentById(@PathVariable("id") String id){
        Student s = studentMapper.selectById(id);
        return gson.toJson(s);
    }

    @PostMapping("/addstudent")
    public void addStudent(@RequestBody Student student){
        studentMapper.insert(student);
    }

    @DeleteMapping("/delstudent")
    public void delStudent(@RequestBody Student student){
        studentMapper.deleteById(student);
    }

    @PutMapping("/updatestudent")
    public void updateStudent(@RequestBody Student student){
        studentMapper.updateById(student);
    }
}
