package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.dto.PostRequestDTO;
import br.com.yhugorocha.social.dto.PostResponseDTO;
import br.com.yhugorocha.social.service.PostService;
import br.com.yhugorocha.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO, @CookieValue("backendHgSocial") String token) {
        var userId = userService.findUserByToken(token).getId();
        PostResponseDTO createdPost = postService.createNewPost(requestDTO, userId);
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @CookieValue("backendHgSocial") String token) {
        var userId = userService.findUserByToken(token).getId();
        postService.deletePost(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPostsByUser(@CookieValue("backendHgSocial") String token) {
        var userId = userService.findUserByToken(token).getId();
        return ResponseEntity.ok(postService.getAllPostsByUserId(userId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long postId) {
        PostResponseDTO post = postService.findPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{postId}/save")
    public ResponseEntity<PostResponseDTO> savePost(@PathVariable Long postId, @CookieValue("backendHgSocial") String token) {
        var userId = userService.findUserByToken(token).getId();
        PostResponseDTO savedPost = postService.savePost(postId, userId);
        return ResponseEntity.ok(savedPost);
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<PostResponseDTO> likePost(@PathVariable Long postId, @CookieValue("backendHgSocial") String token) {
        var userId = userService.findUserByToken(token).getId();
        PostResponseDTO likedPost = postService.likePost(postId, userId);
        return ResponseEntity.ok(likedPost);
    }

}
