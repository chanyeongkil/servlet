package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    /** HTTP요청을 통해 매핑 된 URL이 호출 되면 실행되는 메소드 **/
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");

        /** Client가 요청한 HTTP에 대하여 WAS에서 Servlet 표준을 구현한 구현체의 객체가 출력 **/
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        /** http://localhost:8080/hello?username=kim요청에 대한 URL의 쿼리파라미터 값 출력 **/
        String QueryParam = request.getParameter("username");
        System.out.println("QueryParam = " + QueryParam);

        /** Client에 응답 메시지 전달 **/
        response.setContentType("text/plan"); //HTTP 헤더의 content-Type 지정
        response.setCharacterEncoding("utf-8"); //HTTP 헤더의 content-Type 지정
        response.getWriter().write("hello  "+QueryParam); //HTTP 메시지 Body에 메시지가 작성 된다.

    }
}
