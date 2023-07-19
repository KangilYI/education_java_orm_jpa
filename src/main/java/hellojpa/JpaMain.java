package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		try {
			Member findMember = entityManager.find(Member.class, 102L);
			findMember.setName("HelloZ");

			System.out.println("==============================");

			/*
			//회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태로 만든다.
			entityManager.detach(member);

			//객체를 삭제한 상태
			//entityManager.remove(member);
			*/

			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		emf.close();
	}
}
