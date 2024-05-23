package org.platemate.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;
    @Column(name = "nickname")
    String nickname;
    @Column(name = "latitude")
    Long latitude;
    @Column(name = "longtitude")
    Long longtitude;

    @Builder
    public User(String nickname, Long latitude, Long longtitude) {
        this.nickname = nickname;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
