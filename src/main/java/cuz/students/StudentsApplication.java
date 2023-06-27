package cuz.students;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("cuz.students.mapper")
public class StudentsApplication {

	@GetMapping("/")
	public String index() {
		return "Hello Spring Boot!";
	}

	/*
	@GetMapping("/student")
	public String student() {
		return "Hello Student!";
	}
	 */

	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}

}
