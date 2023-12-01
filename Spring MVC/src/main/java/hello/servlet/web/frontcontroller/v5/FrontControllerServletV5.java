package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 매핑 정보
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 생성자
    public FrontControllerServletV5() {
        initHandlerMappingMap(); // 핸들러 매핑 초기화(등록)
        initHandlerAdapters(); // 어댑터 초기화(등록)
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 핸들러 조회
        Object handler = getHandler(request);

        // 예외 처리
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        // 2. 핸들러를 처리할 수 있는 핸들러 어댑터 조회
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 3. 어댑터 호출
        ModelView mv = adapter.handle(request, response, handler);

        // 4. viewResolver 호출
        String viewName = mv.getViewName();// 논리 이름

        // 5. MyView 반환
        MyView view = viewResolver(viewName);

        // 6. render(model) 호출
        view.render(mv.getModel(), request, response);
    }

    // 핸들러 매핑
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            // Case1) 핸들러가 어댑터를 지원하는 경우
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        // Case2) 핸들러가 어댑터를 지원하지 않는 경우
        throw new IllegalCallerException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    // view의 논리 이름 -> 물리 이름
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
