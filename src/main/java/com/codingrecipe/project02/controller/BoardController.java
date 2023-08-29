package com.codingrecipe.project02.controller;

import com.codingrecipe.project02.dto.BoardDTO;
import com.codingrecipe.project02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    //ModelAttribute -> 필드의 name값이 DTO에 setter을 써서 담김
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        int saveResult = boardService.save(boardDTO);

        if(saveResult > 0){
            return "redirect:/board/";
        }else{
            return "save";
        }
    }
}
