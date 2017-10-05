package com.example.at.support.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TermsOfUseDTO {
    protected String username;
    protected String termsOfUse;
}
