package org.dclm.sealApp.service.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dclm.sealApp.config.security.CustomUserDetails;
import org.dclm.sealApp.exceptions.DataNotFoundException;
import org.dclm.sealApp.exceptions.DuplicateEntryException;
import org.dclm.sealApp.exceptions.PasswordsMismatchException;
import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.model.Person;
import org.dclm.sealApp.model.Role;
import org.dclm.sealApp.model.dto.RegisterDto;
import org.dclm.sealApp.repository.AccountRepository;
import org.dclm.sealApp.repository.RoleRepository;
import org.dclm.sealApp.service.AccountService;
import org.dclm.sealApp.service.PersonService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Transactional
@AllArgsConstructor
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private final AccountRepository accountRepository;
    private final PersonService personService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalUser = accountRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Invalid Username or password"));
        toList(optionalUser.get().getRoles());
        return optionalUser.map(users -> new CustomUserDetails(users)).get();
    }

    public void toList(Set<Role> roles) {
        for (Role role : roles) {
            logger.info("Role: " + role.getName());
        }
    }


    @Override
    @Transactional
    public Account register(RegisterDto user) {

        if(!user.getAccountDto().getPassword().equals(user.getAccountDto().getConfirmPassword())){
            throw new PasswordsMismatchException("Sorry! Passwords do not match.");
        }else if(accountRepository.findByEmail(user.getAccountDto().getEmail()).isPresent()){
            throw new DuplicateEntryException("Email "+user.getAccountDto().getEmail()+" taken already!!");
        }else if(accountRepository.findByUsername(user.getAccountDto().getUsername()).isPresent()){
            throw new DuplicateEntryException("Username "+user.getAccountDto().getUsername()+" taken already!!");
        }

        Account account = new Account();
        account.setUsername(user.getAccountDto().getUsername());
        account.setPassword(bcryptEncoder.encode(user.getAccountDto().getPassword()));
        account.setEmail(user.getAccountDto().getEmail());
        Role role = roleRepository.findByName("MEMBER").get();
        account.setPassword(bcryptEncoder.encode(user.getAccountDto().getPassword()));
        account.setRoles(new HashSet<Role>(Arrays.asList(role)));

        account = accountRepository.save(account);

        Person person = new Person();
        person.setAccount(account);
        person.setFirstName(user.getPersonDto().getFirstName());
        person.setLastName(user.getPersonDto().getLastName());
        person.setOtherName(user.getPersonDto().getOtherName());
        person.setGender(user.getPersonDto().getGender());
        person.setDob(user.getPersonDto().getDob());
        person.setMaritalStatus(user.getPersonDto().getMaritalStatus());
        person.setRace(user.getPersonDto().getRace());
        person.setRecordedBy(account);
        personService.addNewPerson(person);
        return account;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        Optional<Account> account = accountRepository.findByEmail(username);
        account.orElseThrow(() -> new DataNotFoundException("Invalid username "+username));
        return accountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        account.orElseThrow(() -> new DataNotFoundException("Invalid email "+email));
        return account;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        account.orElseThrow(() -> new DataNotFoundException("Invalid user id "+accountId));
        return account;
    }

    public Account getCurrentUser() {
        Account user = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountRepository.findByUsername(user.getUsername()).get();
    }
}
