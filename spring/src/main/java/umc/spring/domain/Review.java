package umc.spring.domain;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.UserStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1) CHECK (rate >= 0.0 AND rate <= 5.0)")
    private BigDecimal rate;


    @Column(nullable = false, columnDefinition="VARCHAR(20)")
    private String title;// 테이블에는 없지만 필요할것 같아서 추가해줬다.

    @Column(nullable = false, columnDefinition="TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status; //이것도 테이블에는 없지만 inactiveDate를 사용하기 위해 추가해줌.
                //그래도 되는진 모르겠지만 user status 에 ACTIVE,INACTIVE가 있어서 이용했다

    private LocalDate inactiveDate; //이것도 테이블에는 없지만 inactiveDate를 사용하기 위해 추가해줌.


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void setMember(User user){
        if(this.user != null)
            user.getReviewList().remove(this);
        this.user = user;
        user.getReviewList().add(this);
    }

    public void setStore(Store store){
        if(this.store != null)
            store.getReviewList().remove(this);
        this.store = store;
        store.getReviewList().add(this);
    }

}