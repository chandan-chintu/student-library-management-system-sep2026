package com.example.student_library_management_system.controller;


import com.example.student_library_management_system.requestdto.TransactionRequestDto;
import com.example.student_library_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction/apis")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        try {
            String response = transactionService.saveTransaction(transactionRequestDto);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Exception occurred : "+e.getMessage());
        }
    }
}
