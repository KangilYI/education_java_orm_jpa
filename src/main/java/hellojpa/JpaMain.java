package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = emf.createEntityManager();


		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {

			//Member findMember = entityManager.find(Member.class, 1L);
			//findMember.setName("HelloA");
			List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class)
				.setFirstResult(2)
				.setMaxResults(3)
				.getResultList();

			for (Member member : resultList) {
				System.out.println("member.getName() = " + member.getName());
			}

			//entityManager.persist(findMember);

			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		emf.close();
	}
}
