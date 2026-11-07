package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static testdata.TestData.*;
import static utils.FakeDataProvider.*;

public class TextBoxTests extends TestBase {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successfulFillFormTest() {
        String name = randomFullName();
        String email = randomEmail();
        String currentAddress = randomAddress();
        String permanentAddress = randomAddress();

        textBoxPage.open();
        textBoxPage.fillName(name)
                .fillEmail(email)
                .fillCurrentAddress(currentAddress)
                .fillPermanentAddress(permanentAddress);
        textBoxPage.submit();

        textBoxPage.checkOutputVisible();
        textBoxPage.checkNameInOutput(name);
        textBoxPage.checkEmailInOutput(email);
        textBoxPage.checkCurrentAddressInOutput(currentAddress);
        textBoxPage.checkPermanentAddressInOutput(permanentAddress);
    }

    @Test
    void textBoxMinimalFieldsTest() {
        String name = randomFullName();

        textBoxPage.open();
        textBoxPage.fillName(name);
        textBoxPage.submit();

        textBoxPage.checkOutputVisible();
        textBoxPage.checkNameInOutput(name);
    }

    @Test
    void textBoxEmptyFieldsTest() {
        textBoxPage.open();
        textBoxPage.submit();

        textBoxPage.checkOutputHidden();
    }

    @Test
    void textBoxTooLongNameTest() {
        String name = TOO_LONG_NAME;

        textBoxPage.open();
        textBoxPage.fillName(name);
        textBoxPage.submit();

        textBoxPage.checkOutputVisible();
        textBoxPage.checkNameInOutput(name);
    }

    @Test
    void failEmailFillFormTest() {
        String name = randomFullName();
        String currentAddress = randomAddress();
        String permanentAddress = randomAddress();

        textBoxPage.open();
        textBoxPage.fillName(name)
                .fillEmail(INVALID_EMAIL)
                .fillCurrentAddress(currentAddress)
                .fillPermanentAddress(permanentAddress);
        textBoxPage.submit();

        textBoxPage.checkEmailErrorBorder(TEXTBOX_ERROR_BORDER);
    }
}
