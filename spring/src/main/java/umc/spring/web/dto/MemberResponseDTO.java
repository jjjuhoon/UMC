package umc.spring.web.dto;

import lombok.*;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

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
}
