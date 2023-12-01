package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    public Account register(Account user) {return accountRepository.save(user);}
    public Account login(Account user) {return accountRepository.findAccountByUsernameAndPassword(user.getUsername(), user.getPassword());}
    public Account findAccountByUsername(String username) { return accountRepository.findAccountByUsername(username);}
    public Account findAccountByUsernameAndPassword(String username, String password) {return accountRepository.findAccountByUsernameAndPassword(username, password);}
}

