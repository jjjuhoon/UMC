package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.enums.Sex;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static umc.spring.domain.enums.MissionStatus.CHALLENGING;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return MemberResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static User toMember(MemberRequestDTO.JoinDTO request){
        Sex sex=null;
        switch (request.getSex()){
            case 1:
                sex=Sex.MALE;
                break;
            case 2:
                sex=Sex.FEMALE;
                break;
            case 3:
                sex=Sex.NONE;
                break;
        }
        return User.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .sex(sex)
                .name(request.getName())
                .preferencefoodList(new ArrayList<>())
                .build();
    }



    public static MemberResponseDTO.UserReviewPreViewDTO userReviewPreViewDTO(Review review){
        return MemberResponseDTO.UserReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .storeName(review.getStore().getName())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static MemberResponseDTO.UserReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<MemberResponseDTO.UserReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::userReviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.UserReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
    /////////////////////////////////////////////////////////////////
    public static MemberResponseDTO.UserMissionPreViewDTO userMissionPreViewDTO(Mission mission){
        return MemberResponseDTO.UserMissionPreViewDTO.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .title(mission.getTitle())
                .body(mission.getBody())
                .status(mission.getStatus())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }
    public static MemberResponseDTO.UserMissionPreViewListDTO userMissionPreViewListDTO(Page<Mission> missionList){

        List<MemberResponseDTO.UserMissionPreViewDTO> userMissionPreViewDTO = missionList.stream()
                .map(MemberConverter::userMissionPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.UserMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(userMissionPreViewDTO.size())
                .missionList(userMissionPreViewDTO)
                .build();
    }

}
