package com.example.at.support.rest;


import com.example.at.support.rest.dto.QuoteResponse;
import com.example.at.support.rest.dto.TermsOfUseDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;


public interface ApplicationEndpoints {

    @RequestLine("GET /initialForm")
    @Headers({"Accepts: application/json, text/plain, */*"})
    QuoteResponse getForm();

    @RequestLine("POST /quote?ignoreWarnings={ignoreWarnings}")
    @Headers("Content-Type: application/json")
    QuoteResponse create(QuoteResponse dto, @Param("ignoreWarnings") boolean ignoreWarnings);

    @RequestLine("PUT /quote/{quoteId}?ignoreWarnings={ignoreWarnings}")
    @Headers("Content-Type: application/json")
    QuoteResponse save(@Param("quoteId") String quoteId, @Param("ignoreWarnings") boolean ignoreWarnings, QuoteResponse dto);

    @RequestLine("PUT /termsofuse/QM")
    @Headers("Content-Type: application/json")
    void acceptTermOfUse(TermsOfUseDTO dto);

    @RequestLine("GET /user/active/{currentUser}")
    void setCurrentUser(@Param("currentUser") String currentUser);
}