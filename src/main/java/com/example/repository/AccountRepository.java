package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> { 
    Account findAccountByUsername(String username);
    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password")
    Account findAccountByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
  
