package umc.spring.service.MemberService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

public interface MissionCommandService {
    Mission newMission(MissionRequestDTO.newMissionDTO mission, Long storeId);
}