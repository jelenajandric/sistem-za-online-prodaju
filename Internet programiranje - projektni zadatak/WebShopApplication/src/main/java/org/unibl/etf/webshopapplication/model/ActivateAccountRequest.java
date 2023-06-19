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
public class ActivateAccountRequest {
    @NotNull
    @NotBlank
    @NotEmpty(message = "Username ne moze biti prazan.")
    private String username;
    @NotNull
    @NotBlank
    @Size(min=4, max=4)
    @Pattern(regexp = "^[0-9]+$")
    @NotEmpty(message = "Pin ne moze biti prazan.")
    private int pin;
}
