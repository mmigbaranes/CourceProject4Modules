package test;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import data.DBUtils;
import org.junit.jupiter.api.*;
import page.FormPage;

import java.sql.SQLException;

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
