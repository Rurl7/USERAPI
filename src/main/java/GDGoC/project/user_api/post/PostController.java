package GDGoC.project.user_api.post;

import GDGoC.project.user_api.user.User;
import GDGoC.project.user_api.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@Tag(name = "Post-Controller", description = "Post CRUD API 엔드포인트")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    /** 게시글 목록 */
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @GetMapping
    public List<PostDto> getList() {
        return postService.getList().stream()
                .map(PostDto::from)
                .toList();
    }

    /** 게시글 조회 */
    @Operation(summary = "게시글 상세 조회", description = "게시글을 상세 조회합니다.", parameters = {
            @Parameter(name = "postId", description = "게시글 ID", required = true)
    })
    @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @GetMapping("/{id}")
    public PostDto getDetail(@PathVariable Integer id) {
        return PostDto.from(postService.getPost(id));
    }

    /** 게시글 생성 */
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostCreateRequest req,
                                          Principal principal) {

        User user = userService.getUser(principal.getName());
        Post post = postService.createPost(req.content(), user);

        return ResponseEntity
                .created(URI.create("/api/posts/" + post.getId())) // 201 + Location
                .body(PostDto.from(post));
    }

    /** 게시글 수정 */
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.", parameters = {
        @Parameter(name = "postId", description = "게시글 ID", required = true)
    })
    @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public PostDto modify(@PathVariable Integer id,
                          @Valid @RequestBody PostUpdateRequest req,
                          Principal principal) {

        Post post = postService.getPost(id);
        authorize(principal, post);                 // 수정 권한 체크
        postService.modifyPost(post, req.content());
        return PostDto.from(post);
    }

    /** 게시글 삭제 */
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.", parameters = {
            @Parameter(name = "postId", description = "게시글 ID", required = true)
    })
    @ApiResponse(responseCode = "204", description = "삭제 성공", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id,
                                       Principal principal) {
        Post post = postService.getPost(id);
        authorize(principal, post);
        postService.deletePost(post);
        return ResponseEntity.noContent().build();  // 204
    }

    /** 좋아요 */
    @Operation(summary = "좋아요 추가", description = "좋아요를 추가합니다.", parameters = {
            @Parameter(name = "postId", description = "게시글 ID", required = true)
    })
    @ApiResponse(responseCode = "200", description = "좋아요 추가", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/like")
    public PostDto like(@PathVariable Integer id, Principal principal) {
        Post post = postService.getPost(id);
        User user = userService.getUser(principal.getName());
        postService.toggleLike(post, user);
        return PostDto.from(post);
    }

    /** 좋아요 취소 */
    @Operation(summary = "좋아요 취소", description = "좋아요를 취소합니다.", parameters = {
            @Parameter(name = "postId", description = "게시글 ID", required = true)
    })
    @ApiResponse(responseCode = "200", description = "좋아요 취소", content = @Content(schema = @Schema(implementation = PostDto.class)))
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}/like")
    public PostDto cancelLike(@PathVariable Integer id, Principal principal) {
        Post post = postService.getPost(id);
        User user = userService.getUser(principal.getName());
        postService.toggleLike(post, user);
        return PostDto.from(post);
    }

    /** 내부 권한 체크 메서드 */
    private void authorize(Principal principal, Post post) {
        if (!post.getAuthor().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }
    }
}
