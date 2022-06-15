package ru.neji69.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PerfomanceLabTests {

    @BeforeEach
    void init() {

        Configuration.pageLoadTimeout=100000;
    }

    @Test
    @DisplayName("SB-T0001. Нажать на картинку под темой и проверить, " +
            "что открылась форма для заполнения контактов")
    void clickAndContactFormOpensTest() {

        open("https://www.performance-lab.ru/");

//        System.out.println();

//        open("https://www.performance-lab.ru/");
//        System.out.println();
        $("div[data-gr=\"popup-container\"]")
                .shouldBe(Condition.hidden);
        executeJavaScript("arguments[0].remove()", $("div[data-gr=\"popup-container\"]"));


        System.out.println();
    }
}
