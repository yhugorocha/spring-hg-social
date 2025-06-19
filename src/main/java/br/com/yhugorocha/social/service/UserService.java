package br.com.yhugorocha.social.service;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO register(UserRequestDTO user);
    UserResponseDTO findUserById(Long userId);
    UserResponseDTO findUserByEmail(String email);
    UserResponseDTO followUser(Long userId, Long followUserId);
    UserResponseDTO updateUser(Long userId, UserRequestDTO user);
    List<UserResponseDTO> searchUser(String query);
}
