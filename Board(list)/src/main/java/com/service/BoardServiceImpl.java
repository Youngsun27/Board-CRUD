package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.BoardDAO;
import com.model.BoardVO;

// 구현체: 실제 동작
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    public void insertBoard(BoardVO vo)      { boardDAO.insertBoard(vo); }
    public void updateBoard(BoardVO vo)      { boardDAO.updateBoard(vo); }
    public void deleteBoard(BoardVO vo)      { boardDAO.deleteBoard(vo); }
    public BoardVO getBoard(BoardVO vo)      { return boardDAO.getBoard(vo); }
    public List<BoardVO> getBoardList()      { return boardDAO.getBoardList(); }
}
