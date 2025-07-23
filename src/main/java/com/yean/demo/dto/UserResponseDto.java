package com.yean.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"user_id", "username", "age", "location", "email", "role"})
public class UserResponseDto {
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("username")
    private String name;

    private Integer age;

    @JsonProperty("location")
    private String address;
    private String email;
    private String role = "USER";

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

}
