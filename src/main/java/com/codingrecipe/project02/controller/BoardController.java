package com.codingrecipe.project02.controller;

import com.codingrecipe.project02.dto.BoardDTO;
import com.codingrecipe.project02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "list";
    }

    // board?id=10 이런 형식이기 때문에 겟 매핑에 뭐가 따로 붙지 않음
    // 그대신 RequestParam id로 id 값을 받음
    // model을 쓰는 이유는 detail.jsp로 보드에 대한 정보를 보내기 위해서
    // model에 db에서 가져온 BoardDTO객체로 받아옴 addAttribute로 모델에 담아줌
    @GetMapping
    public String findById(@RequestParam("id") Long id,Model model){
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "detail";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        boardService.delete(id);

        return "redirect:/board/";
    }

}
