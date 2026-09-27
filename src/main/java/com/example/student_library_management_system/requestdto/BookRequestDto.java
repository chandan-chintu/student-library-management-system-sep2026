package com.example.student_library_management_system.requestdto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookRequestDto {

    private String title;
    private String publisherName;
    private String publishedDate;
    private int pages;
    private Boolean availability;
    private String category;
    private String rackNo;
}
