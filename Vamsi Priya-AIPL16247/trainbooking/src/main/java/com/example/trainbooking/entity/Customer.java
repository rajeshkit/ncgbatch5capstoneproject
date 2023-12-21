package com.example.trainbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_master")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "customerIdSequence",allocationSize = 100, initialValue =50000 ,sequenceName = "customerIdSequence")
    @Column(name = "c_id")
    private Long id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "ssn_type")
    private String ssnType;
    @Column(name = "ssn_no")
    private String ssnNo;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;
}