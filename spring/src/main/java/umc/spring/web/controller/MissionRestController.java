package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MemberService.MissionCommandService;
import umc.spring.web.dto.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
@Tag(name = "미션 관련 API")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{store-id}/missions")
    @Operation(summary = "미션 추가 API", description = "가게에 미션을 추가합니다.")
    public ApiResponse<MissionResponseDTO.newMissionResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.newMissionDTO request,
                                                                             @PathVariable(name ="store-id")Long storeId){
        Mission mission= missionCommandService.newMission(request,storeId);
        return ApiResponse.onSuccess(MissionConverter.tonewMissionResultDTO(mission));
    }
}