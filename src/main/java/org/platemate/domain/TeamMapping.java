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
    @Column(name = "user1_id")
    Long user1_id;

    @Column(name = "user2_id")
    Long user2_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_code")
    Long mapping_code;

    @Column(name = "is_mapped")
    Boolean isMapped;

}
