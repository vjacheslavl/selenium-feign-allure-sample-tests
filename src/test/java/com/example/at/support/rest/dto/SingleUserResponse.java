package com.example.at.support.rest.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SingleUserResponse {
    private UserPayload Item;
}