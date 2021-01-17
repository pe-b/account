package com.fishyRestaurant.account.controller;

import com.fishyRestaurant.account.domain.Account;
import com.fishyRestaurant.account.exceptions.AccountNotFoundException;
import com.fishyRestaurant.account.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {

    private final AccountRepository repository;

    AccountsController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts")
    List<Account> getAll() {
        return repository.findAll();
    }

    @PostMapping("/accounts")
    Account createAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    @GetMapping("/accounts/{id}")
    Account getById(@PathVariable Long id) throws AccountNotFoundException {
        return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/accounts/{id}")
    Account editAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        return repository.findById(id).map(account -> {
            account.setUsername(newAccount.getUsername());
            account.setFullName(newAccount.getFullName());
            account.setEmail(newAccount.getEmail());
            account.setPhoneNumber(newAccount.getPhoneNumber());
            return repository.save(account);
        }).orElseGet(() -> repository.save(newAccount));
    }

    @DeleteMapping("accounts/{id}")
    String deleteAccount(@PathVariable Long id) throws AccountNotFoundException{
        Account removedAccount = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        repository.deleteById(id);
        return removedAccount.toString();
    }
}
