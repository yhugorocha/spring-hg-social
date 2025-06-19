package br.com.yhugorocha.social.service.impl;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;
import br.com.yhugorocha.social.entities.User;
import br.com.yhugorocha.social.mapper.UserMapper;
import br.com.yhugorocha.social.repository.UserRepository;
import br.com.yhugorocha.social.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponseDTO register(UserRequestDTO requestDTO) {
        var user = userMapper.userResponseDTOToUser(requestDTO);
        userRepository.save(user);
        return userMapper.userResponseDTO(user);
    }

    @Override
    public UserResponseDTO findUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::userResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserResponseDTO followUser(Long userId, Long followUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        User followUser = userRepository.findById(followUserId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + followUserId));

        if (user.getFollowing().contains(followUserId)) {
            throw new RuntimeException("You are already following this user.");
        }

        user.getFollowing().add(followUser.getId());
        followUser.getFollowers().add(user.getId());

        userRepository.save(user);
        userRepository.save(followUser);

        return userMapper.userResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, User user) {
        return null;
    }

    @Override
    public List<UserResponseDTO> searchUser(String query) {
        return List.of();
    }
}
