package hellojpa;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Member {
	@Id
	private Long id;

	//name => 필드와 매핑할 테이블의 컬럼 이름
	//insertable , updateable 등록 변경 가능여부
	@Column(name = "name")
	private String username;

	private Integer age;

	//enumtype 쓰기
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	//날짜타입
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	//대용랑 데이터
	@Lob
	private String description;

	// @Trasient -> db랑 상관없게 하기

	public Member() {
	}
	//Getter, Setter…
}
