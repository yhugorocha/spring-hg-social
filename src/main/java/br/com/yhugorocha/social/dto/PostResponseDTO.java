package br.com.yhugorocha.social.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String caption;
    private String imageUrl;
    private String videoUrl;
    private List<UserLikeResponseDTO> likes;
    private LocalDateTime createdAt;
}
