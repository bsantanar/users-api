package bsantanar.com.usersapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "phone_id")
    private Long phoneId;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "city_code")
    private String cityCode;

    @NotNull
    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
