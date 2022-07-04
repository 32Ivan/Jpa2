package com.example.demo.book;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
    private String bookName;

    @Column(name = "book_author", nullable = false, columnDefinition = "TEXT") 
    private String bookAuthor;

//    @ManyToMany
//    @JoinTable(name = "book_student")
//    private List<Student> student;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "book_college", 
//            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
//    private List<College> college;

    public Book(Long id, String bookName, String bookAuthor) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}
