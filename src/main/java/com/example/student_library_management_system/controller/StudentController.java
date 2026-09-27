package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/apis")
public class StudentController {

     /*
User request (either postman or UI) -> dispatcher servlet ->
corresponding API (controller class based on endpoint or url) ->
service (business logic) -> repository (to perform various database operations)
 */

    @Autowired
    StudentService studentService;

    //standard way for returning response - ResponseEntity (it contains http status code and response body)

    @PostMapping("/save")
    public ResponseEntity<?> SaveStudent(@RequestBody StudentRequestDto studentRequestDto){
        try {
            StudentResponseDto studentResponseDto = studentService.saveStudent(studentRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDto);
            // return ResponseEntity.status(201).body(studentResponseDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }

    }
}
