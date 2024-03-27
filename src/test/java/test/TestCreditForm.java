package test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.Status;
import io.qameta.allure.selenide.AllureSelenide;
import data.DBUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import page.FormPage;

import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class TestCreditForm {
    private FormPage formPage;

    @BeforeEach
    void setUpPage() {
        formPage = new FormPage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearAll() throws SQLException{
        DBUtils.clearAllData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Credit form (positive)")
    void shouldPayByApprovedCardInCredit() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("5555666677778888");
        formPage.setCardMonth("08");
        formPage.setCardYear("24");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("999");
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
    }

}
