package com.sivaguru.urlShortner.dto;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortenUrlResponseDto {
    private String url;
    private String shortenedUrl;
    private LocalDateTime createdOn;
    private LocalDateTime expireOn;
}
