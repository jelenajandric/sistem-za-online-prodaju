package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Integer id;
    private String content;
    private String clientName;
    private String clientSurname;
    private String clientUsername;
    private String clientAvatar;
    private Timestamp time;
}
