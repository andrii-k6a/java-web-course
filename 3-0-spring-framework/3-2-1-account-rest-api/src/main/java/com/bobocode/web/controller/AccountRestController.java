package com.bobocode.web.controller;

import com.bobocode.dao.AccountDao;
import com.bobocode.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountDao accountDao;

    @GetMapping
    public List<Account> getAccounts() {
        return accountDao.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable long id) {
        return accountDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveAccount(Account account) {
        return accountDao.save(account);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAccount(@PathVariable long id, Account account) {
        if (id != account.getId()) {
            throw new IllegalStateException();
        }
        accountDao.save(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable long id) {
        Account account = accountDao.findById(id);
        accountDao.remove(account);
    }
}
