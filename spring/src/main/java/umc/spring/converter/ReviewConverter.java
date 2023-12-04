package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDTO.newReviewResultDTO toJoinResultDTO(Review review) {
        return ReviewResponseDTO.newReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.newReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .rate(request.getRate()) //원래 Localtime이였는데, 아닌거같아서 Stirng으로 변경했다.
                .build();
}}