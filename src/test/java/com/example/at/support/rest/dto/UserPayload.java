package com.example.at.support.rest.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPayload {
    private String id;
    private String firstname;
    private String lastname;
    private String userstatus;
    private String extdata;
    private long modificationdate;
    private long creationdate;
}