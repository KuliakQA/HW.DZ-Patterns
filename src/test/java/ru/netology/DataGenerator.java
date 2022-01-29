package ru.netology;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {
    public static FormInfo generateInfo(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return new FormInfo(faker.address().cityName(),
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.numerify("+7##########"));
    }

    public String dateFormat(int days) {
        return LocalDate.now().plusDays(days).
                format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}


