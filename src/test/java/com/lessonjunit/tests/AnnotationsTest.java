package com.lessonjunit.tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import com.codeborne.selenide.WebDriverRunner;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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

    //Тест с аннотацией @ValueSource
    @ValueSource(strings ={
            "Модельный ряд",
            "Покупателям",
            "Владельцам"
    })
    @ParameterizedTest(name = "При выборе хедера: {0} в урле отображается текст https://www.lada.ru/")
    @Tags({
            @Tag("WEB"),
            @Tag("Smokus")
    })
    void headersTest(String header) {
        open("https://www.lada.ru/");
        String url = "https://www.lada.ru/";
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        $(".styles_navList__2gE6Y").$(byText(header)).click();
        assertEquals(url, currentUrl);
    }

    //Тест с аннотацией @MethodSource
    static Stream<Arguments> methodSourceTest() {
        return Stream.of(
            Arguments.of(
                    "Подбор автомобиля", List.of("Автомобили в наличии", "Конфигуратор", "Сравнение комплектаций", "Оценка стоимости Trade-In", "Пройти тест-драйв", "LADA Технологии", "Новая или подержанная", "Проверка ПТС")
            ),
            Arguments.of(
                    "Выгодная покупка", List.of("Акции и спецпредложения", "Кредитный калькулятор", "Программы автокредитования", "Максимальная выгода", "LADA Страхование", "Сервисный контракт LADA")
            )
        );
    }
    @MethodSource
    @ParameterizedTest
    void methodSourceTest(String offers, List<String> services) {
        open("https://www.lada.ru/");
        $(".styles_navList__2gE6Y").$(byText("Покупателям")).click();
        $(".styles_tabs__2Yw0u").$(byText(offers)).click();
        $$(".styles_items__1h15r a").shouldHave(CollectionCondition.texts(services));
    }
}
