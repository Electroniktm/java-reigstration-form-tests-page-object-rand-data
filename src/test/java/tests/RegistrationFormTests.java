package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static testdata.TestData.*;
import static utils.FakeDataProvider.*;

public class RegistrationFormTests extends TestBase {

    private final RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @Test
    void fillAllFieldsAndSubmitTest() {
        String firstName = randomFirstName();
        String lastName = randomLastName();
        String email = randomEmail();
        String gender = randomGender();
        String phone = randomPhone();
        String birthMonth = randomBirthMonth();
        String birthYear = randomBirthYear();
        String birthDay = randomBirthDay();
        String subject = randomSubject();
        String address = randomAddress();
        String state = randomState();
        String city = randomCity(state);

        registrationFormPage.open();
        registrationFormPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .selectGender(gender)
                .fillPhone(phone)
                .selectBirthDate(birthMonth, birthYear, birthDay)
                .fillSubject(subject)
                .uploadPicture(UPLOAD_FILE)
                .fillCurrentAddress(address)
                .selectState(state)
                .selectCity(city)
                .checkHobby();
        registrationFormPage.submit();

        registrationFormPage.checkModalVisible();
        registrationFormPage.checkResultTableContains(EXPECTED_STUDENT_NAME_LABEL);
        registrationFormPage.checkResultTableContains(firstName + " " + lastName);
        registrationFormPage.checkResultTableContains(email);
        registrationFormPage.checkResultTableContains(gender);
        registrationFormPage.checkResultTableContains(phone);
        registrationFormPage.checkResultTableContains(subject);
        registrationFormPage.checkResultTableContains(EXPECTED_HOBBY);
        registrationFormPage.checkResultTableContains(address);
        registrationFormPage.checkResultTableContains(state);
        registrationFormPage.checkResultTableContains(city);
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        String firstName = randomFirstName();
        String lastName = randomLastName();
        String email = randomEmail();
        String gender = randomGender();
        String phone = randomPhone();
        String birthMonth = randomBirthMonth();
        String birthYear = randomBirthYear();
        String birthDay = randomBirthDay();

        registrationFormPage.open();
        registrationFormPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .selectGender(gender)
                .fillPhone(phone)
                .selectBirthDate(birthMonth, birthYear, birthDay);
        registrationFormPage.submit();

        registrationFormPage.checkModalVisible();
        registrationFormPage.checkResultTableContains(EXPECTED_STUDENT_NAME_LABEL);
        registrationFormPage.checkResultTableContains(firstName + " " + lastName);
        registrationFormPage.checkResultTableContains(email);
        registrationFormPage.checkResultTableContains(gender);
        registrationFormPage.checkResultTableContains(phone);
    }

    @Test
    void invalidEmailFormatTest() {
        String firstName = randomFirstName();
        String lastName = randomLastName();
        String gender = randomGender();
        String phone = randomPhone();
        String birthMonth = randomBirthMonth();
        String birthYear = randomBirthYear();
        String birthDay = randomBirthDay();

        registrationFormPage.open();
        registrationFormPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(INVALID_EMAIL_FORMAT)
                .selectGender(gender)
                .fillPhone(phone)
                .selectBirthDate(birthMonth, birthYear, birthDay);
        registrationFormPage.submit();

        registrationFormPage.checkEmailErrorBorder(REGISTRATION_ERROR_BORDER);
    }

    @Test
    void missingGenderSelectionTest() {
        String firstName = randomFirstName();
        String lastName = randomLastName();
        String phone = randomPhone();
        String birthMonth = randomBirthMonth();
        String birthYear = randomBirthYear();
        String birthDay = randomBirthDay();

        registrationFormPage.open();
        registrationFormPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(EMAIL_RU)
                .fillPhone(phone)
                .selectBirthDate(birthMonth, birthYear, birthDay);
        registrationFormPage.submit();

        registrationFormPage.checkGenderErrorBorders(REGISTRATION_ERROR_BORDER);
    }

    @Test
    void invalidPhoneNumberTest() {
        String firstName = randomFirstName();
        String lastName = randomLastName();
        String gender = randomGender();
        String birthMonth = randomBirthMonth();
        String birthYear = randomBirthYear();
        String birthDay = randomBirthDay();

        registrationFormPage.open();
        registrationFormPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(EMAIL_RU)
                .selectGender(gender)
                .fillPhone(INVALID_PHONE)
                .selectBirthDate(birthMonth, birthYear, birthDay);
        registrationFormPage.submit();

        registrationFormPage.checkPhoneErrorBorder(REGISTRATION_ERROR_BORDER);
    }

    @Test
    void tooLongNameTest() {
        String lastName = randomLastName();
        String email = randomEmail();
        String gender = randomGender();
        String phone = randomPhone();
        String birthMonth = randomBirthMonth();
        String birthYear = randomBirthYear();
        String birthDay = randomBirthDay();

        registrationFormPage.open();
        registrationFormPage.fillFirstName(TOO_LONG_FIRST_NAME)
                .fillLastName(lastName)
                .fillEmail(email)
                .selectGender(gender)
                .fillPhone(phone)
                .selectBirthDate(birthMonth, birthYear, birthDay);
        registrationFormPage.submit();

        registrationFormPage.checkModalVisible();
        registrationFormPage.checkResultTableContains(EXPECTED_STUDENT_NAME_LABEL);
        registrationFormPage.checkResultTableContains(TOO_LONG_FIRST_NAME);
        registrationFormPage.checkResultTableContains(lastName);
        registrationFormPage.checkResultTableContains(email);
        registrationFormPage.checkResultTableContains(gender);
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
