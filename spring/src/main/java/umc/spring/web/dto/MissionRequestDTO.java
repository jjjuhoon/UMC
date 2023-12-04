package umc.spring.web.dto;

import lombok.Getter;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class newMissionDTO {
        @NotNull
        String title;
        String body;
        Integer reward;
        LocalDate deadline;
    }
}