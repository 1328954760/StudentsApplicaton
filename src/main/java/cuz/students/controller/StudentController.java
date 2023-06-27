package cuz.students.controller;

import cuz.students.mapper.StudentMapper;
import cuz.students.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import java.util.List;

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
}
