package bsantanar.com.usersapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    @Pattern(regexp="[\\d]{9}")
    private String number;
    @Pattern(regexp="[\\d]{1}")
    private String cityCode;
    @Pattern(regexp="[\\d]{2}")
    private String countryCode;

}
