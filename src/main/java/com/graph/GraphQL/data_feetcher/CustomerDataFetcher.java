package com.graph.GraphQL.data_feetcher;

import com.graph.GraphQL.dto.AccountInput;
import com.graph.GraphQL.dto.CustomerInput;
import com.graph.GraphQL.entity.Account;
import com.graph.GraphQL.entity.Customer;
import com.graph.GraphQL.repo.AccountRepository;
import com.graph.GraphQL.repo.CustomerRepository;
import com.netflix.graphql.dgs.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
@RequiredArgsConstructor
public class CustomerDataFetcher {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @DgsData(parentType = "Query",field = "customer")
    public Customer customer(String id) {
        return customerRepository.findById(id).get();
    }

    @DgsData(parentType = "Query", field = "customers")
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    //new cxommit
    @DgsData(parentType = "Customer", field = "accounts")
    public List<Account> accounts(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
        Customer customer = dgsDataFetchingEnvironment.getSource();
        List<Account> accountList = new ArrayList<>();
        for (Account account : customer.getAccounts()) {
            Account accountResponse = accountRepository.findById(account.getAccountId()).get();
            accountList.add(accountResponse);
        }
        return accountList;
    }

   @DgsMutation
    public Customer customer(CustomerInput customerInput) {
        Customer customer = Customer.builder()
                .contact(customerInput.getContact())
                .name(customerInput.getName())
                .gender(customerInput.getGender())
                .mail(customerInput.getMail())
                .accounts(mapCustomerAccounts(customerInput.getAccounts()))
                .build();
        Customer customerResponse = customerRepository.save(customer);
        return customerResponse;
    }

    private List<Account> mapCustomerAccounts(List<AccountInput> accountIpnut) {
        List<Account> accountsList = accountIpnut.stream().map(accInput -> {
            Account account = Account.builder()
                    .accountBalance(accInput.getAccountBalance())
                    .accountNumber(accInput.getAccountNumber())
                    .accountStatus(accInput.getAccountStatus())
                    .build();
            return account;
        }).collect(Collectors.toList());
        return accountsList;
    }
}
