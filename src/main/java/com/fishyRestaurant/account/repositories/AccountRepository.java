package com.fishyRestaurant.account.repositories;

import com.fishyRestaurant.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
