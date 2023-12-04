package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDTO {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreResultDTO {
        Long storeId;
        LocalDateTime createdAt;
    }
}
