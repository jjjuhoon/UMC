package umc.spring.web.dto;

import lombok.*;
import umc.spring.domain.enums.MissionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long userId;
        LocalDateTime createdAt;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionDTO{
        Long userMissionId;
        MissionStatus status;
        LocalDateTime createdAt;
    }
    ////////////////////////////////////////////////////////////////////////////////
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReviewPreViewListDTO{
        List<UserReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReviewPreViewDTO{
        String ownerNickname;
        String storeName;
        BigDecimal rate;
        String body;
        LocalDate createdAt;
    }
    ////////////////////////////////////////////////////////////////////////////////
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreViewListDTO{
        List<UserMissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreViewDTO{
        Long missionId;
        Integer reward;
        String title;
        String body;
        MissionStatus status;
        LocalDate deadline;
        LocalDate createdAt;
    }
    /////////////////////////////////////////////////////////
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletMissionDTO {
        Long userMissionId;
        MissionStatus status;
        LocalDateTime updatedAt;
    }
}
