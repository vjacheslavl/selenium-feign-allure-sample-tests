package com.example.at.tests.restExamples;

import com.example.at.support.rest.ApplicationEndpoints;
import com.example.at.support.rest.RestClient;
import com.example.at.support.rest.dto.SingleUserResponse;
import com.example.at.support.rest.dto.UserListResponse;
import com.example.at.support.rest.dto.UserPayload;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("REST example")
public class RestTest {
    private RestClient restClient = new RestClient();
    private ApplicationEndpoints applicationRestClient = restClient.createClient();

    @Story("Create user")
    @Issue("TST-123")
    @Test
    public void createAndModifyUsers() {

        //Create example (POST)
        String generatedId = randomUUID().toString();
        UserPayload newUser = UserPayload.builder()
                .id(generatedId)
                .firstname("some-name")
                .lastname("some-last-name")
                .userstatus("some-status")
                .extdata("{}")
                .build();

        applicationRestClient.createUser(newUser);

        //Update example (PUT)
        newUser.setFirstname("Modified Name");
        applicationRestClient.modifyUser(newUser);

        //Get List example (GET)
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        assertThat(allUsers.getItems().size()).isGreaterThan(0).as("Size should not be 0");

        //Get Single Item by ID (GET)
        SingleUserResponse foundUser = applicationRestClient.getUseById(generatedId);
        assertThat(foundUser.getItem().getId()).isEqualTo(generatedId);
        assertThat(foundUser.getItem().getFirstname()).isEqualTo("Modified Name");
    }


    @Story("Delete user")
    @Issue("TST-124")
    @Test
    public void deleteAllUsers() {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        for (UserPayload singleUser : allUsers.getItems()) {
            applicationRestClient.deleteById(singleUser);
        }
    }
}