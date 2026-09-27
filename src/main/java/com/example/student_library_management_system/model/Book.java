package com.example.student_library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "book_details")
@Data
public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="publisher_name",nullable = false)
    private String publisherName;

    @Column(name="published_date",nullable = false)
    private String publishedDate;

    @Column(name="pages",nullable = false)
    private int pages;

    @Column(name="availability",nullable = false)
    private Boolean availability;

    @Column(name="category",nullable = false)
    private String category;

    @Column(name="rack_no",nullable = false)
    private String rackNo;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;
}
