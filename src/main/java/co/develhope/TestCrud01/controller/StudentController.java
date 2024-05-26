package co.develhope.TestCrud01.controller;

import co.develhope.TestCrud01.entities.Student;
import co.develhope.TestCrud01.repositories.StudentRepository;
import co.develhope.TestCrud01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    //Create - CRUD
    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student){
        Student newStudent = studentRepository.save(student);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

    //Read All - CRUD
    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    //Read single student with ID - CRUD
    @GetMapping
    public ResponseEntity<Student> getSingle(@PathVariable Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return ResponseEntity.of(optionalStudent);

    }
    //Update single student with id- CRUD
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id,@RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.get().setName(student.getName());
        optionalStudent.get().setSurname(student.getSurname());
        return ResponseEntity.of(optionalStudent);
    }

    //Update working status - CRUD
    @PutMapping("/{id}/working")
    public ResponseEntity<Student> setStudentActivation(@PathVariable Long id, @RequestParam boolean working){

        studentService.setStudentWorking(id, working);
        return ResponseEntity.ok().build();
    }
    //Delete id - CURD
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
