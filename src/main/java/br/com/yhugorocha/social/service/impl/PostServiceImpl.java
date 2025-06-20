package br.com.yhugorocha.social.service.impl;

import br.com.yhugorocha.social.dto.PostRequestDTO;
import br.com.yhugorocha.social.dto.PostResponseDTO;
import br.com.yhugorocha.social.entities.Post;
import br.com.yhugorocha.social.exception.BusinessException;
import br.com.yhugorocha.social.exception.PostNotFoundException;
import br.com.yhugorocha.social.mapper.PostMapper;
import br.com.yhugorocha.social.repository.PostRepository;
import br.com.yhugorocha.social.repository.UserRepository;
import br.com.yhugorocha.social.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public PostResponseDTO createNewPost(PostRequestDTO postRequestDTO, Long userId) {
        var user = userService.findByIdInternal(userId);
        var post = postMapper.postResponseDTOToPost(postRequestDTO);
        post.setUser(user);
        postRepository.save(post);
        return postMapper.postResponseDTO(post);
    }

    @Transactional
    @Override
    public void deletePost(Long postId, Long userId) {
        var post = this.findByIdInternal(postId);

        if (!post.getUser().getId().equals(userId)) {
            throw new BusinessException("User does not have permission to delete this post");
        }

        postRepository.delete(post);
    }

    @Override
    public List<PostResponseDTO> getAllPostsByUserId(Long userId) {
        return postRepository.findPostByUserId(userId).stream()
                .map(postMapper::postResponseDTO)
                .toList();
    }

    @Override
    public PostResponseDTO findPostById(Long postId) {
        return postMapper.postResponseDTO(this.findByIdInternal(postId));
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::postResponseDTO)
                .sorted((post1, post2) ->
                        post2.getCreatedAt().compareTo(post1.getCreatedAt()))
                .toList();
    }

    @Transactional
    @Override
    public PostResponseDTO savePost(Long postId, Long userId) {
        var post = this.findByIdInternal(postId);
        var user = userService.findByIdInternal(userId);

    if (user.getSavedPosts().contains(post)) {
            user.getSavedPosts().remove(post);
        } else {
            user.getSavedPosts().add(post);
        }
        userRepository.save(user);
        return postMapper.postResponseDTO(post);
    }

    @Transactional
    @Override
    public PostResponseDTO likePost(Long postId, Long userId) {
        var post = this.findByIdInternal(postId);
        var user = userService.findByIdInternal(userId);
        if (post.getLikes().contains(user)) {
            post.getLikes().remove(user);
        } else {
            post.getLikes().add(user);
        }
        postRepository.save(post);
        return postMapper.postResponseDTO(post);
    }

    public Post findByIdInternal(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
    }
}
