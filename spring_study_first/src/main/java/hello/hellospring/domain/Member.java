package hello.hellospring.domain;

import javax.persistence.*;

@Entity //JPA가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //시스템이 저장하는 값 (identity 전략)

    private String name; //고객이 저장하는 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
