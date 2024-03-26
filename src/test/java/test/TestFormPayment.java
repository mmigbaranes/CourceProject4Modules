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
class TestFormPayment {

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
    void clearAll() throws SQLException {
        DBUtils.clearAllData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Test
    @DisplayName("Debit card pay(positive)")
    void shouldPayByApprovedCard() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334444");
        formPage.setCardMonth("08");
        formPage.setCardYear("24");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("999");
        formPage.pushСontinueButton();
        formPage.checkMessageSuccess();
    }
    @Test
    @DisplayName("Invalid number debit card")
    void shouldPayInvalidNumberCard() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334445");
        formPage.setCardMonth("08");
        formPage.setCardYear("24");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("999");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongNumberCard();
    }
    @Test
    @DisplayName("Invalid month debit card")
    void shouldPayInvalidMonthDebitCard() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334444");
        formPage.setCardMonth("13");
        formPage.setCardYear("24");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("999");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongDate();
    }
    @Test
    @DisplayName("Invalid year debit card")
    void shouldPayInvalidYearDebitCard() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334444");
        formPage.setCardMonth("10");
        formPage.setCardYear("22");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("999");
        formPage.pushСontinueButton();
        formPage.checkMessageOverDate();
    }
    @Test
    @DisplayName("Invalid Owner field is empty")
    void shouldPayFieldEmptyDebitCard() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334444");
        formPage.setCardMonth("10");
        formPage.setCardYear("24");
        formPage.setCardOwner(" ");
        formPage.setCardCVV("999");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
    }

    @Test
    @DisplayName("Invalid CVC or CVV date format")
    void shouldPayFieldInvalidCvcDebitCard() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334444");
        formPage.setCardMonth("10");
        formPage.setCardYear("24");
        formPage.setCardOwner("Gans Krug");
        formPage.setCardCVV("9");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Payment by card, valid data, checking the DB entry")
    void shouldPayByApprovedCardStatusInDB() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("1111222233334444");
        formPage.setCardMonth("08");
        formPage.setCardYear("24");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("969");
        formPage.pushСontinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkPaymentStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Payment by inactive card, valid data, checking the DB entry")
    void shouldNotPayByDeclinedCardStatusInDB() throws SQLException {
        formPage.buyForYourMoney();
        formPage.setCardNumber("5555666677778888");
        formPage.setCardMonth("08");
        formPage.setCardYear("24");
        formPage.setCardOwner("Ivan Petrov");
        formPage.setCardCVV("969");
        formPage.pushСontinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkPaymentStatus(Status.DECLINED);
    }
}