package umc.spring.web.dto;

import lombok.Getter;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ReviewRequestDTO {
    @Getter
    public static class newReviewDTO {
        @NotNull
        String title;
        String body;
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "5.0", inclusive = true)
        @NotNull
        private BigDecimal rate;
    }
}