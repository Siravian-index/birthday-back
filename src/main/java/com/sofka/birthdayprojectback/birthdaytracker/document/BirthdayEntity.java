package com.sofka.birthdayprojectback.birthdaytracker.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class BirthdayEntity {
    @Id
    private String id;
    private String name;
    private String age;
    private String birthday;
    private String phone;
    private String city;
    private String maritalStatus;
    private String secret;
}
