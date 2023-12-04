package umc.spring.service.MemberService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

import javax.persistence.EntityNotFoundException;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission newMission(MissionRequestDTO.newMissionDTO mission, Long storeId){
        Mission newMission= MissionConverter.toMission(mission);

        Store store=storeRepository.findById(storeId)
                .orElseThrow(()->new RuntimeException("\""+storeId+"\"해당 유저가 없습니다"));
        newMission.setStore(store);

        return missionRepository.save(newMission);

    }
}