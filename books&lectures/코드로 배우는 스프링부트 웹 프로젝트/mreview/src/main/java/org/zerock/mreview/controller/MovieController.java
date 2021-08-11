package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.mreview.dto.MovieDto;
import org.zerock.mreview.service.MovieService;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
@Log4j2
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(MovieDto movieDto, RedirectAttributes redirectAttributes) {
        log.info("movieDto: " + movieDto);

        Long mno = movieService.register(movieDto);
        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";
    }

}
