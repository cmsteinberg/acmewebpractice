package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when details is null or empty
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Required List parameter \'details\' is not present")
public class NoDetailsException extends RuntimeException {
}
