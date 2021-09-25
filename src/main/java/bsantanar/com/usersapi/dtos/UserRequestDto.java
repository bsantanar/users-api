package bsantanar.com.usersapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=(.*\\d){2})(?=.*[a-z])(?=.*[A-Z]).{6,20}$")
    private String password;
    @NotNull
    private List<PhoneDto> phones;

}
