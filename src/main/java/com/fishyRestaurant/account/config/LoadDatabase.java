package com.fishyRestaurant.account.config;

import com.fishyRestaurant.account.domain.Account;
import com.fishyRestaurant.account.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Account("joey11", "Joe White", "joewhite@gmail.com", "07543333331")));
            log.info("Preloading " + repository.save(new Account("vicks", "Victor Daniels", "vd@gmail.com", "07543333332")));
            log.info("Preloading " + repository.save(new Account("donnie", "Dan Smith", "SmithD@gmail.com", "07543333333")));
        };
    }

}
