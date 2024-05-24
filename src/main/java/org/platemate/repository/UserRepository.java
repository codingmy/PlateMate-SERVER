package org.platemate.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.platemate.domain.TeamMapping;
import org.platemate.domain.User;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Transactional
public class UserRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public UserRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    //유저 데이터 row 추가 (2인 매핑시)
    public Long postUserData(String nickname, Long latitude, Long longtitude) {
        User user = new User(nickname, latitude, longtitude);
        em.persist(user);
        Long userId = query.select(user.userId)
                .from(user)
                .where(user.nickname.eq(nickname), user.latitude.eq(latitude), longtitude)
                .fetchLast();
        return userId;
    }

    //team테이블에 팀 매핑 원하는 row로 추가
    public Long postTeamData(Long userId){
        TeamMapping team = new TeamMapping(userId, false);
        em.persist(team);
        Long teamCode = query.select(team.teamCode)
                .from(team)
                .where(team.user1_id.eq(userId)
                        .and(team.isMapped.eq(false)))
                .fetchLast();
        return teamCode;
    }

}
