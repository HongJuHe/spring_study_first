package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    /*clearstore를 위해 생성한 MemberRepository와 MemberService에 있는 MemberRepository가 다른 인스턴스이기 때문에
    다른 레포지토리가 테스트되고 있음. 물론 현재는 store 변수가 static이라서 같은 DB라 문제는 발생하지 않지만... 같은 인스턴스를 쓰도록 바꿔주는 것이 낫다
    이는 MemberService에서 MemberRepository 객체 생성 부분과 아래 코드 참고*/
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { //테스트 시작 전에 객체 만들기
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //MemberService 입장에서 memoryMemberRepository를 직접 new로 만들지 않고 외부에서 넣어줌
        //이것을 DI(Dependency Injection - 의존성 주입)이라고 한다
    }

    @AfterEach
    public void afterEach() {
        //각 테스트마다 DB 클리어
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() { //테스트에서는 예외가 중요하다!
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //memberService.join(member2); //여기서 예외가 발생. 예외가 발생했다는 것을 확인하기 위해 아래 코드 작성
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //원래 에러 메시지가 나오는지 확인
        }*/
        //memberService.join(member2) 로직을 실행할 때, IllegalStateException 예외가 발생해야 성공
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}