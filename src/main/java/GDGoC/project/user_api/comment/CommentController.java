package GDGoC.project.user_api.comment;

import GDGoC.project.user_api.user.User;
import GDGoC.project.user_api.user.UserService;
import GDGoC.project.user_api.post.Post;
import GDGoC.project.user_api.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController // REST API 컨트롤러로 등록
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    /* 특정 게시글의 댓글 목록 조회 */
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> list(@PathVariable Integer postId,
                                 @AuthenticationPrincipal(expression = "user") User me) {

        return commentService.getComments(postId)      // List<Comment>
                .stream()
                .map(c -> CommentDto.from(c, me))
                .toList();
    }

    @GetMapping("/comments/{id}")
    public CommentDto detail(@PathVariable Integer id,
                             @AuthenticationPrincipal(expression = "user") User me) {
        return CommentDto.from(commentService.getComment(id), me);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Integer postId,
                                             @Valid @RequestBody CommentCreateRequest req,
                                             Principal principal) {

        Post post = postService.getPost(postId);
        User author = userService.getUser(principal.getName());
        Comment comment = commentService.createComment(post, req.content(), author);

        return ResponseEntity
                .created(URI.create("/api/comments/" + comment.getId()))
                .body(CommentDto.from(comment, author));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/comments/{id}")
    public CommentDto modify(@PathVariable Integer id,
                             @Valid @RequestBody CommentUpdateRequest req,
                             Principal principal) {

        Comment comment = commentService.getComment(id);
        commentService.assertAuthor(comment, principal.getName());
        commentService.modifyComment(comment, req.content());
        return CommentDto.from(comment, comment.getAuthor());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id,
                                       Principal principal) {
        Comment comment = commentService.getComment(id);
        commentService.assertAuthor(comment, principal.getName());
        commentService.deleteComment(comment);
        return ResponseEntity.noContent().build();
    }

    /* ---------------- 좋아요 / 취소 ---------------- */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comments/{id}/like")
    public CommentDto like(@PathVariable Integer id, Principal principal) {
        Comment comment = commentService.getComment(id);
        User user = userService.getUser(principal.getName());
        commentService.addLike(comment, user);
        return CommentDto.from(comment, user);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/comments/{id}/like")
    public CommentDto cancelLike(@PathVariable Integer id, Principal principal) {
        Comment comment = commentService.getComment(id);
        User user = userService.getUser(principal.getName());
        commentService.removeLike(comment, user);
        return CommentDto.from(comment, user);
    }
}
