package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface가 interface를 implements 할때는 extends 사용
//아래처럼 인터페이스만 만들면 springdatajpa가 구현체를 자동으로 등록해줌. springconfig에서 가져다 쓰면 됨
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
