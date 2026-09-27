package com.example.student_library_management_system.service;

import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto){
        // convert the request dto into model class
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setSem(studentRequestDto.getSem());
        student.setEmail(studentRequestDto.getEmail());
        student.setDob(studentRequestDto.getDob());
        student.setGender(studentRequestDto.getGender());
        student.setAddress(studentRequestDto.getAddress());
        student.setDept(studentRequestDto.getDept());
        student.setMobile(studentRequestDto.getMobile());

        // whenever student adds, card also gets added as part of cascading
        Card card = new Card();
        card.setCardStatus("ACTIVE");
        card.setExpiryDate(LocalDate.now().plusYears(3).toString());

        card.setStudent(student);
        student.setCard(card);

        student = studentRepository.save(student);

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setMessage("Student saved successfully!");
        studentResponseDto.setSavedStudent(student);

        return studentResponseDto;
    }
}
