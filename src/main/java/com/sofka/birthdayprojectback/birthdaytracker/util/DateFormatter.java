package com.sofka.birthdayprojectback.birthdaytracker.util;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class DateFormatter {

    public void setUserAgeFromDate(BirthdayDTO dto) {
        String[] split = dto.getBirthday().split("-");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);

        LocalDate userDate = LocalDate.of(year, month, day);
        Period between= Period.between(userDate, LocalDate.now());
        int age = between.getYears();
        dto.setAge("" + age);
    }
}
