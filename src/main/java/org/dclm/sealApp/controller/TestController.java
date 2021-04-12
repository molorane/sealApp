package org.dclm.sealApp.controller;

import io.swagger.annotations.ApiOperation;
import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.service.AccountService;
import org.dclm.sealApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class TestController {

    @Resource(name = "accountService")
    private AccountService accountService;

    @Autowired
    private PersonService personService;

    @GetMapping("/api/auth/test")
    @ApiOperation(value = "testing")
    public String getIt(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime joiningDate = LocalDateTime.parse(LocalDateTime.now().toString().replace('T',' ').substring(0,19), formatter);
        System.out.println(joiningDate);
        return "Love rest";
    }

    @GetMapping("/api/auth/account/{accountId}")
    public Optional<Account> findByAccountId(@PathVariable("accountId") Long accountId){
        return accountService.findById(accountId);
    }

    @GetMapping("/api/auth/person/{idno}")
    public ResponseEntity<?> getAllAccounts(@PathVariable("idno") String idno){
        return new ResponseEntity<>(personService.findByIdno(idno), HttpStatus.OK);
    }

}
