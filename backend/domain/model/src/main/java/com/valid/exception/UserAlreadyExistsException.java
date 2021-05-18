package com.valid.exception;

import com.valid.enums.ErrorCode;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private static final String EXISTING_MSG = "A user with first name %1$s and last name %2$s already exists";
    private ErrorCode code;

    public UserAlreadyExistsException(String firstName, String lastName) {
        super(String.format(EXISTING_MSG, firstName, lastName));
        this.code = ErrorCode.USER_ALREADY_EXISTS;
    }

}
