package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //@Controller가 있으면 MemberController 객체를 생성해서 스프링 컨테이너에 먼저 넣어둔다(스프링 빈으로 자동 등록된다)
public class MemberController {
    //private final MemberService memberService = new MemberService();
    //객체를 생성하면 여러개가 생성되는데 MemberService는 여러 개를 생성할 필요가 없다. (아래처럼 작성)
    private final MemberService memberService;

    @Autowired //스프링 컨테이너에 저장된 MemberService 객체와 연결 (생성자를 통한 의존 관계 주입)
    public MemberController(MemberService memberService) { //MemberService가 빈이 아니면 오류 뜸
        this.memberService = memberService;
    }

    //@Autowired private MemberService memberService; //필드를 통한 의존 관계 주입 (추천X)

    /* setter를 통한 의존 관계 주입 (MemberService를 바꿀 일이 없는데 의존 관계 주입을 위해 public하게 노출해야 함)
   @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //post!!!! 인 것 명심
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        
        return "redirect:/"; //홈 화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
