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
public class RegistrationRequest {
    @NotNull
    @NotBlank
    @Size(min=3, max=20)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @NotEmpty(message = "Ime ne moze biti prazno.")
    private String name;
    @NotNull
    @NotBlank
    @Size(min=3, max=20)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @NotEmpty(message = "Prezime ne moze biti prazno.")
    private String surname;
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
    @NotNull
    @NotBlank
    @Size(min=3, max=20)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @NotEmpty(message = "Grad ne moze biti prazan.")
    private String city;
    @NotNull
    @NotBlank
    @NotEmpty(message = "Email ne moze biti prazan.")
    @Email(message = "Email nije validan", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;
}
