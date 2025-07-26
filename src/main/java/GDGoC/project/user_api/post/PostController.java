package GDGoC.project.user_api.post;

import GDGoC.project.user_api.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import GDGoC.project.user_api.post.PostService;
import GDGoC.project.user_api.post.PostForm;

import org.springframework.web.bind.annotation.GetMapping; //@GetMapping
import org.springframework.web.bind.annotation.PostMapping; //PostMapping 사용
import org.springframework.validation.BindingResult; //BindingResult 사용
import jakarta.validation.Valid; //Valid 사용
import java.security.Principal; //Principal 사용
import GDGoC.project.user_api.member.Member;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService  postService;
    private final MemberService memberService;

    @GetMapping("/write") //글쓰기 작성 저장
    public String writeForm(PostForm postForm) {
        return "post_form";  //작성 폼을 보여주는 타임리프 템플릿
    }

    @PostMapping("/create")
    public String create(@Valid PostForm postForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "post_form";
        }

        Member member = memberService.getMember(principal.getName());
        //Member class error: 클래스 존재 여부 or import 존재 여부
        //memberservice: 클래스에서 메서드가 존재 여부 or import 존재 여부

        postService.create(postForm.getContent(), member);
            return "redirect:/"; // 작성 후 메인 페이지로 리디렉트
        }

    }



