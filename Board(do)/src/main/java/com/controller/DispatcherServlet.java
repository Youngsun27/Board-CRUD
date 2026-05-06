package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.BoardDAO;
import com.model.BoardVO;
import com.model.UserDAO;
import com.model.UserVO;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException { process(req, res); }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        process(req, res);
    }

    private void process(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String path = req.getRequestURI();
        path = path.substring(path.lastIndexOf("/"));  // "/login.do"
        HttpSession session = req.getSession();

        // 기본 진입점
        if (path.equals("/login.do") && req.getMethod().equals("GET")) {
            session.setAttribute("view", "login");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);
            return;
        }
        
        if (path.equals("/login.do")) {
            UserVO vo = new UserVO();
            vo.setId(req.getParameter("id"));
            vo.setPassword(req.getParameter("password"));

            UserVO user = new UserDAO().getUser(vo);
            if (user != null) {
                session.setAttribute("loginUser", user);
                res.sendRedirect("getBoardList.do");
            } else {
                session.setAttribute("view", "login");
                req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);
            }

        } else if (path.equals("/getBoardList.do")) {
            List<BoardVO> list = new BoardDAO().getBoardList(new BoardVO());
            session.setAttribute("boardList", list);
            session.setAttribute("view", "list");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);

        } else if (path.equals("/getBoard.do")) {
            BoardVO vo = new BoardVO();
            vo.setSeq(Integer.parseInt(req.getParameter("seq")));
            session.setAttribute("board", new BoardDAO().getBoard(vo));
            session.setAttribute("view", "detail");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);

        } else if (path.equals("/insertBoard.do")) {
            BoardVO vo = new BoardVO();
            vo.setTitle(req.getParameter("title"));
            vo.setWriter(req.getParameter("writer"));
            vo.setContent(req.getParameter("content"));
            new BoardDAO().insertBoard(vo);
            res.sendRedirect("getBoardList.do");

        } else if (path.equals("/updateBoard.do")) {
            BoardVO vo = new BoardVO();
            vo.setSeq(Integer.parseInt(req.getParameter("seq")));
            vo.setTitle(req.getParameter("title"));
            vo.setContent(req.getParameter("content"));
            new BoardDAO().updateBoard(vo);
            res.sendRedirect("getBoardList.do");

        } else if (path.equals("/deleteBoard.do")) {
            BoardVO vo = new BoardVO();
            vo.setSeq(Integer.parseInt(req.getParameter("seq")));
            new BoardDAO().deleteBoard(vo);
            res.sendRedirect("getBoardList.do");

        } else if (path.equals("/logout.do")) {
            session.invalidate();
            session = req.getSession();
            session.setAttribute("view", "login");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);

        } else if (path.equals("/view.do")) {
            session.setAttribute("view", req.getParameter("view"));
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);
        }
    }
}
