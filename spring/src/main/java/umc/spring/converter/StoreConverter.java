package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.AddStoreResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static Store toStore(StoreRequestDTO.AddDTO request) {
        return Store.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .name(request.getName())
                .time(request.getTime()) //원래 Localtime이였는데, 아닌거같아서 Stirng으로 변경했다.
                .build();
    }
}