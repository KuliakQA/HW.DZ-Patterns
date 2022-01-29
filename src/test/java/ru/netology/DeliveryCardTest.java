package ru.netology;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryCardTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }


    @Test
    public void shouldSuccessfulSendValidForm() {
        Configuration.holdBrowserOpen = true;
        FormInfo info = DataGenerator.generateInfo("ru");
        $("[data-test-id='city'] input").setValue(info.getCity());
        String date = DataGenerator.dateFormat(8);
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.text("Успешно!"),
                        Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date));
        String dateChange = DataGenerator.dateFormat(17);
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(dateChange);
        $(withText("Запланировать")).click();
        $("[data-test-id='replan-notification']").
                shouldHave(Condition.text("Необходимо подтверждение"),
                        Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + dateChange));

    }
}
