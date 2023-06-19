package org.unibl.etf.webshopapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewProductRequest {
    @NotNull
    @NotBlank
    @Size(min=3, max=50)
    @Pattern(regexp = "^[a-zA-Z0-9 ,.'-]+$")
    @NotEmpty(message = "Naslov ne moze biti prazan.")
    private String title;
    @NotNull
    @NotBlank
    @Size(min=3)
    @NotEmpty(message = "Opis ne moze biti prazan.")
    private String description;
    @NotNull
    @NotBlank
    @Size(min=1)
    @Pattern(regexp = "^[0-9]+$")
    @NotEmpty(message = "Cijena ne moze biti prazna.")
    private double price;
    @NotNull
    @NotBlank
    private boolean isNew;
    @NotNull
    @NotBlank
    @Size(min=3, max=40)
    @Pattern(regexp = "^[a-zA-Z0-9 ,.'-]+$")
    @NotEmpty(message = "Lokacija ne moze biti prazna.")
    private String location;
    @NotNull
    @NotBlank
    @NotEmpty(message = "Kontakt ne moze biti prazan.")
    private String contact;
    @NotNull
    @NotBlank
    @NotEmpty(message = "Naziv kategorije ne moze biti prazan.")
    private String categoryName;
    private String clientUsernameSelling;
}
