package umc.spring.domain;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.mapping.UserMission;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1) CHECK (rate >= 0.0 AND rate <= 5.0)")
    //0.0~5.0 사이에 float값 뽑아줌.
    private Float rate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;


    @Column(nullable = false) // 영업시간을 표시해주기 위해 localtime을 썻다.(근데 이게 맞는지 잘 모르겠다)
    private LocalTime time;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> ReviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> MissionList = new ArrayList<>();



}