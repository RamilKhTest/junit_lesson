package com.lessonjunit.tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selectors.byText;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Тесты урока JUnit")
public class AnnotationsTest extends TestBaseTest{
    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("Smokus")
    })
    @DisplayName("Тест для проверки аннотаций")
    void annotationTest() {
        open("https://www.lada.ru/");
        $(".styles_navList__2gE6Y").shouldHave(text("Модельный ряд"));
    }

    //Тест с использованием  @CsvFileSource
    @CsvFileSource(resources = "/ResourcesSearchTest.csv", delimiter = '|')
    @ParameterizedTest(name = "При выборе города: {0} на странице отображается город: {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("Smokus")
    })
    void cityNameTest(String searchCity, String expectedCity) {
        basePage.openPage()
                .clickLocationIcon();
        $(".styles_list__OQWdz").$(byText(searchCity)).click();
        $(".styles_text__QYg3R").shouldHave(text(expectedCity));
    }

    //Тест с использованием  @CsvSource
    @CsvSource({
            "Уфа, Уфа",
            "Москва, Москва",
            "Тольятти, Тольятти"
    })
    @ParameterizedTest(name = "При выборе города: {0} на странице отображается город: {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("Smokus")
    })
    void cityNameTestWithCsvSource(String searchCity, String expectedCity) {
        basePage.openPage()
                .clickLocationIcon();
        $(".styles_list__OQWdz").$(byText(searchCity)).click();
        $(".styles_text__QYg3R").shouldHave(text(expectedCity));
    }

    @Test
    @Tag("WEB")
    @Disabled("UV-1234")
    void authTest() {
        basePage.openPage()
                .clickLoginIcon();
    }
}
