package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission,Long> {
    List<UserMission> findAllByUserId(Long userId);

//    Page<UserMission> findAllByUserAndStatus(User user, MissionStatus status, PageRequest pageRequest);

    List<UserMission> findAllByUserAndStatus(User user, MissionStatus status);

    List<UserMission> findDistinctByUserIdAndMissionId(Long userId, Long missionId);


}
