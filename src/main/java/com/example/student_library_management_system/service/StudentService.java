package com.example.student_library_management_system.service;

import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public Student getStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else {
            throw new RuntimeException("Student with id : "+id+" not found!");
        }
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

     /*
    Pagination - fetching or getting the records or data in the form of pages
    pagenumber - the number of page we want to see(0,1,2,3,4,5...)
    pagesize - total number of records in each page(fixed for each page)

    total number of record - 28, page size - 5
    0th page - 1-5
    1st page - 6-10
    2nd page - 11-15
    3rd page - 16-20
    4th page - 21-25
    5th page - 26-28

    total numbers of records-11, page size-3
    0th page - 1-3
    1st page - 4-6
    2nd page - 7-9
    3rd page - 10-11

     */
    public Page<Student> findStudentByPage(int pageNo, int pageSize){
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize));
        return studentPage;
    }

    // sorting - arranging the records based on ascending or descending order of some fields
    public List<Student> sortStudentByFields(String sortByField, String orderBy){
        List<Student> studentList = null;
        if(orderBy.equalsIgnoreCase("Ascending")) {
            studentList = studentRepository.findAll(Sort.by(sortByField).ascending());
        } else if(orderBy.equalsIgnoreCase("Descending")) {
            studentList = studentRepository.findAll(Sort.by(sortByField).descending());
        }
        return studentList;
    }

    //pagination and sorting
    public Page<Student> findStudentByPageAndSort(int pageNo, int pageSize, String sortByField, String orderBy){
        Page<Student> studentPage = null;
        if(orderBy.equalsIgnoreCase("Ascending")) {
            studentPage = studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by(sortByField).ascending()));
        } else if(orderBy.equalsIgnoreCase("Descending")) {
            studentPage = studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by(sortByField).descending()));
        }
        return studentPage;
    }

    public String deleteStudentById(int id){
        Student existingStudent = getStudentById(id);
        if(existingStudent!=null){
            studentRepository.deleteById(id);
            return "Student with id : "+id+" got deleted successfully!";
        } else {
            throw new RuntimeException("Student with id : "+id+" not found!");
        }
    }

    public String updateStudent(int studentId, StudentRequestDto newStudentRequestDto){
        Student existingStudent = getStudentById(studentId);
        if(existingStudent!=null){
            existingStudent.setName(newStudentRequestDto.getName());
            existingStudent.setDob(newStudentRequestDto.getDob());
            existingStudent.setEmail(newStudentRequestDto.getEmail());
            existingStudent.setDept(newStudentRequestDto.getDept());
            existingStudent.setAddress(newStudentRequestDto.getAddress());
            existingStudent.setSem(newStudentRequestDto.getSem());
            existingStudent.setMobile(newStudentRequestDto.getMobile());
            existingStudent.setGender(newStudentRequestDto.getGender());

            studentRepository.save(existingStudent);

            return "Student with id : "+studentId+" updated successfully!";
        } else {
            throw new RuntimeException("Student with id : "+studentId+" not found!");

        }
    }

    public Student getStudentByEmail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        if(student!=null){
            return student;
        } else {
            throw new RuntimeException("Student with email : "+email+" not found");
        }
    }

    public List<Student> getStudentByDept(String dept){
        List<Student> studentList = studentRepository.getStudentByDept(dept);
        if(studentList!=null){
            return studentList;
        } else {
            throw new RuntimeException("Student with email : "+dept+" not found");
        }
    }
}
