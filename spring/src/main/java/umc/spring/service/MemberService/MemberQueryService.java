package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;

import java.util.Optional;

public interface MemberQueryService {
    Optional<User> findUser(Long id);

    Page<Review> getReviewList(Long UserId, Integer page);

    Page<Mission> getMissionList(Long UserId, Integer page);
}
