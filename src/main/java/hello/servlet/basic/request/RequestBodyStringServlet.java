package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 메시지 Body의 내용을 바이트코드로 바로 얻을 수 있다.
        ServletInputStream servletInputStream =  request.getInputStream();

        //Spring에서 제공하는 유틸리티 사용, String으로 변환
        // 바이트코드를 문자로 변환할 떄는 어떤 인코딩인지 명시해야 한다.
        String messageBody = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);

        System.out.println("RequestBodyStringServlet.service");
        System.out.println("messageBody = " + messageBody);
        // 응답 "ok"
        response.getWriter().write("ok");
    }
}
