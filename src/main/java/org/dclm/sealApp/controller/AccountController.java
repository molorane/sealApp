package org.dclm.sealApp.controller;

import org.dclm.sealApp.exceptions.DataNotFoundException;
import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.model.converter.AccountConverter;
import org.dclm.sealApp.model.res.AccountRes;
import org.dclm.sealApp.model.res.RoleRes;
import org.dclm.sealApp.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    @Resource(name = "accountService")
    private AccountService accountService;



    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        Page<Account> accounts  =  accountService.findAll();

        List<AccountRes> response = accounts.stream().map(a ->{

            AccountRes accountRes = AccountConverter.convertAccountToAccountRes(a);
            Link selfLink = linkTo(methodOn(AccountController.class)
                    .findById(a.getId())).withSelfRel();
            accountRes.add(selfLink);

            Link rolesLink = linkTo(methodOn(AccountController.class)
                    .findAccountRoles(a.getId())).withRel("roles");
            accountRes.add(rolesLink);

            return accountRes;

        }).collect(Collectors.toList());


        if(accounts == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountRes> findById(@PathVariable("accountId") Long accountId) {

        Account account = accountService.findById(accountId).orElseThrow(
                () -> new DataNotFoundException("Account with id "+accountId+" not found.")
        );
        AccountRes accountRes = AccountConverter.convertAccountToAccountRes(account);
        Link selfLink = linkTo(methodOn(AccountController.class)
                .findById(account.getId())).withSelfRel();
        accountRes.add(selfLink);

        Link rolesLink = linkTo(methodOn(AccountController.class)
                .findAccountRoles(account.getId())).withRel("roles");
        accountRes.add(rolesLink);


        Link accounts = linkTo(methodOn(AccountController.class)
                .getAllAccounts()).withRel("accounts");
        accountRes.add(accounts);

        return new ResponseEntity<AccountRes>(accountRes, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{accountId}/roles")
    public ResponseEntity<?> findAccountRoles(@PathVariable("accountId") Long accountId) {

        Account account = accountService.findById(accountId).orElseThrow(
                () -> new DataNotFoundException("Account with id "+accountId+" not found.")
        );

        List<RoleRes> roles = account.getRoles().stream().map(role -> {

            RoleRes accountRole = AccountConverter.convertRoleToRoleRes(role);
            Link selfLink = linkTo(methodOn(AccountController.class)
                    .findAccountRoleId(account.getId(), role.getId())).withSelfRel();
            accountRole.add(selfLink);

            Link back = linkTo(methodOn(AccountController.class)
                    .findById(account.getId())).withRel("account");
            accountRole.add(back);

            return accountRole;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(roles, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{accountId}/roles/{roleId}")
    public ResponseEntity<?> findAccountRoleId(@PathVariable("accountId") Long accountId, @PathVariable("roleId") Long roleId) {

        Account account = accountService.findById(accountId).orElseThrow(
                () -> new DataNotFoundException("Account with id "+accountId+" not found.")
        );

        Optional<RoleRes> roles = account.getRoles().stream().filter(role -> role.getId().equals(roleId)).map(role -> {

            RoleRes accountRole = AccountConverter.convertRoleToRoleRes(role);
            Link selfLink = linkTo(methodOn(AccountController.class)
                    .findById(account.getId())).withSelfRel();
            accountRole.add(selfLink);

            return accountRole;
        }).findFirst();

        return new ResponseEntity<>(roles.get(), HttpStatus.ACCEPTED);
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
