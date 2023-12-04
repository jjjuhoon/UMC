package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@Tag(name = "멤버 관련 API")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
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

    @GetMapping("/{userId}/reviews")
    @Operation(summary = "유저의 리뷰 목록 조회 API",description = "유저가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MemberResponseDTO.UserReviewPreViewListDTO> getReviewList(@PathVariable(name = "userId") Long userId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewPage=memberQueryService.getReviewList(userId,page);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewPage));
    }

    @GetMapping("/{userid}/missions/challenging")
    @Operation(summary = "유저의 진행중인 미션 목록 조회 API",description = "유저의 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userid", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MemberResponseDTO.UserMissionPreViewListDTO> getMissionList( @PathVariable(name = "userid") Long userId,
                                                                                    @CheckPage @RequestParam(name = "page") Integer page){
        Page<Mission> missionPage=memberQueryService.getMissionList(userId,page);
        return ApiResponse.onSuccess(MemberConverter.userMissionPreViewListDTO(missionPage));
    }

    @PostMapping("/{userid}/missions/{missionId}/status-change")
    @Operation(summary = "유저의 미션 상태 변화 API",description = "유저의 진행중인 미션을 완료로 바꾸는 API.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userid", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션 아이디, path variable 입니다!"),
    })
    public ApiResponse<MemberResponseDTO.CompletMissionDTO> StatusCompleted( @PathVariable(name = "userid") Long userId,
                                                                               @PathVariable(name = "missionId") Long missionId){

        UserMission userMission=memberCommandService.completMission(userId,missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toCompleteMissionDTO(userMission));
    }


}
