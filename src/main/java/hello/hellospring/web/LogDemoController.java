package hello.hellospring.web;

import hello.hellospring.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // 자동 의존관계 주입 (자동으로 생성자를 만들어 의존관계를 주입해준다.)
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // ObjectProvider 사용

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) { // 1. 웹사이트 진입
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL); // 4. URL 세팅

        myLogger.log("controller test"); // 5. MyLogger 객체의 log 메서드 호출
        logDemoService.logic("testId"); // 6. 서비스의 logic 메서드 호출
        return "OK";
    }
}