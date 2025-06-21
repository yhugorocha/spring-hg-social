package br.com.yhugorocha.social.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDateTime createdAt;
}
