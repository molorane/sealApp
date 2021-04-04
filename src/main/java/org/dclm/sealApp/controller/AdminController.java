package org.dclm.sealApp.controller;

import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Resource(name = "accountService")
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        List<Account> accounts = accountService.findAll();
        if (accounts == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/accounts/{account_id}")
    public ResponseEntity<Account> findById(@PathVariable("account_id") Long account_id) {
        return new ResponseEntity<Account>(accountService.findById(account_id).get(), HttpStatus.CREATED);
    }


}
