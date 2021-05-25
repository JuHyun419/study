package com.juhyun.book.springboot.web;

import com.juhyun.book.springboot.config.auth.LoginUser;
import com.juhyun.book.springboot.config.auth.dto.SessionUser;
import com.juhyun.book.springboot.domain.user.User;
import com.juhyun.book.springboot.service.posts.PostsService;
import com.juhyun.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    // 머스테치는 컨트롤러에서 문자열을 반환할 때 앞의경로와 뒤의 파일 확장자는 자동 지정됨
    // 앞의 경로 : src/main/resources/templates
    // 뒤의 확장자 : .mustache
    // 따라서 아래 메소드는 src/main/resource/templates/index.mustache 로 전환 View Resolver가 처리함
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
