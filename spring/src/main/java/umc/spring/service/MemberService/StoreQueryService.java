package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long StoreId, Integer page);



    Page<Mission> getMissionList(Long StoreId, Integer page);
}