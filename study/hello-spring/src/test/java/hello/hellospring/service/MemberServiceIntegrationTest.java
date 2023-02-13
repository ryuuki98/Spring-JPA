package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository MemberRepository;


    @Test
    void 회원가입() {
        /*
        Member member1 = new Member();
        member1.setName("홍길동");
        member1.setId(0L);
        */
        long start = System.currentTimeMillis();
        
        try {

            //given - 인풋데이터
            Member member = new Member();
            member.setName("spring");

            //when - 실행문
            long saveId = memberService.join((member));

            //then - 검증부분
            Member findmember = memberService.findOne(saveId).get();

            assertThat(member.getId()).isEqualTo(findmember.getId());


        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start ;

            System.out.println("회원가입에 걸린 시간은 " + timeMs + "Ms 입니다");

        }






    }
    @Test
    public void 중복_회원_예외(){

        Long start = System.currentTimeMillis();

        try {

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




        }finally {

            Long finish = System.currentTimeMillis();

            long timeMs = finish - start;

            System.out.println("중복회원 예약은 " + timeMs + "Ms 입니다.");
        }


    }

}