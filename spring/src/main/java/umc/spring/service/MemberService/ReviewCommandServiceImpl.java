package umc.spring.service.MemberService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.newReviewDTO request,Long userId,Long storeId){
        Review newReview= ReviewConverter.toReview(request);

        User user=memberRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("\""+userId+"\"해당 유저가 없습니다"));
        newReview.setMember(user);

        Store store=storeRepository.findById(storeId)
                .orElseThrow(()->new RuntimeException("\""+storeId+"\"해당 유저가 없습니다"));
        newReview.setStore(store);

        reviewRepository.save(newReview);
        return newReview;

    }
}