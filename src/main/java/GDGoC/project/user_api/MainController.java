package GDGoC.project.user_api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        /* localhost:8080로 접속하면 localhost:8080/signin로 주소가 바뀌면서 질문 목록이 있는 웹 페이지로 연결된다. */
        return "redirect:/signin";
    }
}
