package umc.spring.service.MemberService;

import org.springframework.web.bind.annotation.PathVariable;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    User joinMember(MemberRequestDTO.JoinDTO request);
    UserMission createMission(Long userId, Long storeId, Long missionId);
}
