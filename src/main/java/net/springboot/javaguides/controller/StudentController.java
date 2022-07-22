package net.springboot.javaguides.controller;

import net.sf.jasperreports.engine.JRException;
import net.springboot.javaguides.entity.Student;
import net.springboot.javaguides.service.ReportService;
import net.springboot.javaguides.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/students/")
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	private ReportService report;

	@GetMapping("/reports/{format}/{categoryId}")
	public ResponseEntity<String> reporting(@PathVariable String format, @PathVariable int categoryId) throws JRException, FileNotFoundException {
		String response = this.report.exportReport(format, categoryId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Student>> students() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
		return new ResponseEntity<>(this.service.addStudent(student), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @Valid @RequestBody Student student) {
		return new ResponseEntity<>(service.updateStudent(id, student), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteStudent(@PathVariable ("id") long id) {
		this.service.deleteStudent(id);
		return ResponseEntity.ok().body("Suppression effectuée avec succès !");
	}
}
