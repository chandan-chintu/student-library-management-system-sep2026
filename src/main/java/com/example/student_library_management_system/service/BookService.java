package com.example.student_library_management_system.service;

import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.repository.BookRepository;
import com.example.student_library_management_system.requestdto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public String saveBook(BookRequestDto bookRequestDto){
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setPages(bookRequestDto.getPages());
        book.setCategory(bookRequestDto.getCategory());
        book.setRackNo(bookRequestDto.getRackNo());
        book.setPublisherName(bookRequestDto.getPublisherName());
        book.setPublishedDate(bookRequestDto.getPublishedDate());
        book.setAvailability(bookRequestDto.getAvailability());

        bookRepository.save(book);
        return "Book saved successfully";

    }
}
