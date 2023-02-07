package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);

    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        /*
        Member member1 = new Member();
        member1.setName("홍길동");
        member1.setId(0L);
        */

        //given - 인풋데이터
        Member member = new Member();
        member.setName("No");

        //when - 실행문
        long saveId = memberService.join((member));

        //then - 검증부분
        Member findmember = memberService.findOne(saveId).get();

        assertThat(member.getId()).isEqualTo(findmember.getId());






    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 멤버2를 가입 시켰을 때, IllegalStateException이 발생해야함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /*try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        }*/

        //then




    }

    @Test
    void 멤버조회() {
    }

    @Test
    void 한명조회() {
    }
}