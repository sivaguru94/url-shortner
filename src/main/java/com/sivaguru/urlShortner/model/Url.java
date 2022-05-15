package com.sivaguru.urlShortner.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    private String id;
    private String url;
    private LocalDateTime createdOn;
    private LocalDateTime expireOn;
    private String customShortUrl;

    public boolean isExpired() {
        if (this.expireOn == null) {
            return false;
        }
        return LocalDateTime.now().isAfter(this.expireOn);
    }
}
