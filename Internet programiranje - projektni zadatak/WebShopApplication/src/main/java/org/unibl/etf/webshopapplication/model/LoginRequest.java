package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull
    @NotBlank
    @Size(min=6, max=20)
    @Pattern(regexp = "^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    @NotEmpty(message = "Username ne moze biti prazan.")
    private String username;
    @NotNull
    @NotBlank
    @Size(min=8)
    @NotEmpty(message = "Lozinka ne moze biti prazna.")
    private String password;
}
