package GDGoC.project.user_api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * JpaRepository는 JPA가 제공하는 인터페이스 중 하나로 CRUD 작업을 처리하는 메서드들을 이미 내장하고 있어 데이터 관리 작업을 좀 더 편리하게 처리할 수 있음
     * JpaRepository<User, Integer>는 Memeber 엔티티로 리포지터리를 생성하고 User 엔티티의 기본키가 Integer임을 지정
     */

    /* 리포지터리에서 제공되지 않는 메서드는 인터페이스 변경 필요
     * Q. findBySubject 라는 메서드를 선언만 하고 구현하지 않았는데 도대체 어떻게 실행되는 건가요?
     * A. JPA Repository의 메서드명을 분석하여 쿼리를 만들고 실행하는 기능이 있음!
     *    즉, 'findBy + 엔티티의 속성명' 과 같은 리포지터리의 메서드를 작성하면 입력한 속성의 값으로 데이터를 조회할 수 있음
     * */

    /* 다양한 조합: And, Or, Between, LessThan, GreaterThenEqual, Like, In, OrderBy 등
     *  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation 참고
     * */

    Optional<User> findByUserId(String userId);
    Optional<User> findByUsername(String username);
}
