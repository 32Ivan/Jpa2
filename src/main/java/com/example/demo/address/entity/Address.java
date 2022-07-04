package com.example.demo.address.entity;

import com.example.demo.student.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

    @Column(name = "city", columnDefinition = "TEXT")
    private String city;

    @Column(name = "type", columnDefinition = "TEXT")
    private String addressType;

    @OneToOne(mappedBy = "address")
    private Student student;

    @Override
    public String toString() {
        return "Address{" +
                "Id=" + Id +
                ", city='" + city + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}

