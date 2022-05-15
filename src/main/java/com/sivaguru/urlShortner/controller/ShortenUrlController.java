package com.sivaguru.urlShortner.controller;

import com.sivaguru.urlShortner.dto.ShortenUrlRequestDto;
import com.sivaguru.urlShortner.exception.CustomUrlException;
import com.sivaguru.urlShortner.exception.UrlExpiredException;
import com.sivaguru.urlShortner.exception.UrlNotFoundException;
import com.sivaguru.urlShortner.service.UrlService;
import com.sivaguru.urlShortner.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/tiny")
public class ShortenUrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public String health() throws IOException {
        return "Tiny Url Service Up and running";
    }

    @RequestMapping(value = "/shorten", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> shortenUrl(@RequestBody  ShortenUrlRequestDto shortenUrlRequestDto) {
        try {
            return new ResponseEntity<>(urlService.shortenUrl(shortenUrlRequestDto), HttpStatus.OK);
        } catch (CustomUrlException e) {
            return new ResponseEntity<>(Util.buildResponseEntityErrorObject("Url Already Exists", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> redirectToFullUrl(HttpServletResponse response, @PathVariable("id") String shortenString) {
        try {
            String redirectUrl = urlService.redirectShortenedUrl(shortenString);
            response.sendRedirect(redirectUrl);
        } catch (UrlNotFoundException e) {
            return new ResponseEntity<>(Util.buildResponseEntityErrorObject("Url Not found", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (UrlExpiredException e) {
            return new ResponseEntity<>(Util.buildResponseEntityErrorObject("Url Expired", HttpStatus.IM_USED), HttpStatus.IM_USED);
        } catch (IOException e) {
            return new ResponseEntity<>(Util.buildResponseEntityErrorObject("Unknown Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
