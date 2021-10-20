package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerV3.service");

        String reqeustURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(reqeustURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        /*각 Controller는 비즈니스 로직 실행 후 forward 할 view 이름과 Model 객체(member)를 반환 */
        ModelView mv =  controller.process(paramMap);

        String viewName = mv.getViewName();
        // view의 전체 경로 전달
        MyView myView = viewResolver(viewName);

        // model 객체와 서블릿관련 객체 전달
        myView.render(mv.getModel(), request, response);
    }

    // 뷰의 논리적 이름(jsp 파일 명)을 물리적 이름(전체 view 경로)으로 변환
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        // 각 Controller에 넘길 객체
        Map<String, String> paramMap = new HashMap<>();

        // 각 Controller에 paramMap 전달 >> request의 모든 파라미터를 꺼내 Map<String, String>로 저장
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
