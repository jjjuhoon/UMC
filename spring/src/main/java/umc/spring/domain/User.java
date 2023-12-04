package umc.spring.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Sex;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.enums.Social_login;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.mapping.Preference_food;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //region_id


    @Enumerated(EnumType.STRING)
    private Social_login socialLogin;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40) //specAddress는 어디다 사용하는거??
    private String specAddress;

    @Enumerated(EnumType.STRING) //enum인 경우에만 사용.
    @Column(columnDefinition = "VARCHAR(10)")
    private Sex sex;

    @Column(columnDefinition = "VARCHAR(15)")
    private String birth;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String passwoard;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;

    private LocalDate inactiveDate;

    private Integer point;

    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(columnDefinition = "VARCHAR(50)")
    private String phone;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Preference_food> preferencefoodList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy="user",cascade =CascadeType.ALL)
    private List<Alarm> alarmList=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

}