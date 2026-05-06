package com.model;

import java.sql.Date;

// VO (Value Object): DB 테이블의 한 행(row)을 Java 객체로 표현
// board 테이블 컬럼 → Java 필드 1:1 매핑
public class BoardVO {

    private int    seq;
    private String title;
    private String writer;
    private String content;
    private Date   regDate;
    private int    cnt;

    public int    getSeq()               { return seq; }
    public void   setSeq(int seq)        { this.seq = seq; }

    public String getTitle()             { return title; }
    public void   setTitle(String title) { this.title = title; }

    public String getWriter()                { return writer; }
    public void   setWriter(String writer)   { this.writer = writer; }

    public String getContent()               { return content; }
    public void   setContent(String content) { this.content = content; }

    public Date   getRegDate()               { return regDate; }
    public void   setRegDate(Date regDate)   { this.regDate = regDate; }

    public int    getCnt()               { return cnt; }
    public void   setCnt(int cnt)        { this.cnt = cnt; }

    @Override
    public String toString() {
        return "BoardVO [seq=" + seq + ", title=" + title
             + ", writer=" + writer + ", content=" + content
             + ", regDate=" + regDate + ", cnt=" + cnt + "]";
    }
}
