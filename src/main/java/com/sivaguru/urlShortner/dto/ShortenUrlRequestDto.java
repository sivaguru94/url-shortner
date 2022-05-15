package com.sivaguru.urlShortner.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ShortenUrlRequestDto {
    private String url;
    private LocalDateTime ExpireOn;
    private String customShortUrl;
}
