package org.dclm.sealApp.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dclm.sealApp.config.jwt.JwtProvider;
import org.dclm.sealApp.config.security.CustomUserDetails;
import org.dclm.sealApp.exceptions.PasswordsMismatchException;
import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.model.dto.AuthenticationDto;
import org.dclm.sealApp.model.dto.LoginDto;
import org.dclm.sealApp.model.dto.RegisterDto;
import org.dclm.sealApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Resource(name = "accountService")
    private AccountService accountService;

    private AuthenticationManager authenticationManager;
    private JwtProvider jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginUser) throws AuthenticationException {
        logger.info("signin called");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        AuthenticationDto authenticationDto = new AuthenticationDto(
                customUserDetails.getAccount().getId(),
                customUserDetails.getAccount().getUsername(),
                customUserDetails.getAccount().getStatus(),
                customUserDetails.getAccount().isLocked(),
                customUserDetails.getAccount().isActive(),
                customUserDetails.getAccount().getCreatedDate(),
                customUserDetails.getAccount().getExpiryDate(),
                customUserDetails.getAccount().getProfile(),
                customUserDetails.getAccount().getRoles(),
                token
        );
        return ResponseEntity.ok(authenticationDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody @Valid RegisterDto user, BindingResult result) {
        logger.info("User: " + user);
        Set<String> errorlist = null;
        if (result.hasErrors()) {
            errorlist = new HashSet<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                errorlist.add(error.getDefaultMessage());
            }
            throw new PasswordsMismatchException(errorlist.toString());
        }
        return new ResponseEntity<Account>(accountService.register(user), HttpStatus.CREATED);
    }
}
