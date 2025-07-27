package GDGoC.project.user_api.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter /* 일반적으로 엔티티를 만들 때 Setter 대신 생성자에 의해서만 값을 저장할 수 있게 함 */
@Entity /* 스프링 부트가 Member 클래스를 엔티티로 인식 */
public class Member {
    @Id /* id 속성을 PK로 지정 */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* 데이터를 저장할 때 해당 속성에 값을 일일이 입력하지 않아도 자동으로 1씩 증가하여 저장됨 */
    private Integer id;

    @Column(unique = true)
    private String userId;
    private String password;

    private String username;

    @Column(unique = true)
    private String phone;
}
