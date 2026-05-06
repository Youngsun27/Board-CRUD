package com.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.model.UserVO;
import com.model.UserDAO;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;   // UserService媛� �뾾�쑝誘�濡� UserDAO 吏곸젒 二쇱엯

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session) {
        UserVO user = userDAO.getUser(vo);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/board/list";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
