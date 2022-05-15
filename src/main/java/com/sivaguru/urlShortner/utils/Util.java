package com.sivaguru.urlShortner.utils;

import com.sivaguru.urlShortner.model.Error;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class Util {

    public static UUID getRandomUUID() {
        return UUID.randomUUID();
    }
    public static Error.ErrorBuilder buildResponseEntityErrorObject(String message, HttpStatus httpStatus) {
        return Error.builder().message(message).status(httpStatus);
    }
}
