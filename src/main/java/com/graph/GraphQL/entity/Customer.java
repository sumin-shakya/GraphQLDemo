package com.graph.GraphQL.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_NUMBER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerNumber;
    private String name;
    private String gender;
    private Integer contact;
    private String mail;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;
}
