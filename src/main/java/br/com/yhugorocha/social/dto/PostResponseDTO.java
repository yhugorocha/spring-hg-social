package br.com.yhugorocha.social.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String caption;
    private String imageUrl;
    private String videoUrl;
    private LocalDateTime createdAt;
}
