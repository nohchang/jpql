package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            //페이징 쿼리
//            String jpql = "select m from Member m order by m.age desc";
//            List<Member> result = em.createQuery(jpql, Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }

            //조인 쿼리
//            String jpql = "select m from Member m inner join m.team t";
////            String jpql = "select m from Member m left join m.team t on t.name = 'teamA'";
//            List<Member> result = em.createQuery(jpql, Member.class)
//                    .getResultList();

            //조건식
//            String jpql =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금'" +
//                            "     when m.age >= 60 then '경로요금'" +
//                            "     else '일반요금' " +
//                            "end " +
//                    "from Member m";
//            List<String> result = em.createQuery(jpql, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            //fetch join -> N + 1 문제 해결
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();
//
//            String jpql = "select m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(jpql, Member.class)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//            }

            //named 쿼리
            List<Member> result = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
            }

            //벌크 연산
//            int resultCount = em.createQuery("update Member m set m.age = 20")
//                    .executeUpdate();
//
//            em.clear();
//            Member findMember = em.find(Member.class, member1.getAge());
//            System.out.println("findMember = " + findMember);
//            System.out.println("resultCount = " + resultCount);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
