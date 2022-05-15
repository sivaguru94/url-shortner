package com.sivaguru.urlShortner.service;

import com.sivaguru.urlShortner.dto.ShortenUrlRequestDto;
import com.sivaguru.urlShortner.dto.ShortenUrlResponseDto;
import com.sivaguru.urlShortner.exception.CustomUrlException;
import com.sivaguru.urlShortner.exception.UrlExpiredException;
import com.sivaguru.urlShortner.exception.UrlNotFoundException;

public interface UrlService {
    ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto shortenUrlRequestDto) throws CustomUrlException;
    String redirectShortenedUrl(String id) throws UrlNotFoundException, UrlExpiredException;
}
