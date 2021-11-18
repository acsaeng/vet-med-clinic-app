package group825.vetapp.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class User {

    private UUID id;

    private String name;

    private String email;

    private String password;

    private boolean isActive;

    private LocalDate activationDate;

    public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = true;
        this.activationDate = LocalDate.now();
    }
}
