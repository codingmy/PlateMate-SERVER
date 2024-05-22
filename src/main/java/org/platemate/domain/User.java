package org.platemate.domain;

import jakarta.persistence.Column;

public record User (
        @Column(name = "user_id")
        Long userId,
        @Column(name = "nickname")
        String nickname,
        @Column(name = "latitude")
        Long latitude,
        @Column(name = "longtitude")
        Long longtitude
){
}
