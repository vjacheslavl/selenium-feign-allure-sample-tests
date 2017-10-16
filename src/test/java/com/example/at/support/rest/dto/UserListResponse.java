package com.example.at.support.rest.dto;


import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserListResponse {
    private List<UserPayload> Items;
    private int Count;
    private int ScannedCount;
}