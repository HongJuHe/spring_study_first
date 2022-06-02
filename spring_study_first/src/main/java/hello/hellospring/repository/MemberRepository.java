package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 객체 저장 (domain의 Member)
    Optional<Member> findById(Long id); //Null 반환 값을 처리하기 위해 Optional 사용
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
