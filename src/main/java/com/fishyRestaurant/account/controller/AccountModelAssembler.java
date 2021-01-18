package com.fishyRestaurant.account.controller;

import com.fishyRestaurant.account.domain.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {

    @Override
    public EntityModel<Account> toModel(Account account) {
        return EntityModel.of(account,
          linkTo(methodOn(AccountsController.class).accountById(account.getId())).withSelfRel(),
          linkTo(methodOn(AccountsController.class).allAccounts()).withRel("accounts"));
    }
}
