package umc.spring.domain;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberAgree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "VARCHAR(15)")
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private Boolean optional;

//    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
//    private List<MemberAgree> memberAgreeList = new ArrayList<>();
}