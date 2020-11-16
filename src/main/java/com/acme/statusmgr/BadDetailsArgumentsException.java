package com.acme.statusmgr;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when details contains anything other than  memory, operations, or extensions
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Invalid details option")
public class BadDetailsArgumentsException extends RuntimeException {
}
