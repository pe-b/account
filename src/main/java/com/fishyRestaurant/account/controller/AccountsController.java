package com.fishyRestaurant.account.controller;

import com.fishyRestaurant.account.domain.Account;
import com.fishyRestaurant.account.exceptions.AccountNotFoundException;
import com.fishyRestaurant.account.repositories.AccountRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AccountsController {

    private final AccountRepository repository;
    private final AccountModelAssembler accountModelAssembler;

    AccountsController(AccountRepository repository, AccountModelAssembler accountModelAssembler) {
        this.repository = repository;
        this.accountModelAssembler = accountModelAssembler;
    }

    @GetMapping("/accounts")
    CollectionModel<EntityModel<Account>> allAccounts() {
        List<EntityModel<Account>> accounts = repository.findAll().stream()
          .map(accountModelAssembler::toModel)
          .collect(Collectors.toList());

        return CollectionModel.of(accounts,
          linkTo(methodOn(AccountsController.class).allAccounts()).withSelfRel());
    }

    @PostMapping("/accounts")
    ResponseEntity<?> createAccount(@RequestBody Account newAccount) {
        EntityModel<Account> entityModel = accountModelAssembler.toModel(repository.save(newAccount));

        return ResponseEntity
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
          .body(entityModel);
    }

    @GetMapping("/accounts/{id}")
    EntityModel<Account> accountById(@PathVariable Long id) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        return accountModelAssembler.toModel(account);
    }

    @PutMapping("/accounts/{id}")
    ResponseEntity<?> editAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        Account updatedAccount = repository.findById(id)
          .map(account -> {
              account.setUsername(newAccount.getUsername());
              account.setFullName(newAccount.getFullName());
              account.setEmail(newAccount.getEmail());
              account.setPhoneNumber(newAccount.getPhoneNumber());
              return repository.save(account);
          }).orElseGet(() ->
              repository.save(newAccount)
          );

        EntityModel<Account> entityModel = accountModelAssembler.toModel(updatedAccount);

        return ResponseEntity
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
          .body(entityModel);
    }

    @DeleteMapping("accounts/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable Long id) throws AccountNotFoundException{
        Account removedAccount = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        repository.deleteById(id);

        return ResponseEntity.accepted().body(removedAccount);
    }


}
