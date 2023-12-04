package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.User;

public interface MissionRepository extends JpaRepository<Mission,Long> {
}
