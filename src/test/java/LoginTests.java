import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests {

    @Test
    void succesfullLoginTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "edge";
        Configuration.holdBrowserOpen=true;

        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("dmitryzubarev@x5.ru");
        $("[name=password]").setValue("DemonZS92_").pressEnter();

        // поиск по классу "class=" можно заменить на "."
        $(".main-header__login").click();

        // поиск текста "Вы авторизованы"
        $(".logined-form").shouldHave(text("Вы авторизованы"));

        // погасить браузер
        closeWebDriver();
    }

    @Test
    void missingAllFormLoginTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen=false;

        open("https://qa.guru/cms/system/login");
        $(".btn-success").click();

        $(".login-form").shouldHave(text("Не заполнено поле E-Mail"));

        closeWebDriver();
    }

    @Test
    void missingEmailLoginTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen=false;

        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("dmitryzubarev@x5.ru").pressEnter();

        $(".login-form").shouldHave(text("Не заполнено поле Пароль"));

        closeWebDriver();
    }

    @Test
    void missingPasswordLoginTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen=false;

        open("https://qa.guru/cms/system/login");
        $("[name=password]").setValue("demonzs92_").pressEnter();

        $(".login-form").shouldHave(text("Не заполнено поле E-Mail"));

        closeWebDriver();
    }

    @Test
    void wrongPasswordLoginTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen=false;

        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("dmitryzubarev@x5.ru");
        $("[name=password]").setValue("demonzs92_").pressEnter();

        $(".login-form").shouldHave(text("Неверный пароль"));

        closeWebDriver();
    }

}