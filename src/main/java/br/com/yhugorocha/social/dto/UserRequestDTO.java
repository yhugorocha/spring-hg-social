package br.com.yhugorocha.social.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    private String email;
    private String password;
}
