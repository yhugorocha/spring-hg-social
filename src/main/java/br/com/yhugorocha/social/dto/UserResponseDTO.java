package br.com.yhugorocha.social.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    private String email;
    private List<UserFollowResponseDTO> following;
    private List<UserFollowResponseDTO> followers;
    private LocalDateTime createdAt;
}
