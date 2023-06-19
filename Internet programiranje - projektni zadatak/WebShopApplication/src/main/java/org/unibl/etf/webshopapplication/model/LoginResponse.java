package org.unibl.etf.webshopapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String city;
    private String email;
    private String avatar;
    @JsonProperty("isActivated")
    private boolean isActivated;
}
