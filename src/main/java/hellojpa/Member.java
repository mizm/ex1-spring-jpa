package hellojpa;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
// @SequenceGenerator(
// 	name = "MEMBER_SEQ_GENERATOR",
// 	sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
// 	initialValue = 1, allocationSize = 1)
public class Member {

	/*
	식별자 전략
	기본 키 제약조건 , Null아님, 유일, 변하면 안된다
	미래까지 이 조건을 만족하는 자연키(비즈니스적으로 의미있는 주민등록번호?) 는 찾기 어렵다.  대리키(대체키)를 사용하자.
	주민번호도 이제 기본키가 아님 개인정보보호법
	Long형 + sequence + 키생성전략 사용(AutoIncrement , Sequence)
	*/


	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE,
	// 	generator = "MEMBER_SEQ_GENERATOR")
		// -> call next value for MEMBER_SEQ -> 디비에 시퀀스에게 값을 얻어와서 영속성 컨텍스트에 저장한다.
		// -> 쿼리는 commit(); 시점에 나온다.
		// allocationSize = 50 -> 미리 50개 사이즈를 올려놓고 메모리에서 1씩 씀  동시성 이슈 없음
	    //
	private Long id;
	//@GeneratedValue(strategy = GenerationType.AUTO) -> DB에 따라 자동으로 생성
	//@GeneratedValue(strategy = GenerationType.IDENTITY) -> db에 위임 mysql에 autoincrement
		// -> DB에 들어가야 pk값이 생김 -> "이 전략 사용시 persist 호출 시 데이터베이스에 insert 쿼리가 나감"
		// 디비에서 읽어온 후 영속성 컨텍스트(1차 캐시)에 저장
		// 크게 성능상의 차이가 없음
	//@GeneratedValue(strategy = GenerationType.SEQUENCE) 시퀀스를 매핑해주면됨
	//테이블전략 -> 키 생성 전용 테이블 만들기 -> 성능 떨어짐  -> 운영에서 xx
	//name => 필드와 매핑할 테이블의 컬럼 이름
	//insertable , updateable 등록 변경 가능여부
	//nullable -> 기본은 true / false 변경시  not null 제약조건
	//unique -> 유니크조건 잘안씀 -> 유니크 제약조건은 @Table(unique) 로 검
	// @Scale -> bigdecimal
	@Column(name = "name")
	private String username;

	private Integer age;

	//enumtype 쓰기
	// String은 이름 그대로 사용 -> 추가사항 반영시 순서는 변경이 생길 수 있으므로 String으로 사용하는 게 필수
	// basic 은 ORDINAL -> enum의 순서를 데이터베이스에 저장
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	//날짜타입
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	//년월
	// private LocalDate test1;
	// //년월일시
	// private LocalDateTime test2;
	//대용랑 데이터

	//매핑 타입이 String이면 CLOB
	// 제외는 BLOB
	@Lob
	private String description;

	// @Trasient -> db랑 상관없게 하기

	public Member() {
	}
	//Getter, Setter…
}
