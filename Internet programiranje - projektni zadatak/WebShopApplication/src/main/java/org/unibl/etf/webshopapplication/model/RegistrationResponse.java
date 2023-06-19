package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String city;
    private String email;
    private String avatar;
    private boolean isActivated;
}
