package org.platemate.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "teamMapping")
public class TeamMapping {
    @Column(name = "user_id")
    Long userId;
    @Column(name = "nickname")
    String nickname;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_auth_id")
    Long teamAuthId;

    @Column(name = "latitude")
    Long latitude;
    @Column(name = "longtitude")
    Long longtitude;

    @Column(name = "is_team_mapped")
    Boolean isTeamMapped;
}
