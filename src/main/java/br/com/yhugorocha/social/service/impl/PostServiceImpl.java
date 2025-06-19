package br.com.yhugorocha.social.service.impl;

import br.com.yhugorocha.social.dto.PostRequestDTO;
import br.com.yhugorocha.social.dto.PostResponseDTO;
import br.com.yhugorocha.social.entities.Post;
import br.com.yhugorocha.social.exception.BusinessException;
import br.com.yhugorocha.social.exception.PostNotFoundException;
import br.com.yhugorocha.social.mapper.PostMapper;
import br.com.yhugorocha.social.repository.PostRepository;
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

        return List.of();
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
                .toList();
    }

    @Override
    public PostResponseDTO savePost(Long postId, Long userId) {
        return null;
    }

    @Override
    public PostResponseDTO likePost(Long postId, Long userId) {
        return null;
    }

    public Post findByIdInternal(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
    }
}
