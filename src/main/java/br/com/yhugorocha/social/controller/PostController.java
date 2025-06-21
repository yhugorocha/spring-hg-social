package br.com.yhugorocha.social.controller;

import br.com.yhugorocha.social.dto.PostRequestDTO;
import br.com.yhugorocha.social.dto.PostResponseDTO;
import br.com.yhugorocha.social.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO,@PathVariable Long userId) {
        PostResponseDTO createdPost = postService.createNewPost(requestDTO, userId);
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @PathVariable Long userId) {
        postService.deletePost(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDTO>> getAllPostsByUserId(@PathVariable Long userId) {
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

    @PutMapping("/{postId}/save/user/{userId}")
    public ResponseEntity<PostResponseDTO> savePost(@PathVariable Long postId, @PathVariable Long userId) {
        PostResponseDTO savedPost = postService.savePost(postId, userId);
        return ResponseEntity.ok(savedPost);
    }

    @PutMapping("/{postId}/like/user/{userId}")
    public ResponseEntity<PostResponseDTO> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        PostResponseDTO likedPost = postService.likePost(postId, userId);
        return ResponseEntity.ok(likedPost);
    }

}
