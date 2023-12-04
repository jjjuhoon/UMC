package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.MemberService.StoreCommandService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
@Tag(name = "장소 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    @PostMapping("/region/{region-id}")
    @Operation(summary = "장소 추가 API", description = "특정 지역에 장소를 추가합니다.")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> joinStore(@RequestBody @Valid StoreRequestDTO.AddDTO request,
                                                                     @PathVariable(name="region-id")Long regionId){
        Store store= storeCommandService.joinStore(request,regionId);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }
}