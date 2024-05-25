package org.platemate.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.platemate.domain.TeamMapping;
import org.platemate.domain.User;
import org.springframework.stereotype.Repository;

import static com.querydsl.jpa.JPAExpressions.selectFrom;

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
    public Long postUserData(String nickname, Float latitude, Float longtitude) {
        User user = new User(nickname, latitude, longtitude);
        em.persist(user);
        Long userId = query.select(user.userId)
                .from(user)
                .where(user.nickname.eq(nickname), user.latitude.eq(latitude), longtitude)
                .fetchLast();
        return userId;
    }

    //team테이블에 팀 매핑 원하는 row로 추가
    public Long creatTeamData(Long userId) {
        TeamMapping team = new TeamMapping(userId, false);
        em.persist(team);
        Long authCode = query.select(team.authCode)
                .from(team)
                .where(team.user1_id.eq(userId)
                        .and(team.isMapped.eq(false)))
                .fetchLast();
        return authCode;
    }

    //입력받은 유저 아이디를 db에 저장, 팀매핑 여부를 true로 수정
    public void updateTeamMapping(Long teamAuthCode, Long userId) {
        query.update(teamMapping)
                .where(teamMapping.mapping_code.eq(teamAuthCode))
                .set(teamMapping.isMapped, true)
                .set(teamMapping.user2_id, userId)
                .execute();
    }

    //팀인증코드로 팀 매핑 데이터 가져오기
    public TeamMapping getTeamDataByTeamCode(Long teamAuthCode) {
        return query.selectFrom(teamMapping)
                .where(teamMapping.mapping_code.eq(teamAuthCode))
                .fetchFirst();
    }

    //유저 id로 유저 정보 가져오기
    public User getUserDataByUserId(Long userId) {
        return query.selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchFirst();
    }
}
