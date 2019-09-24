package ee.tanel.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.tanel.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private	List<Student> students;
	
	// define @PostConsrtuct to load the data only once
	
	@PostConstruct
	public void loadData() {
		
		students = new ArrayList<>();
		students.add(new Student("Tanel", "Ehatamm"));
		students.add(new Student("Martin", "Muhv"));
		students.add(new Student("Kaspar", "Kaasik"));
	}
	
	@GetMapping("/students") 
	public List<Student> getStudents() {
		return students;
		
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if (students.size() < studentId || studentId < 0) {
			throw new StudentNotFoundException("Student not found - " + studentId);
		}
		
		return students.get(studentId);
	}

}
