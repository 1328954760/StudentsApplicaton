package cuz.students.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import cuz.students.StudentsApplication;
import cuz.students.mapper.CourseMapper;
import cuz.students.mapper.ScoreMapper;
import cuz.students.mapper.StudentMapper;
import cuz.students.model.Course;
import cuz.students.model.Score;
import cuz.students.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GpaController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    private final Gson gson = new Gson();

    @GetMapping("/gpa")
    public String getStudentGPA(){
        List<Student> studentList = studentMapper.selectList(null);
        for(Student s : studentList){
            String sid = s.getId();
            QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
            scoreQueryWrapper.eq("sid",sid);
            List<Score> scoreList = scoreMapper.selectList(scoreQueryWrapper);

            double sumPoint = 0;
            double sumCredit = 0;
            for(Score sc : scoreList){
                double score = sc.getScore();
                String cid = sc.getCid();
                Course c = courseMapper.selectById(cid);
                double credit = c.getCredit();
                double point = score / 10 - 5.0;
                if (score < 60.0) {
                    point = 0;
                }
                sumPoint += point * credit;
                sumCredit += credit;
            }
            double gpa = sumPoint / sumCredit;
            s.setGpa(gpa);

            studentMapper.updateById(s);
        }

        return gson.toJson(studentList);
    }
}
