package com.sivaguru.urlShortner.service;

import com.sivaguru.urlShortner.dao.UrlRepository;
import com.sivaguru.urlShortner.dto.ShortenUrlRequestDto;
import com.sivaguru.urlShortner.dto.ShortenUrlResponseDto;
import com.sivaguru.urlShortner.exception.CustomUrlException;
import com.sivaguru.urlShortner.exception.UrlExpiredException;
import com.sivaguru.urlShortner.exception.UrlNotFoundException;
import com.sivaguru.urlShortner.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    UrlRepository urlRepository;

    public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto shortenUrlRequestDto) throws CustomUrlException {
        Url url = urlRepository.findByUrl(shortenUrlRequestDto.getUrl());
        if (shortenUrlRequestDto.getCustomShortUrl() != null &&
                urlRepository.findByCustomShortUrl(shortenUrlRequestDto.getCustomShortUrl()) != null) {
            throw new CustomUrlException();
        }
        if (url == null) {
            url = Url.builder().url(shortenUrlRequestDto.getUrl()).createdOn(LocalDateTime.now()).build();
        } else {
            url.setCustomShortUrl(shortenUrlRequestDto.getCustomShortUrl());
            url.setExpireOn(shortenUrlRequestDto.getExpireOn());
        }
        url = urlRepository.save(url);
        String shortenedUrl = Optional.ofNullable(url.getCustomShortUrl()).orElse(url.getId());
        return ShortenUrlResponseDto.builder().shortenedUrl(shortenedUrl)
                .expireOn(url.getExpireOn()).createdOn(url.getCreatedOn()).url(url.getUrl()).build();
    }

    @Override
    public String redirectShortenedUrl(String id) throws UrlNotFoundException, UrlExpiredException {
        Url url = Optional.of(urlRepository.findByCustomShortUrl(id)).orElseGet(() -> urlRepository.findById(id).get());
        if (url == null) {
            throw new UrlNotFoundException();
        }
        if (url.isExpired()) {
            throw new UrlExpiredException();
        }
        return url.getUrl();
    }
}
