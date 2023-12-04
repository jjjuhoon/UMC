package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.Preference_food;
import umc.spring.domain.mapping.UserMission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, length = 20)
    //@Column(nullable = false, columnDefinition = "VARCHAR(20)")이랑 똑같음. 약간의 차이가 있긴함.
    private String name;

//    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
//    private List<Preference_food> preferenceFoodList = new ArrayList<>();


}