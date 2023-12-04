package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.enums.Sex;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static umc.spring.domain.enums.MissionStatus.CHALLENGING;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return MemberResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static User toMember(MemberRequestDTO.JoinDTO request){
        Sex sex=null;
        switch (request.getSex()){
            case 1:
                sex=Sex.MALE;
                break;
            case 2:
                sex=Sex.FEMALE;
                break;
            case 3:
                sex=Sex.NONE;
                break;
        }
        return User.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .sex(sex)
                .name(request.getName())
                .preferencefoodList(new ArrayList<>())
                .build();
    }


}
