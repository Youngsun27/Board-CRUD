<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- JSTL: *.do 버전의 <% %> 스크립틀릿을 태그로 대체 --%>

<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>게시판</title></head>
<body>
<div align="center">


<%--접속 URL : http://localhost:8080/biz2/login --%>

<%-- ① 로그인 --%>
<c:if test="${view == 'login' or empty view}">
    <h1>로그인</h1>
    <form action="/login" method="post">
        <input type="text"     name="id"       placeholder="아이디"><br>
        <input type="password" name="password" placeholder="비밀번호"><br>
        <input type="submit" value="로그인">
    </form>
</c:if>

<%-- ② 글 목록 --%>
<c:if test="${view == 'list'}">
    <h1>글 목록</h1>
    <a href="/logout">로그아웃</a> | <a href="/board/write">글쓰기</a>
    <table border="1">
        <tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th></tr>
        <c:forEach var="b" items="${boardList}">
        <tr>
            <td>${b.seq}</td>
            <td><a href="/board/detail?seq=${b.seq}">${b.title}</a></td>
            <td>${b.writer}</td>
            <td>${b.regDate}</td>
        </tr>
        </c:forEach>
    </table>
</c:if>

<%-- ③ 글 상세 + 수정 폼 --%>
<c:if test="${view == 'detail'}">
    <h1>글 상세보기</h1>
    <form action="/board/update" method="post">
        <input type="hidden" name="seq"   value="${board.seq}">
        제목:   <input type="text" name="title" value="${board.title}"><br>
        작성자: ${board.writer}<br>
        내용:   <textarea name="content">${board.content}</textarea><br>
        <input type="submit" value="수정">
    </form>
    <a href="/board/delete?seq=${board.seq}">삭제</a> |
    <a href="/board/list">목록</a>
</c:if>

<%-- ④ 글 쓰기 폼 --%>
<c:if test="${view == 'write'}">
    <h1>글 등록</h1>
    <form action="/board/insert" method="post">
        제목:   <input type="text" name="title"><br>
        작성자: <input type="text" name="writer"><br>
        내용:   <textarea name="content"></textarea><br>
        <input type="submit" value="등록">
    </form>
    <a href="/board/list">목록</a>
</c:if>

</div>
</body>
</html>