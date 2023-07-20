package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	/*
	//회원 엔티티를 영속성 컨텍스트에 등록
	entityManager.persist(memeber);
	entityManager.find(T.class, Long);

	//회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태로 만든다.
	entityManager.detach(member);

	//객체를 삭제한 상태
	//entityManager.remove(member);
	*/

	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
	public static final EntityManager entityManager = emf.createEntityManager();
	public static final EntityTransaction transaction = entityManager.getTransaction();

	public static void main(String[] args) {
		transaction.begin();
		//플러시
		JPAFlush();
		emf.close();
	}

	public static void JPAFlush() {
		try {
			Member member = new Member(104L, "HelloC");
			entityManager.persist(member);

			entityManager.flush();

			System.out.println("==============================");
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}
}
