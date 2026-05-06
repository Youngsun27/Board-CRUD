package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.model.BoardVO;
import com.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        model.addAttribute("view", "list");
        return "index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam int seq, Model model) {
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        model.addAttribute("board", boardService.getBoard(vo));
        model.addAttribute("view", "detail");
        return "index";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writePage(Model model) {
        model.addAttribute("view", "write");
        return "index";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(BoardVO vo) {
        boardService.insertBoard(vo);
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(BoardVO vo) {
        boardService.updateBoard(vo);
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int seq) {
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        boardService.deleteBoard(vo);
        return "redirect:/board/list";
    }
}
