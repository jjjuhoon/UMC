package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@Tag(name = "멤버 관련 API")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final UserMissionRepository userMissionRepository;
    @PostMapping("/join")
    @Operation(summary = "멤버 추가 API", description = "멤버를 추가합니다.")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
        User user= memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(user));
    }

    @PostMapping("/{user-id}/stores/{store-id}/missions/{mission-id}")
    @Operation(summary = "멤버의 미션 추가 API", description = "멤버의 미션에 특정 가게 미션을 추가합니다.")

    public ApiResponse<MemberResponseDTO.ChallengeMissionDTO> joinMission(@PathVariable(name="user-id") Long userId,
                                                                               @PathVariable(name="store-id")Long storeId,
                                                                               @PathVariable(name="mission-id")Long missionId){
        UserMission userMission=memberCommandService.createMission(userId,storeId,missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMissionDTO(userMission));
    }
}
