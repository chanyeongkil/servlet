package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * V3에서의 Controller 인터페이스
     * ModelView process(Map<String, String> paramMap);
     */

    /**
     * @param paramMap
     * @param model
     * @return viewName
     * */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
