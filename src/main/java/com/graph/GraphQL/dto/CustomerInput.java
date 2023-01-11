package com.graph.GraphQL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInput {

    private String name;
    private String gender;
    private Integer contact;
    private String mail;
    private List<AccountInput> accounts;
}
