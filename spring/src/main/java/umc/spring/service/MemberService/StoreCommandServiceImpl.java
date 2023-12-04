package umc.spring.service.MemberService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Food;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.mapping.Preference_food;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.AddDTO request,Long regionId){
        Region region = regionRepository.findById(regionId)
                .orElseThrow(()-> new RuntimeException("\""+regionId+"\"해당 지역이 없습니다"));

        Store newStore= StoreConverter.toStore(request);
        newStore.setRegion(region);
        return storeRepository.save(newStore);

    }
}
