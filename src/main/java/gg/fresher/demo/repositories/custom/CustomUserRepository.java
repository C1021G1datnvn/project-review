//package gg.fresher.demo.repositories.custom;
//
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//
//import gg.fresher.demo.entities.QUser;
//import gg.fresher.demo.entities.User;
//
//public class CustomUserRepository {
//    private final JPAQueryFactory queryFactory;
//
//    public CustomUserRepository(EntityManager entityManager) {
//        this.queryFactory = new JPAQueryFactory(entityManager);
//    }
//
//    public Optional<User> getByUsername(String username) {
//        QUser qUser = QUser.user;
//        return Optional.ofNullable(this.queryFactory.selectFrom(qUser)
//                .where(qUser.username.eq(username))
//                .fetchOne());
//    }
//}
