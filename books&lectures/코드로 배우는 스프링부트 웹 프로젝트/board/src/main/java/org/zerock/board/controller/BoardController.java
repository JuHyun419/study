package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDto;
import org.zerock.board.dto.PageRequestDto;
import org.zerock.board.service.BoardService;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board/")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model) {
        log.info("list...... " + pageRequestDto);
        model.addAttribute("result", boardService.getList(pageRequestDto));
    }

    @GetMapping("/register")
    public void register() {
        log.info("Get /register");
    }

    @PostMapping("/register")
    public String registerPost(BoardDto dto, RedirectAttributes redirectAttributes) {
        Long bno = boardService.register(dto);
        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Long bno, Model model) {
        BoardDto boardDto = boardService.get(bno);
        model.addAttribute("dto", boardDto);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        boardService.deleteWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto, RedirectAttributes redirectAttributes) {
        boardService.update(dto);

        redirectAttributes.addAttribute("page", requestDto.getPage());
        redirectAttributes.addAttribute("type", requestDto.getType());
        redirectAttributes.addAttribute("keyword", requestDto.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }

}
