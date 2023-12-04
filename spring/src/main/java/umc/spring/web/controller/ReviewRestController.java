package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.MemberService.ReviewCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@Tag(name = "댓글 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{user-id}/stores/{stores-id}/reviews")
    @Operation(summary = "댓글 추가 API", description = "댓글을 추가합니다.")
    public ApiResponse<ReviewResponseDTO.newReviewResultDTO> newReview(@RequestBody @Valid ReviewRequestDTO.newReviewDTO request,
                                                                       @PathVariable(name="user-id")Long UserId,
                                                                       @PathVariable(name="stores-id")@ExistStore Long StoreId){
        Review review= reviewCommandService.joinReview(request,UserId,StoreId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }
}