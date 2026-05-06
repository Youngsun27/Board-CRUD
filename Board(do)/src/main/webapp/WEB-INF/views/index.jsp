<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.BoardVO, java.util.List" %>
<%
    String view = (String) session.getAttribute("view");
    if (view == null) view = "login";
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>게시판</title></head>
<body>
<div align="center">

<%--접속 URL : http://localhost:8080/biz/login.do --%>

<%-- ① 로그인 --%>
<% if (view.equals("login")) { %>
    <h1>로그인</h1>
    <form action="login.do" method="post">
        <input type="text"     name="id"       placeholder="아이디"><br>
        <input type="password" name="password" placeholder="비밀번호"><br>
        <input type="submit" value="로그인">
    </form>

<%-- ② 글 목록 --%>
<% } else if (view.equals("list")) { %>
    <h1>글 목록</h1>
    <a href="logout.do">로그아웃</a> | <a href="view.do?view=write">글쓰기</a>
    <table border="1">
        <tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th></tr>
        <% for (BoardVO b : (List<BoardVO>) session.getAttribute("boardList")) { %>
        <tr>
            <td><%= b.getSeq() %></td>
            <td><a href="getBoard.do?seq=<%= b.getSeq() %>"><%= b.getTitle() %></a></td>
            <td><%= b.getWriter() %></td>
            <td><%= b.getRegDate() %></td>
        </tr>
        <% } %>
    </table>

<%-- ③ 글 상세 + 수정 폼 --%>
<% } else if (view.equals("detail")) {
       BoardVO b = (BoardVO) session.getAttribute("board"); %>
    <h1>글 상세보기</h1>
    <form action="updateBoard.do" method="post">
        <input type="hidden" name="seq"   value="<%= b.getSeq() %>">
        제목:  <input type="text" name="title" value="<%= b.getTitle() %>"><br>
        작성자: <%= b.getWriter() %><br>
        내용:  <textarea name="content"><%= b.getContent() %></textarea><br>
        <input type="submit" value="수정">
    </form>
    <a href="deleteBoard.do?seq=<%= b.getSeq() %>">삭제</a> |
    <a href="getBoardList.do">목록</a>

<%-- ④ 글 쓰기 폼 --%>
<% } else if (view.equals("write")) { %>
    <h1>글 등록</h1>
    <form action="insertBoard.do" method="post">
        제목:   <input type="text" name="title"><br>
        작성자: <input type="text" name="writer"><br>
        내용:   <textarea name="content"></textarea><br>
        <input type="submit" value="등록">
    </form>
    <a href="getBoardList.do">목록</a>
<% } %>

</div>
</body>
</html>