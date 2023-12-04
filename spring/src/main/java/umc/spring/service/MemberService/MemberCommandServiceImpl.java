package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.FoodCategoryHandler;
import umc.spring.converter.*;
import umc.spring.domain.*;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.Preference_food;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.*;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public User joinMember(MemberRequestDTO.JoinDTO request){

        User newUser= MemberConverter.toMember(request);
        List<Food> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<Preference_food> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setUser(newUser);});

        return memberRepository.save(newUser);
    }
    @Override
    @Transactional
    public UserMission createMission(Long userId, Long storeId, Long missionId){

        UserMission newUserMission= MemberMissionConverter.toUserMission();

        User user=memberRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("\""+userId+"\"해당 유저가 없습니다"));
        newUserMission.setUser(user);

        Mission mission=missionRepository.findById(missionId)
                .orElseThrow(()->new RuntimeException("\""+missionId+"\"해당 유저가 없습니다"));
        newUserMission.setMission(mission);

        newUserMission.setStatus(MissionStatus.CHALLENGING);

        return userMissionRepository.save(newUserMission);


    }

}
