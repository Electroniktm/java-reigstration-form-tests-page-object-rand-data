package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static testdata.TestData.*;

public class RegistrationFormTests extends TestBase {

    private final RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @Test
    void fillAllFieldsAndSubmitTest() {
        registrationFormPage.open();
        registrationFormPage.fillFirstName(FIRST_NAME)
                .fillLastName(LAST_NAME)
                .fillEmail(STUDENT_EMAIL)
                .selectGender(GENDER)
                .fillPhone(PHONE)
                .selectBirthDate(BIRTH_MONTH, BIRTH_YEAR, BIRTH_DAY)
                .fillSubject(SUBJECT)
                .uploadPicture(UPLOAD_FILE)
                .fillCurrentAddress(ADDRESS)
                .selectState(STATE)
                .selectCity(CITY)
                .checkHobby();
        registrationFormPage.submit();

        registrationFormPage.checkModalVisible();
        registrationFormPage.checkResultTableContains(EXPECTED_STUDENT_NAME_LABEL);
        registrationFormPage.checkResultTableContains(EXPECTED_FULL_NAME);
        registrationFormPage.checkResultTableContains(STUDENT_EMAIL);
        registrationFormPage.checkResultTableContains(GENDER);
        registrationFormPage.checkResultTableContains(PHONE);
        registrationFormPage.checkResultTableContains(SUBJECT);
        registrationFormPage.checkResultTableContains(EXPECTED_HOBBY);
        registrationFormPage.checkResultTableContains(ADDRESS);
        registrationFormPage.checkResultTableContains(STATE);
        registrationFormPage.checkResultTableContains(CITY);
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        registrationFormPage.open();
        registrationFormPage.fillFirstName(FIRST_NAME)
                .fillLastName(LAST_NAME)
                .fillEmail(STUDENT_EMAIL)
                .selectGender(GENDER)
                .fillPhone(PHONE)
                .selectBirthDate(BIRTH_MONTH, BIRTH_YEAR, BIRTH_DAY);
        registrationFormPage.submit();

        registrationFormPage.checkModalVisible();
        registrationFormPage.checkResultTableContains(EXPECTED_STUDENT_NAME_LABEL);
        registrationFormPage.checkResultTableContains(EXPECTED_FULL_NAME);
        registrationFormPage.checkResultTableContains(STUDENT_EMAIL);
        registrationFormPage.checkResultTableContains(GENDER);
        registrationFormPage.checkResultTableContains(PHONE);
    }

    @Test
    void invalidEmailFormatTest() {
        registrationFormPage.open();
        registrationFormPage.fillFirstName(FIRST_NAME)
                .fillLastName(LAST_NAME)
                .fillEmail(INVALID_EMAIL_FORMAT)
                .selectGender(GENDER)
                .fillPhone(PHONE)
                .selectBirthDate(BIRTH_MONTH, BIRTH_YEAR, BIRTH_DAY);
        registrationFormPage.submit();

        registrationFormPage.checkEmailErrorBorder(REGISTRATION_ERROR_BORDER);
    }

    @Test
    void missingGenderSelectionTest() {
        registrationFormPage.open();
        registrationFormPage.fillFirstName(FIRST_NAME)
                .fillLastName(LAST_NAME)
                .fillEmail(EMAIL_RU)
                .fillPhone(PHONE)
                .selectBirthDate(BIRTH_MONTH, BIRTH_YEAR, BIRTH_DAY);
        registrationFormPage.submit();

        registrationFormPage.checkGenderErrorBorders(REGISTRATION_ERROR_BORDER);
    }

    @Test
    void invalidPhoneNumberTest() {
        registrationFormPage.open();
        registrationFormPage.fillFirstName(FIRST_NAME)
                .fillLastName(LAST_NAME)
                .fillEmail(EMAIL_RU)
                .selectGender(GENDER)
                .fillPhone(INVALID_PHONE)
                .selectBirthDate(BIRTH_MONTH, BIRTH_YEAR, BIRTH_DAY);
        registrationFormPage.submit();

        registrationFormPage.checkPhoneErrorBorder(REGISTRATION_ERROR_BORDER);
    }

    @Test
    void tooLongNameTest() {
        registrationFormPage.open();
        registrationFormPage.fillFirstName(TOO_LONG_FIRST_NAME)
                .fillLastName(LAST_NAME)
                .fillEmail(STUDENT_EMAIL)
                .selectGender(GENDER)
                .fillPhone(PHONE)
                .selectBirthDate(BIRTH_MONTH, BIRTH_YEAR, BIRTH_DAY);
        registrationFormPage.submit();

        registrationFormPage.checkModalVisible();
        registrationFormPage.checkResultTableContains(EXPECTED_STUDENT_NAME_LABEL);
        registrationFormPage.checkResultTableContains(TOO_LONG_FIRST_NAME);
        registrationFormPage.checkResultTableContains(LAST_NAME);
        registrationFormPage.checkResultTableContains(STUDENT_EMAIL);
        registrationFormPage.checkResultTableContains(GENDER);
    }

    @Test
    void emptyRequiredFieldsTest() {
        registrationFormPage.open();
        registrationFormPage.submit();

        registrationFormPage.checkGenderErrorBorders(REGISTRATION_ERROR_BORDER);
        registrationFormPage.checkPhoneErrorBorder(REGISTRATION_ERROR_BORDER);
        registrationFormPage.checkFirstNameErrorBorder(REGISTRATION_ERROR_BORDER);
        registrationFormPage.checkLastNameErrorBorder(REGISTRATION_ERROR_BORDER);
    }
}
