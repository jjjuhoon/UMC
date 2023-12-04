package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.UserMissionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static umc.spring.domain.enums.MissionStatus.CHALLENGING;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository userRepository;

    private final ReviewRepository reviewRepository;

    private final MissionRepository missionRepository;

    private final UserMissionRepository userMissionRepository;

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long userId, Integer page) {

        User user = userRepository.findById(userId).get();

        Page<Review> UserPage = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return UserPage;
    }

    @Override
    public Page<Mission> getMissionList(Long userId, Integer page){
        User user = userRepository.findById(userId).get();

        List<UserMission> userMissionList =userMissionRepository.findAllByUserAndStatus(user,CHALLENGING);

        List<Long> missionId=userMissionList.stream()
                .map(userMission->userMission.getMission().getId())
                .collect(Collectors.toList());


        Page<Mission> MissionPage=missionRepository.findAllByIdIn(missionId, PageRequest.of(page, 10));
        return MissionPage;
    }
}
