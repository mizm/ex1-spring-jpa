package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		//emf는 하나만 생성한다
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		//transaction 단위당 entity manager가 필요하다
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// jpa 데이터 변경은 무조건 트랜잭션안에서 실행해야한다.

			// Member member = new Member();
			// member.setId(2L);
			// member.setName("HELLOB");
			//
			// em.persist(member);

			// Member findMember = em.find(Member.class, 1L);
			// //삭제 em.remove(findMember);
			// //update jpa가 엔티티클래스를 관리하기 때문에 업데이트 쿼리가 동작함
			// findMember.setName("HELLOAA");

			//jpaql
			// List<Member> result = em.createQuery("select m from Member as m", Member.class)
			// 	.setFirstResult(5)
			// 	.setMaxResults(8)
			// 	.getResultList();
			// for(Member member : result) {
			// 	System.out.println("member.name = " + member.getName());
			// }

			//영속성 컨텍스트
			//비영속
			// Member member = new Member();
			// member.setId(100L);
			// member.setName("HELLOJPA");
			//1차 캐시에서 가져옴
			//Member findMember1 = em.find(Member.class, 100L);
			//영속 -> 이때 디비에 저장되는게 아님
			//em.persist(member);

			// //디비
			// Member findMember1 = em.find(Member.class, 100L);
			// // 1차 캐시
			// Member findMember2 = em.find(Member.class, 100L);
			// System.out.println("findMember.id = " + findMember1.getName());

			// 지연 쓰기 sql
			// Member member1 = new Member(150L, "A");
			// Member member2 = new Member(160L, "B");
			// em.persist(member1);
			// em.persist(member2);
			// 바로 쿼리가 안나감
			// System.out.println("-------------------------------");

			//Dirty checking 변경 감지
			// Member member = em.find(Member.class, 150L);
			// member.setName("zzzz");
			// em persist 가 필요 없다.
			// update query 가 commit 할떄 나옴
			// 최초 꺼내올때 snapshot을 만들고 변경될때 비교해서 update query를 쓰기지연 sql저장소에 올리고
			// 쓰기지연 sql 저장소 flush 후 커밋

			// flush -> 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
			// em.flush() -> 그냥 데이터베이스에 반영 1차 캐시도 그대로 , 영속성 컨텍스트는 그대로
			// commit()
			// jpql 쿼리 실행 시


			// 영속된 애들이 쿼리로 발생생
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.clear();
			em.close();
		}
		emf.close();
	}
}
