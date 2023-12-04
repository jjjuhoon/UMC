package umc.spring.repository;

import jdk.jshell.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);

//    Page<Mission> findAllByUserAndStatus(User user, MissionStatus status,PageRequest pageRequest);
    Page<Mission> findAllByIdIn(List<Long> missionIds, PageRequest pageRequest);

}
