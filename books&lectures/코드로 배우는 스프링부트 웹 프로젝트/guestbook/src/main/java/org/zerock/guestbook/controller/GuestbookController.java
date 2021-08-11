package org.zerock.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("result", guestbookService.getList(pageRequestDto));
    }

    @GetMapping("/register")
    public String register() {
        log.info("Get register ....");
        return "/guestbook/register";
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDto dto, RedirectAttributes redirectAttributes) {
        // 새로 추가된 엔티티 번호
        Long gno = guestbookService.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long gno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("read gno: " + gno);
        GuestbookDto dto = guestbookService.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        guestbookService.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDto dto,
                         @ModelAttribute("requestDto") PageRequestDto requestDto,
                         RedirectAttributes redirectAttributes) {
        log.info("modify ................. ");
        log.info("dto: " + dto);
        guestbookService.modify(dto);
        redirectAttributes.addAttribute("page", requestDto.getPage());
        redirectAttributes.addAttribute("type", requestDto.getType());
        redirectAttributes.addAttribute("keyword", requestDto.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }
}

