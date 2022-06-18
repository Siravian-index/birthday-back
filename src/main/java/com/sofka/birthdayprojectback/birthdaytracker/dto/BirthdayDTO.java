package com.sofka.birthdayprojectback.birthdaytracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BirthdayDTO {
    private String id;
    private String age;
    @NotBlank
    private String name;
    @NotBlank
    private String birthday;
    @NotBlank
    private String phone;
    @NotBlank
    private String city;
    @NotBlank
    private String maritalStatus;
}
