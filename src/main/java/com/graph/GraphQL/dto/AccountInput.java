package com.graph.GraphQL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInput {

    private Integer accountNumber;
    private String accountStatus;
    private Double accountBalance;
}
