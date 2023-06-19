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
public class SendEmailToTechnicalSupportRequest {
    @NotNull
    @NotBlank
    @NotEmpty(message = "Username ne moze biti prazan.")
    private String clientUsername;
    @NotNull
    @NotBlank
    @Size(min=3)
    @NotEmpty(message = "Sadrzaj ne moze biti prazan.")
    private String content;
}
