package com.service;

import java.util.List;
import com.model.BoardVO;

// 인터페이스: 무엇을 할 수 있는지 선언
public interface BoardService {
    void insertBoard(BoardVO vo);
    void updateBoard(BoardVO vo);
    void deleteBoard(BoardVO vo);
    BoardVO getBoard(BoardVO vo);
    List<BoardVO> getBoardList();
}
