package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class StoreRequestDTO {
    @Getter
    public static class AddDTO{
        @NotBlank
        String name;
        @NotNull
        String time;
        @Size(min = 5, max = 12)
        String address;
        @NotNull
        @Size(min = 5, max = 12)
        String specAddress;

    }
}
