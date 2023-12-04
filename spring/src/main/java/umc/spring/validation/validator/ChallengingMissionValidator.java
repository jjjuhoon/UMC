//package umc.spring.validation.validator;
//
//import jdk.jshell.Snippet;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import umc.spring.apiPayload.code.status.ErrorStatus;
//import umc.spring.domain.enums.MissionStatus;
//import umc.spring.domain.mapping.UserMission;
//import umc.spring.repository.UserMissionRepository;
//import umc.spring.validation.annotation.ChallengingMissions;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class ChallengingMissionValidator implements ConstraintValidator<ChallengingMissions, Long> {
//
//    private final UserMissionRepository userMissionRepository;
//    @Override
//    public void initialize(ChallengingMissions constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(Long values, ConstraintValidatorContext context) {
////        Long userId = (Long) values[0];
//        Long missionId = values;
//
//        List<UserMission> newUserMission = userMissionRepository.findAllByUserId(userId);
//
//        boolean isChallenging=newUserMission.stream()
//                .anyMatch(userMission -> userMission.getMission().equals(missionId) && userMission.getStatus() == MissionStatus.CHALLENGING);
//
//        if (isChallenging) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGING.toString()).addConstraintViolation();
//        }
//        return !isChallenging;
//    }
//}
