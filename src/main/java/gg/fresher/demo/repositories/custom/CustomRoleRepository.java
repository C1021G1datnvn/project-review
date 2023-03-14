//package gg.fresher.demo.repositories.custom;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//
//import gg.fresher.demo.entities.QRole;
//import gg.fresher.demo.entities.QUserRole;
//import gg.fresher.demo.entities.Role;
//
//public class CustomRoleRepository {
//    private final JPAQueryFactory queryFactory;
//
//    public CustomRoleRepository(EntityManager entityManager) {
//        this.queryFactory = new JPAQueryFactory(entityManager);
//    }
//
//    public List<Role> fetchRolesByUserId(final Long userId) {
//        QRole qRole = QRole.role;
//        QUserRole qUserRole = QUserRole.userRole;
//        return this.queryFactory.selectFrom(qRole)
//                .innerJoin(qRole.userRoles, qUserRole)
//                .where(qUserRole.userId.eq(userId))
//                .fetch();
//    }
//}
