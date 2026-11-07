package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class RegistrationFormTests extends TestBase {

    @Test
    void fillAllFieldsAndSubmitTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(STUDENT_EMAIL);

        $("#genterWrapper").$(byText(GENDER)).click();

        $("#userNumber").setValue(PHONE);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").$(byText(BIRTH_MONTH)).click();
        $(".react-datepicker__year-select").$(byText(BIRTH_YEAR)).click();

        $(String.format(".react-datepicker__day--0%s", BIRTH_DAY)).click();

        $("#subjectsInput").setValue(SUBJECT).pressEnter();

        $("#uploadPicture").uploadFromClasspath(UPLOAD_FILE);

        $("#currentAddress").setValue(ADDRESS);

        $("[id='react-select-3-input']").setValue(STATE).pressEnter();
        $("[id='react-select-4-input']").setValue(CITY).pressEnter();

        $("[id='hobbies-checkbox-1']").click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text(EXPECTED_STUDENT_NAME_LABEL));
        $(".table-responsive").shouldHave(text(EXPECTED_FULL_NAME));
        $(".table-responsive").shouldHave(text(STUDENT_EMAIL));
        $(".table-responsive").shouldHave(text(GENDER));
        $(".table-responsive").shouldHave(text(PHONE));
        $(".table-responsive").shouldHave(text(SUBJECT));
        $(".table-responsive").shouldHave(text(EXPECTED_HOBBY));
        $(".table-responsive").shouldHave(text(ADDRESS));
        $(".table-responsive").shouldHave(text(STATE));
        $(".table-responsive").shouldHave(text(CITY));
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(STUDENT_EMAIL);

        $("#genterWrapper").$(byText(GENDER)).click();

        $("#userNumber").setValue(PHONE);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").$(byText(BIRTH_MONTH)).click();
        $(".react-datepicker__year-select").$(byText(BIRTH_YEAR)).click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text(EXPECTED_STUDENT_NAME_LABEL));
        $(".table-responsive").shouldHave(text(EXPECTED_FULL_NAME));
        $(".table-responsive").shouldHave(text(STUDENT_EMAIL));
        $(".table-responsive").shouldHave(text(GENDER));
        $(".table-responsive").shouldHave(text(PHONE));
    }

    @Test
    void invalidEmailFormatTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(INVALID_EMAIL_FORMAT);

        $("#genterWrapper").$(byText(GENDER)).click();

        $("#userNumber").setValue(PHONE);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").$(byText(BIRTH_MONTH)).click();
        $(".react-datepicker__year-select").$(byText(BIRTH_YEAR)).click();

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
    }

    @Test
    void missingGenderSelectionTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(EMAIL_RU);

        $("#userNumber").setValue(PHONE);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").$(byText(BIRTH_MONTH)).click();
        $(".react-datepicker__year-select").$(byText(BIRTH_YEAR)).click();

        $("#submit").click();

        $("[id=gender-radio-1]").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
        $("[id=gender-radio-3]").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
    }

    @Test
    void invalidPhoneNumberTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(EMAIL_RU);

        $("#genterWrapper").$(byText(GENDER)).click();

        $("#userNumber").setValue(INVALID_PHONE);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").$(byText(BIRTH_MONTH)).click();
        $(".react-datepicker__year-select").$(byText(BIRTH_YEAR)).click();

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
    }

    @Test
    void tooLongNameTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(TOO_LONG_FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(STUDENT_EMAIL);

        $("#genterWrapper").$(byText(GENDER)).click();

        $("#userNumber").setValue(PHONE);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").$(byText(BIRTH_MONTH)).click();
        $(".react-datepicker__year-select").$(byText(BIRTH_YEAR)).click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text(EXPECTED_STUDENT_NAME_LABEL));
        $(".table-responsive").shouldHave(text(TOO_LONG_FIRST_NAME));
        $(".table-responsive").shouldHave(text(LAST_NAME));
        $(".table-responsive").shouldHave(text(STUDENT_EMAIL));
        $(".table-responsive").shouldHave(text(GENDER));
    }

    @Test
    void emptyRequiredFieldsTest() {
        open("/automation-practice-form");

        $("#submit").click();

        $("[id=gender-radio-1]").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
        $("[id=gender-radio-3]").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));

        $("#userNumber").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));

        $("#firstName").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
        $("#lastName").shouldHave(cssValue("border-color", REGISTRATION_ERROR_BORDER));
    }
}
