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
public class ChangeEmailRequest {
    @NotNull
    @NotBlank
    @NotEmpty(message = "Username ne moze biti prazan.")
    private String username;
    @NotNull
    @NotBlank
    @NotEmpty(message = "Email ne moze biti prazan.")
    @Email(message = "Email nije validan", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;
}
