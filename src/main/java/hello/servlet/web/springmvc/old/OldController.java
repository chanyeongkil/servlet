package hello.servlet.web.springmvc.old;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 과거 버전 스프링 컨트롤러 (org.springframework.web.servlet.mvc.Controller)
 * http://localhost:8080/springmvc/old-controller 요청 시 호출 됨
 * URL과 동일한 SpringBean을 찾아 실행 함
 */

@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest~~~~~~~~~~~");
        return new ModelAndView("new-form"); // viewResolver를 만들어야 함
    }
}
