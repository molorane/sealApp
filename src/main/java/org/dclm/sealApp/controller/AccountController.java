package org.dclm.sealApp.controller;

import org.dclm.sealApp.exceptions.DataNotFoundException;
import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Resource(name = "accountService")
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> findById(@PathVariable("accountId") Long accountId) {

        Account account = accountService.findById(accountId).orElseThrow(
                () -> new DataNotFoundException("Account with id "+accountId+" not found.")
        );
        return new ResponseEntity<Account>(account, HttpStatus.ACCEPTED);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Account> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<Account>(accountService.findByUsername(username).get(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Account> findByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<Account>(accountService.findByEmail(email).get(), HttpStatus.ACCEPTED);
    }
}
