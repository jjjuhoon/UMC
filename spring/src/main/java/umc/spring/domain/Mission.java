package umc.spring.domain;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.mapping.UserMission;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//rate가 있는데 내가 잘못쓴것 같아 생략했다.

    @Column(nullable = false)
    private Integer reward;

    @Column(nullable = false, columnDefinition="TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();
}
