package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // 이제 JPA가 관리하는 엔티티가 됨.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 자동으로 생성하는 데이터임을 알림 (이름을 받으면 숫자가 부여됨)
    private Long id;
    private String name;

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
