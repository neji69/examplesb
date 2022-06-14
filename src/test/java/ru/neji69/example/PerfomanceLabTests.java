package ru.neji69.example;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class PerfomanceLabTests {

    @Test
    @DisplayName("SB-T0001. Нажать на картинку под темой и проверить, " +
            "что открылась форма для заполнения контактов")
    void clickAndContactFormOpensTest(){

//        open("https://www.performance-lab.ru/");
//        System.out.println();

        open("https://www.performance-lab.ru/");
        System.out.println();
        $("div[data-gr=\"popup-container\"]")
                .shouldBe(Condition.hidden);
        executeJavaScript("arguments[0].remove()", $("div[data-gr=\"popup-container\"]"));


    }
}
