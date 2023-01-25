package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        // key는 hello, value가 hello!이며, value가 hello.html의 data에 삽입되어 표현된다.
        return "hello"; // hello.html을 화면에 렌더링하라고 명령!
    }

    @GetMapping("hello-mvc") // 외부에서 parameter를 받기 위한 RequestParam
    // http://localhost:8080/hello-mvc?name=spring과 같은 형태가 된다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 의미: http 통신 프로토콜에서 body 부분에 이 데이터를 직접 넣어 주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // name에 spring을 넣으면 hello spring이 되어 클라이언트에게 넘어감. html이 아니라 순수 문자열이!
    }
    // 만약 데이터(객체)를 내놓으라고 하면 어떻게 하는가?

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 커맨드 쉬프트 엔터 자동완성
        hello.setName(name);
        return hello; // 문자가 아닌 객체를 반환한다면 기본적으로 JSON 형식으로 반환된다.
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    } // get/setter로 대표되는 property 접근 방식 채택

}
