package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.UserMission;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission,Long> {
    List<UserMission> findAllByUserId(Long userId);

}
