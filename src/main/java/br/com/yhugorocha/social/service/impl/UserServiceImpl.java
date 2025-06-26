package br.com.yhugorocha.social.service.impl;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;
import br.com.yhugorocha.social.entities.User;
import br.com.yhugorocha.social.exception.BusinessException;
import br.com.yhugorocha.social.exception.UserNotFoundException;
import br.com.yhugorocha.social.mapper.UserMapper;
import br.com.yhugorocha.social.repository.UserRepository;
import br.com.yhugorocha.social.service.UserService;
import br.com.yhugorocha.social.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponseDTO register(UserRequestDTO requestDTO) {
        var user = userMapper.userResponseDTOToUser(requestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.userResponseDTO(user);
    }

    @Override
    public UserResponseDTO findUserById(Long userId) {
        var user = this.findByIdInternal(userId);
        return userMapper.userResponseDTO(user);
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userResponseDTO)
                .orElseThrow(() -> new BusinessException("User not found with email: " + email));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserResponseDTO followUser(Long userId, Long followUserId) {
        User user = this.findByIdInternal(userId);
        User followUser = this.findByIdInternal(followUserId);

        if (user.getFollowing().contains(followUser)) {
            throw new BusinessException("You are already following this user.");
        }

        user.getFollowing().add(followUser);
        followUser.getFollowers().add(user);

        userRepository.save(user);
        userRepository.save(followUser);

        return userMapper.userResponseDTO(user);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO requestDTO) {
        User existingUser = this.findByIdInternal(userId);

        userMapper.updateUserFromUserRequestDTO(requestDTO, existingUser);
        userRepository.save(existingUser);
        return userMapper.userResponseDTO(existingUser);
    }

    public User findByIdInternal(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public List<UserResponseDTO> searchUser(String query) {
        return userRepository.searchUser(query)
                .stream()
                .map(userMapper::userResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO findUserByToken(String token) {
        var user = userRepository.findByUsername(jwtUtil.extractUsername(jwtUtil.validateToken(token)))
                .orElseThrow(() -> new UserNotFoundException("User not found with token: " + token));
        return userMapper.userResponseDTO(user);
    }
}
