package com.codingrecipe.project02.service;

import com.codingrecipe.project02.dto.BoardDTO;
import com.codingrecipe.project02.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public int save(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO);
    }
}
