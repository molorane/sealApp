package org.dclm.sealApp.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class ApiException {

    private HttpStatus status;
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;
    private Throwable throwable;


    private ApiException() {
        timestamp = LocalDateTime.now();
    }


    public ApiException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.code = this.status.value();
        this.message = ex.getLocalizedMessage();
        this.debugMessage = message;
        this.throwable = ex.getCause();
    }
}
