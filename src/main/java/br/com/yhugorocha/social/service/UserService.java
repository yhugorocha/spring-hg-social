package br.com.yhugorocha.social.service;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;
import br.com.yhugorocha.social.entities.User;

import java.util.List;

public interface UserService {

    public UserResponseDTO register(UserRequestDTO user);
    public UserResponseDTO findUserById(Long userId);
    public UserResponseDTO findUserByEmail(String email);
    public UserResponseDTO followUser(Long userId, Long followUserId);
    public UserResponseDTO updateUser(Long userId, User user);
    public List<UserResponseDTO> searchUser(String query);
}
