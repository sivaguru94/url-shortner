package com.sivaguru.urlShortner.model;

import lombok.Data;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class Error {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
