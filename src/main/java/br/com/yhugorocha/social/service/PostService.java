package br.com.yhugorocha.social.service;

import br.com.yhugorocha.social.dto.PostRequestDTO;
import br.com.yhugorocha.social.dto.PostResponseDTO;

import java.util.List;

public interface PostService {

    PostResponseDTO createNewPost(PostRequestDTO postRequestDTO, Long userId);
    void deletePost(Long postId, Long userId);
    List<PostResponseDTO> getAllPostsByUserId(Long userId);
    PostResponseDTO findPostById(Long postId);
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO savePost(Long postId, Long userId);
    PostResponseDTO likePost(Long postId, Long userId);
}
