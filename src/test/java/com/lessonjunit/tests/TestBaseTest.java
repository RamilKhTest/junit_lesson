package com.lessonjunit.tests;

import com.codeborne.selenide.Configuration;
import com.lessonjunit.pages.BasePage;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseTest {
    BasePage basePage = new BasePage();
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.lada.ru/";
        Configuration.browserSize = "1920x1080";
        //Configuration. pageLoadStrategy="eager";
    }
}
