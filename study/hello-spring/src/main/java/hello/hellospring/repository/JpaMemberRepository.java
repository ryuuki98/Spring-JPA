package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //em.persist(파라미터) em을 통해 db에 저장
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        //em.find(클래스,식별자)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        // em.createQuery("쿼리문")하면 객체를 대상으로 쿼리문을 날릴수 있음 . .getResultList()를 사용하여 리스트형태로 반환
        return em.createQuery("select m from Member m").getResultList();

    }
}
