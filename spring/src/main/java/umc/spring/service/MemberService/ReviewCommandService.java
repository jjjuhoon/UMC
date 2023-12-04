package umc.spring.service.MemberService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review joinReview(ReviewRequestDTO.newReviewDTO request,Long userId,Long storeId);
}
