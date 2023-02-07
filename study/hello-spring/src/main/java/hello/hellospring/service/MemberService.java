package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**회원가입 (중복시 가입 불가)
     *
     */

    public long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }



    private void validateDuplicateMember(Member member) {

        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

    }

    /**전체 조회
     *
     * @return
     */


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**id를 이용하여 1명 조회
     *
     *
     *
     * */

    public Optional<Member>findOne(long memberId){
        return memberRepository.findById(memberId);

    }


}
