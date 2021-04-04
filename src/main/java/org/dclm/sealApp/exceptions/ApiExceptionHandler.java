package org.dclm.sealApp.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ApiExceptionHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiException> handleApiAccessDeiniedException(AccessDeniedException ex){ //,HttpHeaders headers, WebRequest request
        //1. Create a payload
        String error = "Access denied";
        ApiException apiException = new ApiException(HttpStatus.FORBIDDEN, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiException> handleApiBadCredentialsException(BadCredentialsException ex){
        //1. Create a payload
        String error = "Username or password invalid!";
        ApiException apiException = new ApiException(HttpStatus.UNAUTHORIZED, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = AccountExpiredException.class)
    public ResponseEntity<ApiException> handleApiAccountExpiredException(AccountExpiredException ex){
        //1. Create a payload
        String error = "Sorry! your account has expired! You must change your password to extend an expiry date pf your account.";
        ApiException apiException = new ApiException(HttpStatus.UNAUTHORIZED, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = DisabledException.class)
    public ResponseEntity<ApiException> handleApiDisabledException(DisabledException ex){
        //1. Create a payload
        String error = "Sorry! your account is not active. Contact administrator to activate your account!";
        ApiException apiException = new ApiException(HttpStatus.UNAUTHORIZED, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = LockedException.class)
    public ResponseEntity<ApiException> handleApiLockedException(LockedException ex){
        //1. Create a payload
        String error = "Sorry! your account is locked. Contact administrator to unlock your account.";
        ApiException apiException = new ApiException(HttpStatus.UNAUTHORIZED, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ApiException> handleApiNoHandlerFoundException(NoHandlerFoundException ex){
        //1. Create a payload
        String error = "Page not found";
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ApiException> handleApiDataNotFoundException(DataNotFoundException ex){
        //1. Create a payload
        String error = "Data not found denied";
        ApiException apiException = new ApiException(HttpStatus.FORBIDDEN, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = PasswordsMismatchException.class)
    public ResponseEntity<ApiException> handleApiPasswordsMismatchException(PasswordsMismatchException ex){
        //1. Create a payload
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, ex.getErrormessage(), ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = DuplicateEntryException.class)
    public ResponseEntity<ApiException> handleApiUserAlreadyExistException(DuplicateEntryException ex){
        //1. Create a payload
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getErrormessage(), ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiException> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiException(HttpStatus.NOT_FOUND, error, ex));
    }

    private ResponseEntity<ApiException> buildResponseEntity(ApiException apiError) {
        logger.error(apiError.getDebugMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
