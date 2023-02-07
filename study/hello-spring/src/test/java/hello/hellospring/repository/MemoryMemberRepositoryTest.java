package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }



    @Test
    public void save(){
        Member member = new Member();
        member.setName("홍길동");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member,result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        //System.out.println("result = " + (member == result))
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("홍길동");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("이순신");
        repository.save(member2);

        Member result = repository.findByName("홍길동").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);


    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("sp1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sp2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("sp3");
        repository.save(member3);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(3);



    }
}
