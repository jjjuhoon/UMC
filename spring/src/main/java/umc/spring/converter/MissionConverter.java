package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.newMissionResultDTO tonewMissionResultDTO(Mission missoin) {
        return MissionResponseDTO.newMissionResultDTO.builder()
                .missionId(missoin.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Mission toMission(MissionRequestDTO.newMissionDTO mission){
        return Mission.builder()
                .title(mission.getTitle())
                .body(mission.getBody())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .build();
    }
}