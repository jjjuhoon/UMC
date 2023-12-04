package umc.spring.service.MemberService;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.AddDTO request,Long regionId);
}
