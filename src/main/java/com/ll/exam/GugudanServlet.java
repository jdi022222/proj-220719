package com.ll.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Rq rq = new Rq(req, resp);

        int dan = rq.getIntParam("dan", 0);
        int limit = rq.getIntParam("limit", 0);

        //request에 정보를 담는다
        //나중에 gugudan2.jsp에서 관련 내용을 재사용 할 수 있음
        req.setAttribute("dan",dan);
        req.setAttribute("limit",limit);

        //gugudan2.jsp 에게 나머지 작업을 토스
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/gugudan2.jsp");
        requestDispatcher.forward(req,resp);

        rq.appendBody("<h1>%d단</h1>\n".formatted(dan));

        for (int i = 1; i <= limit; i++) {
            rq.appendBody("<div>%d * %d = %d</div>\n".formatted(dan, i, dan * i));
        }
    }
}