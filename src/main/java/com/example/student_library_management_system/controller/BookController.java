package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.BookRequestDto;
import com.example.student_library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/apis")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody BookRequestDto bookRequestDto){
        try {
            String response = bookService.saveBook(bookRequestDto);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Exception occurred : "+e.getMessage());
        }
    }
}
