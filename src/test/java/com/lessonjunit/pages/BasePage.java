package com.lessonjunit.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {
    SelenideElement loginIcon = $(".styles_content__2Z91H");
    SelenideElement locationIcon = $(".styles_location__1qKKy");

    public BasePage openPage(){
        open("https://www.lada.ru/");
        return this;
    }

    public BasePage clickLoginIcon() {
        loginIcon.click();
        return this;
    }
    public BasePage clickLocationIcon() {
        locationIcon.click();
        return this;
    }


}
