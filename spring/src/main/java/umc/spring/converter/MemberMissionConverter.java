package umc.spring.converter;

import net.bytebuddy.dynamic.TypeResolutionStrategy;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;

import java.time.LocalDateTime;

import static umc.spring.domain.enums.MissionStatus.CHALLENGING;

public class MemberMissionConverter {

    public static MemberResponseDTO.ChallengeMissionDTO toChallengeMissionDTO(UserMission userMission){
        return MemberResponseDTO.ChallengeMissionDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(LocalDateTime.now()).build();
    }

    public static UserMission toUserMission() {
        return UserMission.builder()
                .build();
    }


    public static MemberResponseDTO.CompletMissionDTO toCompleteMissionDTO(UserMission userMission){
        return MemberResponseDTO.CompletMissionDTO.builder()
                .userMissionId(userMission.getId())
                .status(userMission.getStatus())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
