package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { // Autowired를 통해 스프링 빈에 있는 memberservice를 주입받는다. (DI - 생성자 주입)
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 보통 조회할 때는 Get
    public String createForm(){
        return "members/createMemberForm"; //createMemberForm 템플릿 html을 화면으로 반환함.
    }

    @PostMapping("/members/new") // 데이터를 폼에 넣어 등록해야 할 때 Post
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈화면으로 이동
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
