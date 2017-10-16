package com.example.at.support.rest;


import com.example.at.support.rest.dto.SingleUserResponse;
import com.example.at.support.rest.dto.UserListResponse;
import com.example.at.support.rest.dto.UserPayload;
import feign.Headers;
import feign.Param;
import feign.RequestLine;


public interface ApplicationEndpoints {

    @Headers({"Accepts: application/json, text/plain, */*"})
    @RequestLine("GET /prod")
    UserListResponse getAllUsers();

    @RequestLine("GET /prod/{id}")
    SingleUserResponse getUseById(@Param("id") String id);


    @RequestLine("DELETE /prod")
    void deleteById(UserPayload dto);

    @RequestLine("POST /prod")
    @Headers("Content-Type: application/json")
    void createUser(UserPayload dto);

    @RequestLine("PUT /prod")
    @Headers("Content-Type: application/json")
    void modifyUser(UserPayload dto);
}